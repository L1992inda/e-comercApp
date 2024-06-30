import { ItemDTO } from './item-dto';

export interface CartItemsTotal {
  item: ItemDTO;
  color: string;
  size: string;
  count: number;
}
