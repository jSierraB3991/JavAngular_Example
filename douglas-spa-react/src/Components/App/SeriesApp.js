import React, { useEffect, useState } from "react";
import './SeriesApp.css';
import Movie from "../Movie/Movie";
import { MovieService } from "../../Service/MovieService";


export default function SeriesApp()
{
    const [ moviesApi, setMoviesApi ] = useState([]);
    const [ categories, setCategories ] = useState([]);
    const [ categorySel, setCategorySel ] = useState(0);

    useEffect(() =>{
        async function fetchSeries(){
            var service = new MovieService();
            service.GetCategories().then(res =>{
                setCategories(res.data);
            });
        }
        fetchSeries();
    },[]);

    const handleOnChange = (e) =>{
        setCategorySel(e.target.value);
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();
    };

    useEffect(() =>{
        async function fetchSeries(){
            var service = new MovieService();
            service.GetMovies(0).then(res =>{
                setMoviesApi(res.data);
            });
        }
        fetchSeries();
    },[])

return <div>
        <header>
            <form onSubmit={handleOnSubmit} className="form">
                <select className="search" value={categorySel} onChange={handleOnChange}>
                    <option value="0">Seleccione</option>
                        {categories.map(category => (
                            <option key={category.id} value={category.id}>{category.name}</option>
                    ))}
                </select>
            </form>
        </header>
        <div className="movie-container">
            {moviesApi.map(movie => (<Movie key={movie.id} {...movie} />))}
        </div>
    </div>
}