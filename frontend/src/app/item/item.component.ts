import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { CategoryDTO } from '../models/category-dto';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../service/cart.service';
import { ItemDTO } from '../models/item-dto';
import { Description } from '../models/description';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrl: './item.component.css',
})
export class ItemComponent implements OnInit {
  categoryItems: CategoryDTO[] = [];
  categoryID: string | null = null;
  itemID: number[] | null = null;
  idAndValue = new Map<number, boolean>();
  color?: string;
  size?: string;

  constructor(
    private service: DataService,
    private cartService: CartService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getCategoryId();
  }

  fetchData(id: string): void {
    this.service.getCategoryItems(id).subscribe({
      next: (value) => {
        (this.categoryItems = value),
          this.getItemId(value.flatMap((v) => v.items));
      },
      error: (err) => console.error(err),
      complete: () => console.info('Items successfully retrieved'),
    });
  }

  getCategoryId(): void {
    this.route.paramMap.subscribe((param) => {
      this.categoryID = param.get('id');
      if (this.categoryID) {
        this.fetchData(this.categoryID);
      }
    });
  }

  toCart(item: ItemDTO): void {
    const description = item.descriptions[1];
    this.color = description.color;
    this.size = description.size;
    if (this.color && this.size) {
      this.cartService.cartItems(item, this.color, this.size);
    }
  }

  getItemId(items: ItemDTO[]): void {
    if (items) {
      items.forEach((item) => this.itemQuantity(item.id));
    }
  }

  itemQuantity(id: number): void {
    this.service.getTotalQuantity(id).subscribe({
      next: (value) => {
        const itemPersists = value !== null && value > 0;
        this.idAndValue.set(id, itemPersists);
      },
      error: (err) => console.error(err),
    });
  }

  showShoppingicon(id: number): boolean {
    return this.idAndValue.get(id) || false;
  }
}
