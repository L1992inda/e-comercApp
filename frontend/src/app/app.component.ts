import { Component, OnInit } from '@angular/core';
import { CartService } from './service/cart.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend';

  show:boolean =false;
  cartItemCount: number | null = null;


  constructor(private service:CartService){}
  ngOnInit(): void {
     this.service.items.subscribe( count=>{
      this.cartItemCount = count;

    });
  }

  showCart():void{
    this.show = !this.show;
  }

  

}
