/**
 * Angular component for the calendar
 *
 * @package calendar
 * @author Soumya Bagade
 */

import { Calendar } from '@fullcalendar/core';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';

import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';

@Component({
    selector: "app-calendar-list",
    templateUrl: "./calendar.html"
  })
  export class CalendarComponent implements OnInit {
  
    /**
     * Constructor for the CalendarComponent; does nothing
     */
    constructor(private dialog: MatDialog) {}

    /**
     * reloads the data on initialize of the page show the table
     */
    ngOnInit() {
        document.addEventListener('DOMContentLoaded', function() {
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
                  click: function () {
                  }
                },
                editApptButton: {
                  text: "Edit Appointment",
                  click: function () {
                    
                  }
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
  