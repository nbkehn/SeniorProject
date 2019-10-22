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

@Component({
    selector: "app-calendar-list",
    templateUrl: "./calendar.html"
  })
  export class CalendarComponent implements OnInit {
  
    /**
     * Constructor for the CalendarComponent; does nothing
     */
    constructor() {}
  
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
              header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
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
                  id: 999,
                  title: 'Repeating Event',
                  start: '2018-01-09T16:00:00'
                },
                {
                  id: 999,
                  title: 'Repeating Event',
                  start: '2018-01-16T16:00:00'
                },
                {
                  title: 'Conference',
                  start: '2018-01-11',
                  end: '2018-01-13'
                },
                {
                  title: 'Meeting',
                  start: '2018-01-12T10:30:00',
                  end: '2018-01-12T12:30:00'
                },
                {
                  title: 'Lunch',
                  start: '2018-01-12T12:00:00'
                },
                {
                  title: 'Meeting',
                  start: '2018-01-12T14:30:00'
                },
                {
                  title: 'Happy Hour',
                  start: '2018-01-12T17:30:00'
                },
                {
                  title: 'Dinner',
                  start: '2018-01-12T20:00:00'
                },
                {
                  title: 'Birthday Party',
                  start: '2018-01-13T07:00:00'
                },
                {
                  title: 'Click for Google',
                  url: 'http://google.com/',
                  start: '2018-01-28'
                }
              ]
            });
          
            // generate the calendar
            calendar.render();
          });
    }
  }
  