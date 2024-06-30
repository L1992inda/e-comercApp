import { Injectable } from '@angular/core';
import { ItemDTO } from '../models/item-dto';
import { BehaviorSubject } from 'rxjs';
import { CartItemsTotal } from '../models/cartItemsTotal';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cart: CartItemsTotal[] = [];
  private cartSize = new BehaviorSubject(0);
  items = this.cartSize.asObservable();

  constructor() {}

  cartItems(item: ItemDTO,color:string,size:string): void {
    const itemPersist = this.cart.find(itemInCart => itemInCart.item.id === item.id && itemInCart.color === color && itemInCart.size === size);
    if (itemPersist) {
      itemPersist.count++;
    } else {
      this.cart.push({ item: item,color: color,size:size,count: 1 });
    }
    const count  = this.cart.reduce((count, item )=> count + item.count,0);
    this.cartSize.next(count);
  }

  getItems(): CartItemsTotal[] {
    return this.cart;
  }
}
