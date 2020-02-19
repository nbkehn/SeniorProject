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
import { AbstractFormDialogComponent } from '../abstract-dialog/abstract-form-dialog-component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';
import { ComponentType } from '@angular/cdk/portal';

@Component({
    selector: "app-calendar-list",
    templateUrl: "./calendar.html"
  })
  export class CalendarComponent implements OnInit {

    /**
     * Constructor for the CalendarComponent; does nothing
     */
    constructor(public dialog: MatDialog) {}

    openDialog(dialogComponentClass: ComponentType<AbstractFormDialogComponent>): void {
      /* The parameter must be indexed to retrieve the class which was passed as an argument
      (discovered through debugging in Google Chrome source) */
      const dialogRef = this.dialog.open(dialogComponentClass[0], {});
      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      })
    }

    /**
     * reloads the data on initialize of the page show the table
     */
    ngOnInit() {
      /* The below function is necessary so the openAddDialog function can run in the correct scope
      (aka have the right value of "this"). For the same reason, we create inScopeOpenEditDialog
      and inScopeOpenDeleteDialog functions as well.
      */
        const inScopeOpenAddDialog = this.openDialog.bind(this, [AddDialogComponent]);
        const inScopeOpenEditDialog = this.openDialog.bind(this, [EditDialogComponent]);
        const inScopeOpenDeleteDialog = this.openDialog.bind(this, [DeleteDialogComponent]);
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
  