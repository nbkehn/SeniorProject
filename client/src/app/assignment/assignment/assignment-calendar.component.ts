import { Component, OnInit } from '@angular/core';
import { Calendar, EventApi } from '@fullcalendar/core';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment-calendar.component.html',
  styleUrls: ['./assignment-calendar.component.less']
})
export class AssignmentCalendarComponent implements OnInit {

  private calendarObject: Calendar;


  setCalendar(cal: Calendar) {
    this.calendarObject = cal;
  }

  constructor() { }

  ngOnInit() {


    const inScopeSetCalendar = this.setCalendar.bind(this);

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
          right: 'dayGriddWeek,dayGridDay'
        },
        defaultView: 'dayGridWeek',


        // eventDrop: (dropEvent) => {
        //   inScopeUpdateSelectedEvent(dropEvent.event);
        // },
        // eventResize: (resizeEvent) => {
        //   inScopeUpdateSelectedEvent(resizeEvent.event);
        // },
        // eventClick: (clickEvent) => {
        //   inScopeEventClicked(clickEvent.event);
        // },
        // customButtons: {
        //   addApptButton: {
        //     text: "Add Appointment",
        //     click: inScopeOpenAddDialog,
        //   },
        //   editApptButton: {
        //     text: "Edit Appointment",
        //     click: inScopeOpenEditDialog,
        //   },
        //   deleteApptButton: {
        //     text: "Delete Appointment",
        //     click: inScopeOpenDeleteDialog,
        //   }
        // },
        footer: {
          left: '',
          center: 'addApptButton editApptButton deleteApptButton',
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

}
