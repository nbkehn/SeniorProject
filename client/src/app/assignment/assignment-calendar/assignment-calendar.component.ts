import { Component, OnInit } from '@angular/core';

import { CdkDragDrop, transferArrayItem, copyArrayItem } from '@angular/cdk/drag-drop';
import { Technician } from 'src/app/technician/technician';
import { TechnicianService } from 'src/app/technician/technician.service';
import { AlertService } from 'src/app/alert/alert.service';
import { AppointmentService } from 'src/app/appointment/appointment.service';
import { Appointment } from 'src/app/appointment/appointment';
import { MatDialog } from '@angular/material/dialog';
import { ViewInstallerDialogComponent } from 'src/app/view-installer-dialog/view-installer-dialog.component';
import { getRelevantEvents } from '@fullcalendar/core';
import { AssignmentService } from '../assignment.service';
import { Assignment } from '../assignment';

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
    if (!event.container.data.includes(data) && currentApptObject.appointment) {
      if (event.container.id != "listOfTeams" && event.previousContainer.id != "listOfTeams") {
        transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
      } else {
        if (event.previousContainer.id == "listOfTeams") {
          copyArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
          this.createAssignment(currentApptObject.appointment, 1, technician);
          if (currentApptObject.numOfDays > 1 && currentApptObject.dayNumber == 1 && dayIndex != 6) {
            var tempIndex = dayIndex + 1;
            while (tempIndex <= 6) {
              for (var i=0; i<this.calendarData[tempIndex].length; i++) {
                const tempObject = this.calendarData[tempIndex][i];
                if (tempObject.appointment.id == currentApptObject.appointment.id) {
                  tempObject.teams.push(data);
                }
              }
              tempIndex = tempIndex + 1;
            }
          }
        }
      }
    }
    console.log(this.calendarData);
  }

  markAsAway(event: CdkDragDrop<string[]>) {
    if (event.container.id != "listOfTeams" && event.previousContainer.id != "listOfTeams") {
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    } else {
      copyArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    }
  }

  toss(event: CdkDragDrop<string[]>) {
    transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    event.container.removeItem(event.item);
    this.listForDeletedItems = [];
  }


  createAssignment(appt: Appointment, dayNumber: number, technician: Technician) {
    if (appt.assignments) {
      const relevantAssignment = appt.assignments.filter((assignment) => {
        assignment.dayNumber == dayNumber;
      });
      if (relevantAssignment.length >= 1) {
        relevantAssignment[0].technicians.push(technician);
        this.assignmentService.updateAssignment(relevantAssignment[0].id, relevantAssignment[0]).subscribe(() => {
          this.alertService.success("Assignment updated successfully", false);
          this.appointmentService.updateAppointment(appt.id, appt).subscribe(() => {
            this.alertService.success("Appointment updated successfully", false);
          }, () => {
            this.alertService.error("Appointment could not be updated", false);
          });
        }, () => {
          this.alertService.error("Assignment could not be updated", false);
        });
      }
    } else {
      appt.assignments = [];
      const assignment = new Assignment(dayNumber);
      assignment.technicians.push(technician);
      this.assignmentService.createAssignment(assignment).subscribe((response) => {
        this.alertService.success("Assignment created successfully", false);
        assignment.id = response.id;
        appt.assignments.push(response);
        this.appointmentService.updateAppointment(appt.id, appt).subscribe(() => {
          this.alertService.success("Appointment updated successfully", false);
        }, (error) => {
          this.alertService.error("Appointment could not be updated", false);
          console.log(error);
        });
      }, () => {
        this.alertService.error("Assignment could not be created", false);
      });
    }
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
      const appointmentsPerDay = [];
      for (var i=0; i<this.weekLength; i++) {
        appointmentsPerDay.push([]);
      }

      // Calculate length of each appointment and store that information
      var apptToApptLength = [];
      for (var i=0; i<appointmentsInRange.length; i++) {
        const tempAppt = appointmentsInRange[i];
        const startDate = new Date(Date.parse(tempAppt.startDate.toString()) + this.dayLength);
        const endDate = new Date(Date.parse(tempAppt.endDate.toString()) + this.dayLength);
        const lengthOfAppointment = Math.ceil((endDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
        apptToApptLength.push({appt: tempAppt, length: lengthOfAppointment});
      }

      //Order the appointments from longest to shortest
      apptToApptLength = apptToApptLength.sort((a: {appt: Appointment, length: number}, b: {appt: Appointment, length: number}) => {
        if (a.length < b.length) {
          return 1;
        }
        if (a.length > b.length) {
          return -1;
        }
        return 0;
      });

      // Add information regarding the appointments to each of the 7 days of the week
      for (var i=0; i < apptToApptLength.length; i++) {
        const tempAppt = apptToApptLength[i].appt;
        const startDate = new Date(Date.parse(tempAppt.startDate.toString()) + this.dayLength);
        const endDate = new Date(Date.parse(tempAppt.endDate.toString()) + this.dayLength);
        var tempDate = startDate >= this.startOfWeek ? startDate : this.startOfWeek;
        while (tempDate <= endDate && tempDate <= this.endOfWeek) {
              const dayIndex = Math.floor((tempDate.valueOf() - this.startOfWeek.valueOf()) / this.dayLength);
              const appointmentDayNumber = Math.ceil((tempDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
              appointmentsPerDay[dayIndex].push({
                appointment: tempAppt,
                dayNumber: appointmentDayNumber,
                numOfDays: apptToApptLength[i].length,
                teams: [],
              });
              tempDate = new Date(tempDate.valueOf() + this.dayLength);
        }
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
            dayNumber: null,
            numOfDays: null,
            teams: [],
          });
        }
      }

      // Populate the calendar with the data
      for (var i=0; i< appointmentsPerDay.length; i++) {
        for (var j=0; j< appointmentsPerDay[i].length; j++) {
          this.calendarData[i][j] = appointmentsPerDay[i][j];
        }
      }
      console.log(this.calendarData);
    }, () => {
      this.alertService.error("Appointments could not be loaded.", false);
    })
  }

  refreshCalendar() {
    this.calendarData = [];
    this.awayList = [];
    for (var i=0; i< this.weekLength; i++) {
      this.calendarData.push([]);
      this.awayList.push([]);
    }
  }

  openInfoDialog(appointmentObject: {appointment: Appointment, dayNumber: number, numOfDays: number, teams: Technician []}) {
    let dialogProperties = {
          data: {
            id: appointmentObject.appointment.id,
            title: `Customer: ${appointmentObject.appointment.customer.firstName} ${appointmentObject.appointment.customer.lastName}`,
            start: appointmentObject.appointment.startDate,
            end: appointmentObject.appointment.endDate,
            dayNumber: appointmentObject.dayNumber,
            numOfDays: appointmentObject.numOfDays
          },
        }
    return this.dialog.open(ViewInstallerDialogComponent, dialogProperties);
  }
}