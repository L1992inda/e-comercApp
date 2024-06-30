import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { CartItemsTotal } from '../models/cartItemsTotal';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent implements OnInit {
  addedItemsInCart: CartItemsTotal[] = [];
  itemColors: Map<number, string[]> = new Map();
  itemSizes: Map<number, string[]> = new Map();

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.addedItemsInCart = this.cartService.getItems();
    this.retrieveUniqueColorSizes();
  }

  itemsInCart(): number {
    const count = this.addedItemsInCart.reduce(
      (count, item) => count + item.count,
      0
    );
    return count;
  }

  totalPrice(): number {
    return this.addedItemsInCart.reduce(
      (totalPrice, items) => totalPrice + items.item.price * items.count,
      0
    );
  }

  retrieveUniqueColorSizes(): void {
    this.addedItemsInCart.forEach((item) => {
      const itemId = item.item.id;
      const description = item.item.descriptions.filter((d) => d.quantity > 0);
      const colors = Array.from(new Set(description.flatMap((d) => d.color)));
      const items = Array.from(new Set(description.flatMap((d) => d.size)));

      this.itemColors.set(itemId, colors);
      this.itemSizes.set(itemId, items);
    });
  }

  setColor(itemId: number, color: string) {
    const item = this.addedItemsInCart.filter(
      (itemInCart) => itemId === itemInCart.item.id
    );
    item.map((i) => {
      i.color = color;
    });
  }

  setSize(itemId: number, size: string) {
    const item = this.addedItemsInCart.filter(
      (itemInCart) => itemId === itemInCart.item.id
    );
    item.map((i) => {
      i.size = size;
    });
  }

  increment(itemId: number) {
    const item = this.addedItemsInCart.find(
      (itemInCart) => itemInCart.item.id === itemId
    );
    if (item) {
      const correspondingSizeColor = item.item.descriptions.filter(
        (d) => d.size === item.size && d.color === item.color
      );
      correspondingSizeColor.map((d) => {
        if (item.count < d.quantity) {
          item.count++;
        }
      });
    }
  }

  decrement(itemId: number) {
    const item = this.addedItemsInCart.find(
      (itemInCart) => itemInCart.item.id === itemId
    );
    if (item && item.count > 0) {
      item.count--;
    }
  }
}
