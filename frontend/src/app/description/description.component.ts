import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { Description } from '../models/description';
import { ItemDTO } from '../models/item-dto';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../service/cart.service';

@Component({
  selector: 'app-description',
  templateUrl: './description.component.html',
  styleUrl: './description.component.css',
})
export class DescriptionComponent implements OnInit {
  itemDescriptions: ItemDTO[] = [];
  private itemID: string | null = null;
  uniqueColors: string[] = [];
  uniqueSizes: string[] = [];
  selectedColor = new Map<number, string>();
  selectedSize = new Map<number, string>();
  sizeCorrespondingColors  = new Map<string, string[]>();

  constructor(
    private service: DataService,
    private route: ActivatedRoute,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.getId();
  }

  getId(): void {
    this.route.paramMap.subscribe((param) => {
      this.itemID = param.get('itemid');
      if (this.itemID) {
        this.fetchData(this.itemID);
      }
    });
  }

  fetchData(id: string): void {
    this.service.getItemDescriptions(id).subscribe({
      next: (value) => (
        (this.itemDescriptions = value),
        this.getUniqueColors(value.flatMap((v) => v.descriptions))
      ),
      error: (err) => console.error(err),
      complete: () => 'Descriptions successfully retrieved',
    });
  }

  getUniqueColors(value: Description[]): void {
    const description = value.filter((d) => d.quantity > 0);

    description.forEach((d) => {
      if (!this.sizeCorrespondingColors.has(d.size)) {
        this.sizeCorrespondingColors.set(d.size, []);
      }
      const colors = this.sizeCorrespondingColors.get(d.size);
      if (colors && !colors.includes(d.color)) {
        colors.push(d.color);
      }
    });

    const colors = Array.from(new Set(description.flatMap((d) => d.color)));
    const sizes = Array.from(new Set(description.flatMap((d) => d.size)));

    this.uniqueColors = colors;
    this.uniqueSizes = sizes;
  }

  disable(itemId: number, colorName: string): boolean {
    const size = this.selectedSize.get(itemId);
    if (size) {
      return !this.sizeCorrespondingColors.get(size)?.includes(colorName);
    }
    return false;
  }

  setColor(itemId: number, colorName: string): void {
    this.selectedColor.set(itemId, colorName);
  }

  setSize(itemId: number, size: string): void {
    this.selectedSize.set(itemId, size);
  }

  addToCart(item: ItemDTO) {
    const color = this.selectedColor.get(item.id);
    const size = this.selectedSize.get(item.id);

    if (color && size) {
      this.cartService.cartItems(item, color, size);
    }
  }
}
