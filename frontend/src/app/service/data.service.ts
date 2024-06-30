import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryDTO } from '../models/category-dto';
import { ItemDTO } from '../models/item-dto';
import { Description } from '../models/description';

const url = 'http://localhost:8080';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor(private http: HttpClient) {}

  getCategories(): Observable<CategoryDTO[]> {
    return this.http.get<CategoryDTO[]>(url + '/category/user/categories');
  }

  getCategoryItems(id: string): Observable<CategoryDTO[]> {
    let params = new HttpParams().set('ID', id);

    return this.http.get<CategoryDTO[]>(url + '/category/user/categories', {
      params,
    });
  }

  getItemDescriptions(id: string): Observable<ItemDTO[]> {
    let params = new HttpParams().set('ID', id);

    return this.http.get<ItemDTO[]>(url + '/item/user/items', { params });
  }

  getTotalQuantity(id:number): Observable<number>{
    return this.http.get<number>(url+`/description/user/totalQuantity/${id}`);
  }
}
