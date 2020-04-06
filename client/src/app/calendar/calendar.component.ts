/**
 * Angular component for the calendar
 *
 * @package calendar
 * @author Soumya Bagade
 * @author Renee Segda
 */

import { Calendar, EventApi } from '@fullcalendar/core';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';



import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AddDialogComponent } from '../add-dialog/add-dialog.component';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { AbstractFormDialogComponent } from '../abstract-dialog/abstract-form-dialog-component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { ComponentType } from '@angular/cdk/portal';
import { AppointmentService } from '../appointment/appointment.service';
import { Appointment } from '../appointment/appointment';
import {  BehaviorSubject } from 'rxjs';
import { AlertService } from '../alert/alert.service';
import { OTWDialogComponent } from '../otw-dialog/otw-dialog.component';
import { ViewDialogComponent } from '../view-dialog/view-dialog.component';


@Component({
  selector: "app-calendar-list",
  templateUrl: "./calendar.html"
})
export class CalendarComponent implements OnInit {

  private calendarObject: Calendar;

  private appointments  = new BehaviorSubject<Appointment[]>([]);
  private appStore : { apps : Appointment[]} = {apps : []};
  readonly apps = this.appointments.asObservable();


  savedAppointment: BehaviorSubject<Appointment> = new BehaviorSubject<Appointment>(new Appointment);

  private selectedEvent: { id: number, title: string, start: Date, end: Date }

  private static nextId: number;

  /**
   * Used to detect when the user double clicks since the FullCalendar
   * doesn't check for double clicks innately. According to Wikipedia
   * (and some experiments I did on my own), the time between clicks for
   * a double click is considered <= 500 ms.
   */
  private timeOfLastClick: number = 0;

  appointment: Appointment;



  setCalendar(cal: Calendar) {
    this.calendarObject = cal;
  }

  getCalendar() {
    return this.calendarObject;
  }

  setSelectedEvent(event: { id: number, title: string, start: Date, end: Date }) {
    this.selectedEvent = event;
  }

  getSelectedEvent(): { title: string, start: Date, end: Date } {
    return this.selectedEvent;
  }

  getTimeOfLastClick() {
    return this.timeOfLastClick;
  }

  setTimeOfLastClick(time: number) {
    this.timeOfLastClick = time;
  }

  /**
   * Constructor for the CalendarComponent; does nothing
   */
  constructor(public dialog: MatDialog, private appointmentService: AppointmentService, private alertService: AlertService) { }

  private getYesterday(date: Date) {
    const dayLength = new Date("2000-01-02").valueOf() - new Date("2000-01-01").valueOf();
    return new Date(date.valueOf() - dayLength);
  }

  private getTomorrow(date: Date) {
    const dayLength = new Date("2000-01-02").valueOf() - new Date("2000-01-01").valueOf();
    return new Date(date.valueOf() + dayLength);
  }

  private dateObjectToString(date: Date) {
    const dateString = date.toISOString();
    const tIndex = dateString.indexOf("T");
    return dateString.substr(0, tIndex);

  }

  private openDialog(dialogComponentClass: ComponentType<AbstractFormDialogComponent>, eventSelectionRequired: boolean = false): MatDialogRef<unknown, any> {
    let dialogProperties;
    if (eventSelectionRequired) {
      if (!this.selectedEvent) {
        alert("Please select an appointment to process");
        return null;
      } else {
        dialogProperties = {
          data: {
            id: this.selectedEvent.id,
            title: this.selectedEvent.title,
            start: this.selectedEvent.start,
            end: this.selectedEvent.end ? this.getYesterday(this.selectedEvent.end) : null
          },
        }
      }
    }
    return this.dialog.open(dialogComponentClass, dialogProperties);
  }

  openAddDialog() {
    const dialogRef = this.openDialog(AddDialogComponent);
    dialogRef.afterClosed().subscribe(returnedValue => {
      if (typeof returnedValue != "boolean") {
        let newEnd = returnedValue.end;
        this.appointment = new Appointment();
          this.appointment.startDate = returnedValue.start;
          this.appointment.endDate = returnedValue.end;
          this.appointment.customer = returnedValue.customer;
          this.appointment.technicians =  returnedValue.technicians;
          this.appointment.rsa = returnedValue.rsa;
          this.appointment.flooring = returnedValue.flooring;
          console.log(this.appointment.endDate);
        
          this.save();
      }
          })
  }

