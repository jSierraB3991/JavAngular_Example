import React, { useEffect, useState } from "react";
import './SeriesApp.css';
import Movie from "../Movie/Movie";
import { MovieService } from "../../Service/MovieService";


export default function SeriesApp()
{
    const [searchTerm, setSearchTerm] = useState('');
    const [ moviesApi, setMoviesApi ] = useState([]);

    useEffect(() =>{
        async function fetchSeries(){
            var service = new MovieService();
            service.GetMovies().then(res =>{
                setMoviesApi(res.data);
            });
        }
        fetchSeries();
    },[])



    const handleOnSubmit = (e) => {
        e.preventDefault();
    };

    const handleOnChange = (e) =>{
        setSearchTerm(e.target.value);
        //TODO SEARCH SERIE
    };

    return <div>
        <header>
            <form onSubmit={handleOnSubmit}>
                <input type="text" className="search" 
                placeholder="Search..." value={searchTerm}
                onChange={handleOnChange} />
            </form>
        </header>
        <div className="movie-container">
            {moviesApi.map(movie => (<Movie key={movie.id} {...movie} />))}
        </div>
    </div>
}