import { Template } from '../template/template';
import { TimeToSend } from './timetosend';
import { UserType } from './usertype';


/**
 * Defines a reminder class with it's necessary components
 *
 * @package reminder
 * @author Noah Trimble
 */
export class Reminder {
  id: number;
  emailTemplate: Template;
  textTemplate: Template;
  timeToSend: TimeToSend;
  userType: UserType;
}
