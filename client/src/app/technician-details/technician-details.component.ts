import { Technician } from '../technician';
import { Component, OnInit, Input } from '@angular/core';
import { TechnicianService } from '../technician.service';
import { TechnicianListComponent } from '../technician-list/technician-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-technician-details',
  templateUrl: './technician-details.component.html',
  styleUrls: ['./technician-details.component.less']
})
export class TechnicianDetailsComponent implements OnInit {

  id: number;
  technician: Technician;

  constructor(private route: ActivatedRoute,private router: Router,
              private technicianService: TechnicianService) { }

  ngOnInit() {
    this.technician = new Technician();

    this.id = this.route.snapshot.params['id'];

    this.technicianService.getTechnician(this.id)
      .subscribe(data => {
        console.log(data)
        this.technician = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['technicians']);
  }
}
