/**
 * Angular component for alerts
 *
 * @package alert
 * @source https://github.com/cornflourblue/angular2-alert-notifications
 * @author Noah Trimble
 */
import { Component, OnInit, Input } from '@angular/core';

import { Alert, AlertType } from '../alert';
import { AlertService } from '../alert.service';

@Component({
  selector: 'alert',
  templateUrl: 'alert.component.html'
})

export class AlertComponent implements OnInit {
  @Input() id: string;

  // List of alerts
  alerts: Alert[] = [];

  /**
   * AlertService constructor
   * @param alertService
   */
  constructor(private alertService: AlertService) { }

  /**
   * Initializes alert component
   */
  ngOnInit() {
    this.alertService.getAlert(this.id).subscribe((alert: Alert) => {
      if (!alert.message) {
        // clear alerts when an empty alert is received
        this.alerts = [];
        return;
      }

      // add alert to array
      this.alerts.push(alert);
    });
  }

  /**
   * Remove alert
   * @param alert
   */
  removeAlert(alert: Alert) {
    this.alerts = this.alerts.filter(x => x !== alert);
  }

  /**
   * Gets css class of alert depending on type
   * @param alert
   */
  cssClass(alert: Alert) {
    if (!alert) {
      return;
    }

    // return css class based on alert type
    switch (alert.type) {
      case AlertType.Success:
        return 'alert alert-success';
      case AlertType.Error:
        return 'alert alert-danger';
      case AlertType.Info:
        return 'alert alert-info';
      case AlertType.Warning:
        return 'alert alert-warning';
    }
  }
}
