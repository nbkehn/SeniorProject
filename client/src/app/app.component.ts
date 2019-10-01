/**
 * Base angular component
 *
 * @author Noah Trimble
 */
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  // Landing page's title
  title = 'BCO Installation Scheduler';
}
