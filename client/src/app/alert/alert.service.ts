/**
 * Angular service for creating alerts
 *
 * @package alert
 * @source https://github.com/cornflourblue/angular2-alert-notifications
 * @modifiedBy Noah Trimble
 */
import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/filter';

import { Alert, AlertType } from './alert';

@Injectable()
export class AlertService {
  private subject = new Subject<Alert>();
  private keepAfterRouteChange = true;

  /**
   * AlertService Constructor
   * @param router
   */
  constructor(private router: Router) {
    // clear alert messages on route change unless 'keepAfterRouteChange' flag is true
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        if (this.keepAfterRouteChange) {
          // only keep for a single route change
          this.keepAfterRouteChange = false;
        } else {
          // clear alert messages
          this.clear();
        }
      }
    });
  }

  /**
   * Subscribe to alerts
   * @param alertId
   */
  getAlert(alertId?: string): Observable<any> {
    return this.subject.asObservable().filter((x: Alert) => x && x.alertId === alertId);
  }

  /**
   * Create and add success alert message
   * @param message
   * @param keepAfterRouteChange
   */
  success(message: string, keepAfterRouteChange: boolean) {
    this.alert(new Alert({ message: message, type: AlertType.Success, keepAfterRouteChange: keepAfterRouteChange }));
  }

  /**
   * Create and add error alert message
   * @param message
   * @param keepAfterRouteChange
   */
  error(message: string, keepAfterRouteChange: boolean) {
    this.alert(new Alert({ message: message, type: AlertType.Error, keepAfterRouteChange: keepAfterRouteChange }));
  }

  /**
   * Create and add info alert message
   * @param message
   * @param keepAfterRouteChange
   */
  info(message: string, keepAfterRouteChange: boolean) {
    this.alert(new Alert({ message: message, type: AlertType.Info, keepAfterRouteChange: keepAfterRouteChange }));
  }

  /**
   * Create and add warning alert message
   * @param message
   * @param keepAfterRouteChange
   */
  warn(message: string, keepAfterRouteChange: boolean) {
    this.alert(new Alert({ message: message, type: AlertType.Warning, keepAfterRouteChange: keepAfterRouteChange }));
  }

  /**
   * Add alert message
   * @param alert
   */
  alert(alert: Alert) {
    this.keepAfterRouteChange = alert.keepAfterRouteChange;
    this.subject.next(alert);
  }

  /**
   * Clear alerts
   * @param alertId
   */
  clear(alertId?: string) {
    this.subject.next(new Alert({ alertId }));
  }
}
