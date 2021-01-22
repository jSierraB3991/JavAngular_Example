import { Serie } from './Serie';

export interface ResultPage{
    data: Array<Serie>;
    previousPage: String;
    maxPage: number;
    nextPage: String;
}