  openEditDialog() {
    const dialogRef = this.openDialog(EditDialogComponent, true);
    if (dialogRef) {
      dialogRef.afterClosed().subscribe(returnedValue => {
        if (typeof returnedValue != "boolean") {
          const appt = {
            id: this.selectedEvent.id,
            title: `Customer: ${returnedValue.customer.firstName} ${returnedValue.customer.lastName}`,
            start: returnedValue.start,
            end: returnedValue.end.to,
          }
  
          //update event on the calendar
          const editedEvent = this.calendarObject.getEventById(appt.id.toString());
          let newEnd = this.getTomorrow(returnedValue.end);
          editedEvent.setStart(returnedValue.start);
          editedEvent.setEnd(newEnd);
          this.updateSelectedEvent(editedEvent);

          //update appointment in the database
          this.appointment = new Appointment();
          this.appointment.id = this.selectedEvent.id;
          this.appointment.startDate = appt.start;
          this.appointment.endDate = newEnd;
          this.appointment.customer = returnedValue.customer;
          this.appointment.technicians =  returnedValue.technicians;
          this.appointment.rsa = returnedValue.rsa;
          this.appointment.flooring = returnedValue.flooring;
          this.update(this.selectedEvent.id);
        }
      });
    }
  }

  openDeleteDialog() {
    const dialogRef = this.openDialog(DeleteDialogComponent, true);
    if (dialogRef) {
      dialogRef.afterClosed().subscribe(returnedValue => {
        if (typeof returnedValue != "boolean") {
          if (returnedValue.deleted) {
            const deleteEvent = this.calendarObject.getEventById(this.selectedEvent.id.toString());
            deleteEvent.remove();
            this.delete(this.selectedEvent.id);
            this.selectedEvent = null;
            console.log(returnedValue);
          }
        }
      });
    }
  }

  /**
   * Since OTW messages don't require editing an appointment, this
   * dialog is its own entity apart from the abstract dialog class
   * AbstractFormDialogComponent. Therefore,
   */
  openOTWDialog() {
    if (!this.selectedEvent) {
      alert("Please select an appointment to process");
      return null;
    } else {
      const dialogRef = this.dialog.open(OTWDialogComponent, {data: {
        id: this.selectedEvent.id,
        title: this.selectedEvent.title,
        start: this.selectedEvent.start,
        end: this.selectedEvent.end ? this.getYesterday(this.selectedEvent.end) : null
      }});
      if (dialogRef) {
        dialogRef.afterClosed().subscribe(returnedValue => {
          console.log(returnedValue)});
      }
    }
  }

  openViewDialog() {
    this.openDialog(ViewDialogComponent, true);
  }

  updateSelectedEvent(newEvent: EventApi) {
    this.updateClickedEvent(newEvent);
    
    //update the event in the backend
    this.appointmentService.getAppointment(this.selectedEvent.id).subscribe(data => {
      this.appointment = data;
      this.appointment.startDate = this.selectedEvent.start;
      this.appointment.endDate = this.getYesterday(this.selectedEvent.end);
      this.update(this.selectedEvent.id);
    });

    
  }

  updateClickedEvent(newEvent: EventApi) {
    const newEventShaped = {
      id: Number(newEvent.id),
      title: newEvent.title,
      start: newEvent.start,
      end: newEvent.end
    };

    if (this.getSelectedEvent()) {
      const currentEvent = this.getCalendar().getEventById(String(this.selectedEvent.id));
      currentEvent.setProp("backgroundColor", "#198E97");
      currentEvent.setProp("textColor", "#FFFFFF");
      currentEvent.setProp("borderColor", "#198E97");
    }

    this.setSelectedEvent(newEventShaped);
    newEvent.setProp("backgroundColor", "#FFAA1D");
    newEvent.setProp("textColor", "");
    newEvent.setProp("borderColor", "#FFAA1D")
  }

  /**
 * reloads the data with the most updated list of appointments from the database
 * calls from the appointment service so that it makes the DB call
 */
  reloadData() {
    this.appointmentService.getAppointmentsList()
    .subscribe( data => {
        this.alertService.success('Appointments loaded successfully.', true);
        if (data.length == 0) {
          console.log("There are no appointments");
        }
        this.appStore.apps = data;
        this.appointments.next(Object.assign({}, this.appStore).apps);

      },
      error => {
        this.alertService.error('Appointments could not be loaded.', false);
      });

      this.apps.forEach(element => {
        element.forEach(data => {
          const newEnd = this.getTomorrow(new Date(data.endDate));
          const newEndString = this.dateObjectToString(newEnd);
          var event = {id: data.id, title: "Customer: " + data.customer.firstName + " " + data.customer.lastName, start: data.startDate, end: newEndString, allDay: true};
          this.calendarObject.addEvent(event);
        });
      });
  }


  /* Trying to get the added event to show up in the calendar but the event object returned isn't being parsed correctly.
  */


