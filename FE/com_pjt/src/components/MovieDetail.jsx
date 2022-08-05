import React from "react";

function MovieDetail() {

    return (
        <div className="MovieDetail">
            <div>
                <img src={ movie.img }></img>
                <h1>{ movie.title }</h1>
                <h3>{ movie.summary }</h3>

                { movie_time }
                <br></br>
                <button>좌석 선택</button>
                <div>
                
                </div>
            </div>

            {sr_info}
            {sc_info}
        </div>
    )
}
export default MovieDetail;