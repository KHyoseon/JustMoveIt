import React from "react";
import { Button } from "@mui/material";
import Seats from "./Seats";

let movie
let times = []
let movie_time = []
let movie_time_tickets = []
let seats_info = []


 movie = {
    "id": 1,
    "title": "인터스텔라",
    "movieCode": "1",
    "country": "1",
    "genre": "1",
    "summary": "1",
    "runningTime": "1",
    "img": "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zDNAeWU0PxKolEX1D8Vn1qWhGjH.jpg",
    "rating": "1",
    "engTitle": "1",
    "ageLimit": "12",
    "releaseDate": "2000-11-11T00:00:00",
    "director": "1",
    "actor": "1",
    "totalCustomer": "1",
    "moviePlayingInfo": []
  }
    const axios = require('axios').default;

    axios.get('http://localhost/justmoveit/movies/1')
    .catch(function(err){
        console.log(err,'axios 안되누 ㅡ')
    })
    .then(function (response) {
    // 성공 핸들링
    // console.log(response.data);
    movie = response.data
    // console.log(movie)
    const temp_data = response.data.moviePlayingInfoList   

    movie_time_tickets = temp_data[0].tickets
    // console.log(movie_time_tickets)

    for (const iterator of movie_time_tickets) {
      seats_info.push( iterator.seat )
    }
    seats_info.sort()
    // console.log(seats_info)
    // console.log(seats_info[0][0])
    for (const seat of seats_info) {
      console.log(seat)
    }


    for (const iterator of temp_data) {
      // movie_time.push(
      //   <Button>{iterator.startTime}</Button>
      // )
      times.push(iterator.startTime)
    }
    times.sort()

    for (const time of times) {
      movie_time.push(
        <Button>{ time }</Button>
      )
    }
    // movie_time.sort()
  
    // for (const iterator of info_data) {
    //   slides.push(<img src={ iterator['img'] }></img>)
    //   text_data.push(<h1>{iterator['title']}</h1>)
    // }
  })
  
function MovieDetail() {

    return (
        <div className="MovieDetail">
            <div>
                <img src={ movie.img }></img>
                <h1>{ movie.title }</h1>
                <h3>{ movie.summary }</h3>

                { movie_time }
                <br></br>
                {/* <button>좌석 선택</button> */}
                <Seats />
                <div>
                
                </div>
            </div>

            {/* {sr_info}
            {sc_info} */}
        </div>
    )
}
export default MovieDetail;