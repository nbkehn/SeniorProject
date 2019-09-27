import { TechnicianDetailsComponent } from '../technician-details/technician-details.component';
import { Observable } from "rxjs";
import { TechnicianService } from "../technician.service";
import { Technician } from "../technician";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-technician-list",
  templateUrl: "./technician-list.component.html",
  styleUrls: ["./technician-list.component.less"]
})
export class TechnicianListComponent implements OnInit {
  technicians: Observable<Technician[]>;

  constructor(private technicianService: TechnicianService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.technicians = this.technicianService.getTechniciansList();
  }

  deleteTechnician(id: number) {
    this.technicianService.deleteTechnician(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  technicianDetails(id: number){
    this.router.navigate(['details', id]);
  }

  editTechnician(id: number) {
    this.router.navigate(['edit', id]);
  }
}
