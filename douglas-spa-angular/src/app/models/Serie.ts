import { Category } from './Category';
import { Season } from './Season';

export interface Serie{
    id: number;
    name: String;
    remarks: String;
    category: Category;
    images: Array<String>;
    seasons: Array<Season>;
    firstImage: String;
}