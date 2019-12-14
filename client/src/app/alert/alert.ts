/**
 * Defines an alert
 *
 * @package alert
 * @source https://github.com/cornflourblue/angular2-alert-notifications
 */
export class Alert {
  type: AlertType;
  message: string;
  alertId: string;
  keepAfterRouteChange: boolean;

  constructor(init?:Partial<Alert>) {
    Object.assign(this, init);
  }
}

export enum AlertType {
  Success,
  Error,
  Info,
  Warning
}
