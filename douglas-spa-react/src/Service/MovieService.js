import axios from 'axios'

export class MovieService
{   
    GetMovies(page){
        const API_SERIES = "http://ec2-3-135-202-119.us-east-2.compute.amazonaws.com:8080/api/public/serie";
        return axios.get(API_SERIES);
    }

    GetCategories(){
        const API_CATEGORY = "http://ec2-3-135-202-119.us-east-2.compute.amazonaws.com:8080/api/public/category";
        return axios.get(API_CATEGORY);
    }
}