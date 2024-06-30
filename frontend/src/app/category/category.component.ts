import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { CategoryDTO } from '../models/category-dto';
import { CartService } from '../service/cart.service';
import { ItemDTO } from '../models/item-dto';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css',
})
export class CategoryComponent implements OnInit {
  categories: CategoryDTO[] = [];
  

  constructor(private service: DataService,private ser : CartService) {}

  ngOnInit(): void {
    this.fetchDtat();
  }

  fetchDtat(): void {
    this.service.getCategories().subscribe({
      next: (value) => (this.categories = value),
      error: (err) => console.error(err),
      complete: () => console.info('Categories successfully retrieved'),
    });
  }
}
