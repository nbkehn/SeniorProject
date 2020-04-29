import { Component, OnInit } from '@angular/core';

import { CdkDragDrop, transferArrayItem, copyArrayItem } from '@angular/cdk/drag-drop';
import { Technician } from 'src/app/technician/technician';
import { TechnicianService } from 'src/app/technician/technician.service';
import { AlertService } from 'src/app/alert/alert.service';
import { AppointmentService } from 'src/app/appointment/appointment.service';
import { Appointment } from 'src/app/appointment/appointment';

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

  constructor(private technicianService: TechnicianService, private appointmentService: AppointmentService, private alertService: AlertService) {}

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

  drop(event: CdkDragDrop<string[]>, dayIndex: number, slotIndex: number = 0) {
    const data = event.previousContainer.data[event.previousIndex];
    const currentApptObject = this.calendarData[dayIndex][slotIndex];
    if (!event.container.data.includes(data) && currentApptObject.appointment) {
      if (event.container.id != "listOfTeams" && event.previousContainer.id != "listOfTeams") {
        transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
      } else {
        if (event.previousContainer.id == "listOfTeams") {
          copyArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
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

  toss(event: CdkDragDrop<string[]>) {
    transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    event.container.removeItem(event.item);
    this.listForDeletedItems = [];
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
      for (var i=0; i<appointmentsInRange.length; i++) {
        const tempAppt = appointmentsInRange[i];
        const startDate = new Date(Date.parse(tempAppt.startDate.toString()) + this.dayLength);
        const endDate = new Date(Date.parse(tempAppt.endDate.toString()) + this.dayLength);
        var tempDate = startDate >= this.startOfWeek ? startDate : this.startOfWeek;
        const lengthOfAppointment = Math.ceil((endDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
        while (tempDate <= endDate && tempDate <= this.endOfWeek) {
          const dayIndex = Math.floor((tempDate.valueOf() - this.startOfWeek.valueOf()) / this.dayLength);
          const appointmentDayNumber = Math.ceil((tempDate.valueOf() - startDate.valueOf()) / this.dayLength) + 1;
          appointmentsPerDay[dayIndex].push({
            appointment: tempAppt,
            dayNumber: appointmentDayNumber,
            numOfDays: lengthOfAppointment,
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

}