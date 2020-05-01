import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout-landing',
  templateUrl: './checkout-landing.component.html',
  styleUrls: ['./checkout-landing.component.less']
})
export class CheckoutLandingComponent implements OnInit {

  constructor( private router: Router) { }

  ngOnInit(): void {
  }

  toCheckout() {
    this.router.navigate(['/flooring/check-out/create-customer']);
  }

  toCheckin() {
    this.router.navigate(['/flooring/check-in']);
  }

}
