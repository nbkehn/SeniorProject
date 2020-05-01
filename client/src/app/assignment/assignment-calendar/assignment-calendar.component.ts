import { Component, OnInit } from '@angular/core';

import { CdkDragDrop, transferArrayItem, copyArrayItem } from '@angular/cdk/drag-drop';
import { Technician } from 'src/app/technician/technician';
import { TechnicianService } from 'src/app/technician/technician.service';
import { AlertService } from 'src/app/alert/alert.service';
import { AppointmentService } from 'src/app/appointment/appointment.service';
import { Appointment } from 'src/app/appointment/appointment';
import { MatDialog } from '@angular/material/dialog';
import { ViewInstallerDialogComponent } from 'src/app/view-installer-dialog/view-installer-dialog.component';
import { AssignmentService } from '../assignment.service';
import { Assignment } from '../assignment';
import { isNumber } from 'util';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment-calendar.component.html',
  styleUrls: ['./assignment-calendar.component.less']
})
export class AssignmentCalendarComponent implements OnInit {

  private technicians: Technician [];
  private listForDeletedItems: any [];
  private calendarData: any [];
  private awayList: any [];
  private startOfWeek: Date;
  private endOfWeek: Date;
  private dayLength =  Date.parse("01-02-2020") - Date.parse("01-01-2020");
  private weekLength = 7;

  constructor(public dialog: MatDialog, private technicianService: TechnicianService, private appointmentService: AppointmentService, private assignmentService: AssignmentService, private alertService: AlertService) {}

  ngOnInit() {
    const currentDate = new Date();
    const dayOfWeekIndex = currentDate.getDay();
    this.startOfWeek = new Date(currentDate.valueOf() - dayOfWeekIndex*this.dayLength);
    this.startOfWeek = new Date(this.startOfWeek.setHours(20, 0, 0, 0));
    this.endOfWeek = new Date(this.startOfWeek.valueOf() + (this.weekLength - 1)*this.dayLength);
    this.endOfWeek = new Date(this.endOfWeek.setHours(20, 0, 0, 0));
    this.calendarData = [];
    this.awayList = [];
    this.listForDeletedItems = [];
    this.technicianService.getTechniciansList().subscribe((response) => {
      this.technicians = response;
      this.refreshCalendar();
      this.getAppointmentsForCurrentRange();
    }, () => {
      this.alertService.error("Technicians could not be loaded.", false);
    });
  }

  assign(event: CdkDragDrop<string[]>, dayIndex: number, slotIndex: number = 0) {
    const data = event.previousContainer.data[event.previousIndex];
    var technician: Technician;
    if (typeof(data) == "string") {
      technician = JSON.parse(data);
    } else {
      technician = data;
    }
    const currentApptObject = this.calendarData[dayIndex][slotIndex];
    for (var i=0; i < currentApptObject.assignment.unavailableTechnicians.length; i++) {
      if (currentApptObject.assignment.unavailableTechnicians[i].id == technician.id) {
        alert("Error: This technician has already been marked as away for this day");
        return;
      }
    }
    if (!event.container.data.includes(data) && currentApptObject.appointment) {
      const updatedAssignments = [];
      if (event.container.id != "listOfTeams" && event.previousContainer.id != "listOfTeams") {
        transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
        const previousIndices = this.parseId(event.previousContainer.id);
        updatedAssignments.push(this.calendarData[previousIndices[0]][previousIndices[1]].assignment);
      } else {
        if (event.previousContainer.id == "listOfTeams") {
          copyArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
        }
      }
      updatedAssignments.push(currentApptObject.assignment);
      if (currentApptObject.numOfDays > 1 && currentApptObject.assignment.dayNumber == 1 && dayIndex != 6) {
        var tempIndex = dayIndex + 1;
        var appointmentDayIndex = 2; // Indicates the current day of the appointment being processed (i.e. 2/numOfDays)
        while (tempIndex <= 6 && appointmentDayIndex <= currentApptObject.numOfDays) {
          for (var i=0; i<this.calendarData[tempIndex].length; i++) {
            const tempObject = this.calendarData[tempIndex][i];
            if (tempObject.appointment && tempObject.appointment.id == currentApptObject.appointment.id) {
              tempObject.assignment.technicians.push(data);
              updatedAssignments.push(tempObject.assignment);
                }
              }
              tempIndex = tempIndex + 1;
              appointmentDayIndex = appointmentDayIndex + 1;
        }
      }
      this.updateAssignments(updatedAssignments).then(() => {
          this.alertService.success("Assignments updated successfully", false);
        }, () => {
          this.alertService.error("Could not update assignments", false);
      });
    }
    }

