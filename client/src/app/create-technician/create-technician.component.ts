import { TechnicianService } from '../technician.service';
import { Technician } from '../technician';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-technician',
  templateUrl: './create-technician.component.html',
  styleUrls: ['./create-technician.component.less']
})
export class CreateTechnicianComponent implements OnInit {

  id: number;
  technician: Technician = new Technician();
  submitted = false;
  title: string;

  constructor(private route: ActivatedRoute,
              private technicianService: TechnicianService,
              private router: Router) { }

  ngOnInit() {
    this.technician = new Technician();

    this.id = this.route.snapshot.params['id'];

    this.title = this.id ? 'Edit Technician' : 'Create Technician';

    if (this.id) {
      this.technicianService.getTechnician(this.id)
        .subscribe(data => {
          console.log(data)
          this.technician = data;
        }, error => console.log(error));
    }
  }

  newTechnician(): void {
    this.submitted = false;
    this.technician = new Technician();
  }

  save() {
    let response = !this.id ? this.technicianService.createTechnician(this.technician)
      : this.technicianService.updateTechnician(this.id, this.technician);
    response.subscribe(data => console.log(data), error => console.log(error));
    //this.technician = new Technician();
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