  /**
   * reloads the data on initialize of the page show the table
   */
  ngOnInit() {
    /* The below function is necessary so the openAddDialog function can run in the correct scope
    (aka have the right value of "this"). For the same reason, we create inScopeOpenEditDialog
    and inScopeOpenDeleteDialog functions as well.
    */
    this.reloadData();
    const inScopeOpenAddDialog = this.openAddDialog.bind(this);
    const inScopeOpenEditDialog = this.openEditDialog.bind(this);
    const inScopeOpenDeleteDialog = this.openDeleteDialog.bind(this);
    const inScopeOpenOTWDialog = this.openOTWDialog.bind(this);
    const inScopeOpenViewDialog = this.openViewDialog.bind(this);
    const inScopeSetCalendar = this.setCalendar.bind(this);
    const inScopeUpdateSelectedEvent = this.updateSelectedEvent.bind(this);
    const inScopeEventClicked = this.updateClickedEvent.bind(this);
    const inScopeGetTimeOfLastClick = this.getTimeOfLastClick.bind(this);
    const inScopeSetTimeOfLastClick = this.setTimeOfLastClick.bind(this);
    const inScopeGetSelectedEvent = this.getSelectedEvent.bind(this);
    document.addEventListener('DOMContentLoaded', function (event: Event) {
      // initialize the calendar element on page
      let calendarEl: HTMLElement = document.getElementById('calendar')!;
      // initialize the details for the calendar
      let calendar = new Calendar(calendarEl, {
        plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin],
        droppable: true,
        height: "auto",
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,dayGridWeek,dayGridDay'
        },


        eventDrop: (dropEvent) => {
          inScopeUpdateSelectedEvent(dropEvent.event);
        },
        eventResize: (resizeEvent) => {
          inScopeUpdateSelectedEvent(resizeEvent.event);
        },
        eventClick: (clickEvent) => {
          const currentTime = Date.now();
          const selectedEvent = inScopeGetSelectedEvent();
          const isSameEvent = selectedEvent && selectedEvent.id == clickEvent.event.id;
          inScopeEventClicked(clickEvent.event);
          if (isSameEvent && currentTime - inScopeGetTimeOfLastClick() < 500) {
            inScopeOpenViewDialog();
          }
          inScopeSetTimeOfLastClick(currentTime);
        },
        aspectRatio: 3,
        customButtons: {
          addApptButton: {
            text: "Add Appointment",
            click: inScopeOpenAddDialog,
          },
          editApptButton: {
            text: "Edit Appointment",
            click: inScopeOpenEditDialog,
          },
          deleteApptButton: {
            text: "Delete Appointment",
            click: inScopeOpenDeleteDialog,
          },
          sendOTWMessageButton: {
            text: "Send OTW Message",
            click: inScopeOpenOTWDialog,
          }
        },
        footer: {
          left: '',
          center: 'addApptButton editApptButton deleteApptButton sendOTWMessageButton',
          right: '',
        },
        defaultDate: new Date(),
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: false, // allow "more" link when too many events
        selectable: true, // allow the selection of multiple dates (for scheduling)
        displayEventTime: false,

        //TODO: later delete this dummy data
        eventBackgroundColor: '#198E97',
        eventTextColor: '#FFFFFF',
        eventBorderColor: '#198E97',
      });

      // generate the calendar
      calendar.render();
      inScopeSetCalendar(calendar);
    });
  }
  save() {
    // saves the appointment object to the database -- if the appointment hasn't been created before, it saves as a new entry
    // if the appointment has been created before, it updates the appointment
    console.log(this.appointment);
    let response = this.appointmentService.createAppointment(this.appointment);
    return response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Appointment saved successfully.', true);
        console.log("Successfully saved appointment");
        if (data.id) {
          const appt = {
            id: data.id,
            title: `Customer: ${this.appointment.customer.firstName} ${this.appointment.customer.lastName}`,
            start: this.appointment.startDate ? this.dateObjectToString(this.appointment.startDate) : this.dateObjectToString(new Date()),
            end: this.appointment.endDate ? this.dateObjectToString(this.getTomorrow(this.appointment.endDate)) : this.dateObjectToString(this.getTomorrow(this.appointment.startDate)),
          }
          this.calendarObject.addEvent(appt);
          CalendarComponent.nextId += 1;
          console.log(this.calendarObject.getEvents());
          this.updateClickedEvent(this.calendarObject.getEventById(String(appt.id)));
        }
        return data;
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The appointment could not be saved.', false);
      });
  }

  update(id: number) {
    let response = this.appointmentService.updateAppointment(id, this.appointment);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Appointment updated successfully.', true);
        console.log(data);
      },
      error => {
        // Display error message on error and remain in form
        //this.alertService.error('The appointment could not be updated.', false);
        console.log("Unsuccessfully updated appointment");
      });
  }

  delete(id : number) {
    let response = this.appointmentService.deleteAppointment(id);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Appointment deleted successfully.', true);
        console.log("Successfully deleted appointment");
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The appointment could not be deleted.', false);
        console.log("Unsuccessfully deleted appointment");
      });
  }
}