    parseId(id: string) {
      const indices = id.split(',');
      return [parseInt(indices[0]), parseInt(indices[1])];
    }

  markAsAway(event: CdkDragDrop<string[]>) {
    const dayIndex = parseInt(event.container.id);
    var technicianAssigned = false;
    var technician: Technician;
      const data = event.previousContainer.data[event.previousIndex];
      if (typeof(data) == "string") {
        technician = JSON.parse(data);
      } else {
        technician = data;
      }
    for (var i=0; i < this.calendarData[dayIndex].length; i++) {
      const appointmentObject = this.calendarData[dayIndex][i];
      if (appointmentObject.assignment) {
        const matchingAppointments = appointmentObject.assignment.technicians.filter((t) => {
          return t.id == technician.id;
        })
        if (matchingAppointments.length != 0) {
          technicianAssigned = true;
          break;
        }
      }
    } if (technicianAssigned) {
      alert("Error: This team has already been assigned on this day");
    } else {
      if (event.container.id != "listOfTeams" && event.previousContainer.id != "listOfTeams") {
        transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
      } else {
        copyArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
      }
      const updatedAssignments = [];
      for (var i=0; i < this.calendarData[dayIndex].length; i++) {
        const appointmentObject = this.calendarData[dayIndex][i];
        if (appointmentObject.assignment) {
          appointmentObject.assignment.unavailableTechnicians = this.awayList[dayIndex];
          updatedAssignments.push(appointmentObject.assignment);
        }
      }
      this.updateAssignments(updatedAssignments).then(() => {
        this.alertService.success("Assignments updated successfully", false);
      }, () => {
        this.alertService.error("Could not update assignments", false);
      });
    }
  }

  toss(event: CdkDragDrop<string[]>) {
    const updatedAssignments = [];
    var technician: Technician;
      const data = event.previousContainer.data[event.previousIndex];
      if (typeof(data) == "string") {
        technician = JSON.parse(data);
      } else {
        technician = data;
      }
    transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    if (event.previousContainer.id.includes(",")) { //Checking if it's a member of the main assignment calendar
      const indices = this.parseId(event.previousContainer.id);
    const dayIndex = indices[0];
    const assignmentIndex = indices[1];
    updatedAssignments.push(this.calendarData[dayIndex][assignmentIndex].assignment);
    } else {
      const dayIndex = parseInt(event.previousContainer.id);
      if (!Number.isNaN(dayIndex)) {
        for (var i=0; i < this.calendarData[dayIndex].length; i++) {
          const apptObject: {appointment: Appointment, assignment: Assignment, numOfDays: number} = this.calendarData[dayIndex][i];
          const removeIndex = apptObject.assignment.unavailableTechnicians.indexOf(technician);
          apptObject.assignment.unavailableTechnicians.splice(removeIndex, 1);
          updatedAssignments.push(apptObject.assignment);
        }
      }
    }
        this.updateAssignments(updatedAssignments).then(
          () => {
              this.alertService.success("Assignments updated successfully", false);
              event.container.removeItem(event.item);
              this.listForDeletedItems = [];
          }, () => {
            this.alertService.error("Could not update assignments", false);
          });
      }

  changeWeek(moveAhead: boolean) {
    const dayLength = Date.parse("01-02-2020") - Date.parse("01-01-2020");
    const weekLength = 7;
    if (moveAhead) {
      this.startOfWeek = new Date(this.startOfWeek.valueOf() + weekLength * dayLength);
      this.startOfWeek = new Date(this.startOfWeek.setHours(20, 0, 0, 0));
      this.endOfWeek = new Date(this.endOfWeek.valueOf() + weekLength * dayLength);
      this.endOfWeek = new Date(this.endOfWeek.setHours(20, 0, 0, 0));
    } else {
      this.startOfWeek = new Date(this.startOfWeek.valueOf() - weekLength * dayLength);
      this.startOfWeek = new Date(this.startOfWeek.setHours(20, 0, 0, 0));
      this.endOfWeek = new Date(this.endOfWeek.valueOf() - weekLength * dayLength);
      this.endOfWeek = new Date(this.endOfWeek.setHours(20, 0, 0, 0));
    }
    this.refreshCalendar();
    this.getAppointmentsForCurrentRange();
  }

  formatDate(date: Date) {
    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    const dayNumber = date.getDate();
    const dayNumberString = dayNumber < 10 ? `0${dayNumber}` : dayNumber.toString();
    const monthString = months[date.getMonth()];
    const yearNumber = date.getFullYear();
    return `${monthString} ${dayNumberString} ${yearNumber}`;

  }

