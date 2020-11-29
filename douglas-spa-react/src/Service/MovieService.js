import axios from 'axios'

export class MovieService
{   
    GetMovies(){
        const API_SERIES = "http://localhost:20001/api/serie";
        return axios.get(API_SERIES);
    }
}