import { Description } from './description';

export interface ItemDTO {
  id: number;
  img: string;
  imageData: string;
  contentType: string;
  name: string;
  price: number;
  descriptions: Description[];
}
