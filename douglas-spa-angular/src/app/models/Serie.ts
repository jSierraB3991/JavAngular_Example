import { Category } from './Category';
import { Season } from './Season';

export class Serie{
    id: number;
    name: string;
    remarks: string;
    category: Category;
    images: Array<string>;
    seasons: Array<Season>;
    firstImage: string;

    constructor(id: number, name: string, remarks: string, category: Category){
        this.id = id;
        this.name = name;
        this.remarks = remarks;
        this.category = category;
        this.images = [];
        this.seasons = [];
        this.firstImage = "";
    }
}