  getAppointmentsForCurrentRange() {
    // Get the appointments which fall in the current week
    this.appointmentService.getAppointmentsList().subscribe((response: Appointment []) => {
      const appointmentsInRange = response.filter((appt: Appointment) => {
        const startDate = new Date(Date.parse(appt.startDate.toString()) + this.dayLength);
        const endDate = new Date(Date.parse(appt.endDate.toString()) + this.dayLength);
        if (startDate >= this.startOfWeek && startDate <= this.endOfWeek) {
          return true;
        }
        if (endDate >= this.startOfWeek && endDate <= this.endOfWeek) {
            return true;
        }
        if (startDate < this.startOfWeek && endDate > this.endOfWeek) {
              return true;
        }
        return false;
      });

      var apptsProcessed = 0;
      var currentlyProcessing = false;
      var noErrors = true;
      // var apptsAndAssignments = [];
      this.getAssignmentsForAppointments(appointmentsInRange)
      .then((apptsAndAssignments) => {
          const appointmentsPerDay = [];
          for (var i=0; i<this.weekLength; i++) {
            appointmentsPerDay.push([]);
          }
    
          // Add information regarding the appointments to each of the 7 days of the week
          for (var i=0; i < apptsAndAssignments.length; i++) {
            const tempAppt = apptsAndAssignments[i].appointment;
            const startDate = new Date(Date.parse(tempAppt.startDate.toString()) + this.dayLength);
            const endDate = new Date(Date.parse(tempAppt.endDate.toString()) + this.dayLength);
            var tempDate = startDate >= this.startOfWeek ? startDate : this.startOfWeek;
            const lengthOfAppointment = Math.ceil((endDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
            while (tempDate <= endDate && tempDate <= this.endOfWeek) {
              const dayIndex = Math.floor((tempDate.valueOf() - this.startOfWeek.valueOf()) / this.dayLength);
              const appointmentDayIndex = Math.ceil((tempDate.valueOf() - startDate.valueOf()) / this.dayLength);
              appointmentsPerDay[dayIndex].push({
                appointment: tempAppt,
                assignment: apptsAndAssignments[i].assignments[appointmentDayIndex],
                numOfDays: lengthOfAppointment,
              });
              tempDate = new Date(tempDate.valueOf() + this.dayLength);
            }
          }

          for (var i=0; i < appointmentsPerDay.length; i++) {
            appointmentsPerDay[i].sort((a, b) => {
              if (a.numOfDays > b.numOfDays) {
                return -1;
              }
              if (a.numOfDays < b.numOfDays) {
                return 1;
              }
              return 0;
            });
          }
    
          //Finding maximum # of appointments during a day of the week
          var maxNum = 1;
          for (var i=0; i < appointmentsPerDay.length; i++) {
            const currentListLength = appointmentsPerDay[i].length;
            if (currentListLength > maxNum) {
              maxNum = currentListLength;
            }
          }
    
          //Populate the calendar with the maximum number of rows necessary to hold all appointments
          for (var i=0; i< this.weekLength; i++) {
            for (var j=0; j < maxNum; j++) {
              this.calendarData[i].push({
                appointment: null,
                assignment: null,
                numOfDays: null,
              });
            }
          }

          // Populate the calendar with the data
          for (var i=0; i< appointmentsPerDay.length; i++) {
            const tempList = [];
            for (var j=0; j< appointmentsPerDay[i].length; j++) {
              this.calendarData[i][j] = appointmentsPerDay[i][j];
              const unavailables = appointmentsPerDay[i][j].assignment.unavailableTechnicians;
              for (var k=0; k<unavailables.length; k++) {
                const matchingTechnician = tempList.find((value, index, list) => {
                  return value.id == list[index].id;
                });
                if (!matchingTechnician) {
                  tempList.push(unavailables[k]);
                }
              }
            }
            tempList.forEach((technician) => {
              this.awayList[i].push(technician);
            });
          }
          console.log(this.calendarData);
        });
    
      // while (apptsProcessed < appointmentsInRange.length && noErrors) {
      //   if (!currentlyProcessing) {
      //     currentlyProcessing = true;
      //     this.appointmentService.getAppointmentAssignments(appointmentsInRange[apptsProcessed].id).subscribe((response) => {
      //       apptsAndAssignments.push({
      //         appointment: appointmentsInRange[apptsProcessed],
      //         assignments: response,
      //       });
      //       apptsProcessed = apptsProcessed + 1;
      //       currentlyProcessing = false;
      //     }, (error) => {
      //       this.alertService.error("Something went wrong", false);
      //       console.log(error);
      //       noErrors = false;
      //     });
      //   }
      // }

      // const appointmentsPerDay = [];
      // for (var i=0; i<this.weekLength; i++) {
      //   appointmentsPerDay.push([]);
      // }

      // // Add information regarding the appointments to each of the 7 days of the week
      // for (var i=0; i < apptsAndAssignments.length; i++) {
      //   const tempAppt = apptsAndAssignments[i].appointment;
      //   const startDate = new Date(Date.parse(tempAppt.startDate.toString()) + this.dayLength);
      //   const endDate = new Date(Date.parse(tempAppt.endDate.toString()) + this.dayLength);
      //   var tempDate = startDate >= this.startOfWeek ? startDate : this.startOfWeek;
      //   const lengthOfAppointment = Math.ceil((endDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
      //   while (tempDate <= endDate && tempDate <= this.endOfWeek) {
      //     const dayIndex = Math.floor((tempDate.valueOf() - this.startOfWeek.valueOf()) / this.dayLength);
      //     const appointmentDayIndex = Math.ceil((tempDate.valueOf() - startDate.valueOf()) / this.dayLength);
      //     appointmentsPerDay[dayIndex].push({
      //       appointment: tempAppt,
      //       assignment: response[appointmentDayIndex],
      //       numOfDays: lengthOfAppointment,
      //     });
      //     tempDate = new Date(tempDate.valueOf() + this.dayLength);
      //   }
      // }

      // //Finding maximum # of appointments during a day of the week
      // var maxNum = 1;
      // for (var i=0; i < appointmentsPerDay.length; i++) {
      //   const currentListLength = appointmentsPerDay[i].length;
      //   if (currentListLength > maxNum) {
      //     maxNum = currentListLength;
      //   }
      // }

      // //Populate the calendar with the maximum number of rows necessary to hold all appointments
      // for (var i=0; i< this.weekLength; i++) {
      //   for (var j=0; j < maxNum; j++) {
      //     this.calendarData[i].push({
      //       appointment: null,
      //       assignment: null,
      //       numOfDays: null,
      //     });
      //   }
      // }

      // // Populate the calendar with the data
      // for (var i=0; i< appointmentsPerDay.length; i++) {
      //   for (var j=0; j< appointmentsPerDay[i].length; j++) {
      //     this.calendarData[i][j] = appointmentsPerDay[i][j];
      //   }
      // }
      // console.log(this.calendarData);
    }, () => {
      this.alertService.error("Appointments could not be loaded.", false);
    });
  }

  refreshCalendar() {
    this.calendarData = [];
    this.awayList = [];
    for (var i=0; i< this.weekLength; i++) {
      this.calendarData.push([]);
      this.awayList.push([]);
    }
  }

  getAssignmentsForAppointments(appointments: Appointment []) {
    return new Promise<{appointment: Appointment, assignments: Assignment []}[]>((resolve, reject) => {
      const returnList = [];
      if (appointments.length == 0) {
        resolve(returnList);
      }
      var resolved = false;
      for (var i = 0; i < appointments.length; i++) {
        const appointment = appointments[i];
        this.appointmentService.getAppointmentAssignments(appointment.id).subscribe((assignments) => {
          returnList.push({
            appointment: appointment,
            assignments: assignments,
          });
          if (returnList.length == appointments.length) {
            resolve(returnList);
          }
        }, (error) => {
          reject(error);
        })
      }
    });
  }

  updateAssignments(assignments: Assignment []) {
    if (assignments.length == 0) {
      return Promise.resolve(true);
    }
    return new Promise<boolean>((resolve, reject) => {
      for (var i=0; i < assignments.length; i++) {
        const assignment = assignments[i];
        this.assignmentService.updateAssignment(assignment.id, assignment).subscribe(() => {
          if (assignment.id == assignments[assignments.length-1].id) {
            resolve(true);
          }
        }, (error) => {
          console.log(error);
          reject(false);
        });
      }
    });
  }

  openInfoDialog(appointmentObject: {appointment: Appointment, assignment: Assignment, numOfDays: number, teams: Technician []}) {
    let dialogProperties = {
          data: {
            id: appointmentObject.appointment.id,
            title: `Customer: ${appointmentObject.appointment.customer.firstName} ${appointmentObject.appointment.customer.lastName}`,
            start: appointmentObject.appointment.startDate,
            end: appointmentObject.appointment.endDate,
            dayNumber: appointmentObject.assignment.dayNumber,
            numOfDays: appointmentObject.numOfDays
          },
        }
    return this.dialog.open(ViewInstallerDialogComponent, dialogProperties);
  }
}