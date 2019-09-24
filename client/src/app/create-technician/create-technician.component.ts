import { TechnicianService } from '../technician.service';
import { Technician } from '../technician';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-technician',
  templateUrl: './create-technician.component.html',
  styleUrls: ['./create-technician.component.less']
})
export class CreateTechnicianComponent implements OnInit {

  technician: Technician = new Technician();
  submitted = false;

  constructor(private technicianService: TechnicianService,
              private router: Router) { }

  ngOnInit() {
  }

  newTechnician(): void {
    this.submitted = false;
    this.technician = new Technician();
  }

  save() {
    this.technicianService.createTechnician(this.technician)
      .subscribe(data => console.log(data), error => console.log(error));
    this.technician = new Technician();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/technicians']);
  }
}
