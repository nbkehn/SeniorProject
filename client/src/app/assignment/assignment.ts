import { Technician } from '../technician/technician';
import { Appointment } from '../appointment/appointment';

export class Assignment {
    id: number;
    technicians: Technician [];
    dayNumber: number;
    unavailableTechnicians: Technician [];

    constructor(dayNumber: number) {
        this.technicians = [];
        this.unavailableTechnicians = [];
        this.dayNumber = dayNumber;
    }
}