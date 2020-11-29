import axios from 'axios'

export class MovieService
{   
    GetMovies(page){
        const API_SERIES = "http://localhost:20001/api/serie";
        return axios.get(API_SERIES);
    }

    GetCategories(){
        const API_CATEGORY = "http://localhost:20001/api/category";
        return axios.get(API_CATEGORY);
    }
}