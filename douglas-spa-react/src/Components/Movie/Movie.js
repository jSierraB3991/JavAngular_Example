import React from "react"



const movie = ({name, remarks, category, seasons, imageTVSeries, firstImage})  => 
    <div className="movie">
        <img src={firstImage} alt={name} />
        <div className="movie-info">
            <h3>{name}</h3>
            <span>{category.name}</span>
        </div>
        <div className="movie-over">
            <h3>Sipnosis</h3>
            <p className="remarks-movie">{remarks}</p>
        </div>
    </div>

export default movie;