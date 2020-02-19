/**
 * Angular component for the calendar
 *
 * @package calendar
 * @author Soumya Bagade
 * @author Renee Segda
 */

import { Calendar } from '@fullcalendar/core';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';

import { Component, OnInit } from "@angular/core";
import { MatDialog} from '@angular/material/dialog';
import { AddDialogComponent } from '../add-dialog/add-dialog.component';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';

@Component({
    selector: "app-calendar-list",
    templateUrl: "./calendar.html"
  })
  export class CalendarComponent implements OnInit {

    /**
     * Constructor for the CalendarComponent; does nothing
     */
    constructor(public dialog: MatDialog) {}

    openAddDialog(): void {
      const dialogRef = this.dialog.open(AddDialogComponent,{
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    }

    openEditDialog(): void {
      const dialogRef = this.dialog.open(EditDialogComponent, {});

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    }

    /**
     * reloads the data on initialize of the page show the table
     */
    ngOnInit() {
      /* The below function is necessary so the openAddDialog function can run in the correct scope
      (aka have the right value of "this"). For the same reason, we create an inScopeOpenEditDialog
      function as well.
      */
        const inScopeOpenAddDialog = this.openAddDialog.bind(this);
        const inScopeEditAddDialog = this.openEditDialog.bind(this);
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
            customButtons: {
              addApptButton: {
                text: "Add Appointment",
                click: inScopeOpenAddDialog,
              },
              editApptButton: {
                text: "Edit Appointment",
                click: inScopeEditAddDialog,
              },
              deleteApptButton: {
                text: "Delete Appointment",
                click: function () {
                  
                }
              }
            },
            footer: {
              left: '',
              center: 'addApptButton editApptButton deleteApptButton',
              right: '',
            },
            defaultDate: '2018-01-12',
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true, // allow the selection of multiple dates (for scheduling)

            //TODO: later delete this dummy data
            events: [
              {
                title: 'All Day Event',
                start: '2018-01-01',
              },
              {
                title: 'Long Event',
                start: '2018-01-07',
                end: '2018-01-10'
              },
              {
                title: 'Semi-Long Event',
                start: '2018-01-08',
                end: '2018-01-09'
              },
            ]
          });
        
          // generate the calendar
          calendar.render();
        });
    }
  }
  