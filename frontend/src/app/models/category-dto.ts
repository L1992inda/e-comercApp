import { ItemDTO } from "./item-dto"

export interface CategoryDTO {
    id: number
    name: string
    items: ItemDTO[]
}
