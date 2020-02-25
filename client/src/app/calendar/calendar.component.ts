/**
 * Angular component for the calendar
 *
 * @package calendar
 * @author Soumya Bagade
 * @author Renee Segda
 */

import { Calendar, EventApi } from '@fullcalendar/core';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';

import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogRef} from '@angular/material/dialog';
import { AddDialogComponent } from '../add-dialog/add-dialog.component';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { AbstractFormDialogComponent } from '../abstract-dialog/abstract-form-dialog-component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { ComponentType } from '@angular/cdk/portal';

@Component({
    selector: "app-calendar-list",
    templateUrl: "./calendar.html"
  })
  export class CalendarComponent implements OnInit {

    private calendarObject: Calendar;

    private selectedEvent: {id: number, title: string, start: Date, end: Date}

    private static nextId: number = 3;

    setCalendar(cal: Calendar) {
      this.calendarObject = cal;
    }

    getCalendar() {
      return this.calendarObject;
    }

    setSelectedEvent(event: {id: number, title: string, start: Date, end: Date}) {
      this.selectedEvent = event;
    }

    getSelectedEvent(): {title: string, start: Date, end: Date} {
      return this.selectedEvent;
    }

    /**
     * Constructor for the CalendarComponent; does nothing
     */
    constructor(public dialog: MatDialog) {
    }

    private getYesterday(date: Date) {
      const dayLength = new Date("2000-01-02").valueOf() - new Date("2000-01-01").valueOf();
      return new Date(date.valueOf() - dayLength);
    }

    private getTomorrow(date: Date) {
      const dayLength = new Date("2000-01-02").valueOf() - new Date("2000-01-01").valueOf();
      return new Date(date.valueOf() + dayLength);
    }

    private openDialog(dialogComponentClass: ComponentType<AbstractFormDialogComponent>, eventSelectionRequired: boolean = false): MatDialogRef<unknown, any> {
      const dialogWidthProperties = {width: '30%'};
      let dialogProperties;
      if (eventSelectionRequired) {
        if (!this.selectedEvent) {
          alert("Please select an appointment to process");
          return null;
        } else {
          dialogProperties = {
            ...dialogWidthProperties,
            data: {
              id: this.selectedEvent.id,
              title: this.selectedEvent.title,
              start: this.selectedEvent.start,
              end: this.selectedEvent.end ? this.getYesterday(this.selectedEvent.end) : null
            },
          }
        }
      } else {
        dialogProperties = dialogWidthProperties;
      }
      return this.dialog.open(dialogComponentClass, dialogProperties);
    }

    openAddDialog() {
      const dialogRef = this.openDialog(AddDialogComponent);
      dialogRef.afterClosed().subscribe(returnedValue => {
        const appt = {
          id: CalendarComponent.nextId,
          title: `Customer: ${returnedValue.firstName} ${returnedValue.lastName}`,
          start: returnedValue.start ? returnedValue.start.format("YYYY-MM-DD") : (new Date()).toDateString(),
          end: returnedValue.end ? returnedValue.end.add(1, "days").format("YYYY-MM-DD") : "",
        }
        this.calendarObject.addEvent(appt);
        CalendarComponent.nextId += 1;
        console.log(this.calendarObject.getEvents());
      })
    }

    openEditDialog() {
      const dialogRef = this.openDialog(EditDialogComponent, true);
      if (dialogRef) {
        dialogRef.afterClosed().subscribe(returnedValue => {
          const appt = {
            id: returnedValue.id,
            title: returnedValue.firstName + " " + returnedValue.lastName,
            start: new Date(returnedValue.start) || new Date(),
            end: this.getTomorrow(returnedValue.end) || "",
          }
          console.log(this.calendarObject.getEvents());
          const editedEvent = this.calendarObject.getEventById(appt.id);
          editedEvent.setStart(appt.start);
          editedEvent.setEnd(appt.end);
        });
      }
    }

    openDeleteDialog() {
      const dialogRef = this.openDialog(DeleteDialogComponent, true);
      if (dialogRef) {
        dialogRef.afterClosed().subscribe(returnedValue => {
          if (returnedValue.deleted) {
            const deleteEvent = this.calendarObject.getEvents().find((event) => {
              return event.title == `Customer: ${returnedValue.firstName} ${returnedValue.lastName}`;
            })
            deleteEvent.remove();
            this.selectedEvent = null;
            console.log(returnedValue);
          }
        });
    }
    }

    updateSelectedEvent(newEvent: EventApi) {
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
        const inScopeOpenAddDialog = this.openAddDialog.bind(this);
        const inScopeOpenEditDialog = this.openEditDialog.bind(this);
        const inScopeOpenDeleteDialog = this.openDeleteDialog.bind(this);
        const inScopeSetCalendar = this.setCalendar.bind(this);
        const inScopeGetCalendar = this.getCalendar.bind(this);
        const inScopeSetSelectedEvent = this.setSelectedEvent.bind(this);
        const inScopeGetSelectedEvent = this.getSelectedEvent.bind(this);
        const inScopeUpdateSelectedEvent = this.updateSelectedEvent.bind(this);
        document.addEventListener('DOMContentLoaded', function(event: Event) {
          // initialize the calendar element on page
          let calendarEl: HTMLElement = document.getElementById('calendar')!;
          // initialize the details for the calendar
          let calendar = new Calendar(calendarEl, {
            plugins: [ interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin ],
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
              inScopeUpdateSelectedEvent(clickEvent.event);
            },
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
              }
            },
            footer: {
              left: '',
              center: 'addApptButton editApptButton deleteApptButton',
              right: '',
            },
            defaultDate: new Date(),
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true, // allow the selection of multiple dates (for scheduling)

            //TODO: later delete this dummy data
            events: [
              {
                id: 0,
                title: "Customer: Renee Segda",
                start: "2020-02-16"
              },
              {
                id: 1,
                title: "Customer: Nico Kehn",
                start: "2020-02-16",
                end: "2020-02-18"
              },
              {
                id: 2,
                title: "Customer: Koby Brown",
                start: "2020-02-25",
                end: "2020-02-28"
              }
            ],
            eventBackgroundColor: '#198E97',
            eventTextColor: '#FFFFFF',
            eventBorderColor: '#198E97',
          });
        
          // generate the calendar
          calendar.render();
          inScopeSetCalendar(calendar);
        });
    }
  }
  