import { Template } from '../template/template';

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
  timeToSend: number;
}
