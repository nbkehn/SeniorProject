import { Technician } from '../technician/technician';
import { Appointment } from '../appointment/appointment';

export class Assignment {
    id: number;
    technicians: Technician [];
    dayNumber: number;

    constructor(dayNumber: number) {
        this.technicians = [];
        this.dayNumber = dayNumber;
    }
}