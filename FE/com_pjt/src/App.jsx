import React, {StyleSheet, useState, useRef } from 'react'
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import { Carousel } from '3d-react-carousal';
import Grid from '@mui/material/Grid';
import { MovieDetail } from './route/MovieDetail'
import { BrowserRouter, Route, Switch } from "react-router-dom"
import { Container } from '@material-ui/core';

// function Field() {
  
// }

let slides = [];
let text_data = []
let info_data 
let movie_title

let movie_detail = []



const axios = require('axios').default;

axios.get('http://localhost/justmoveit/movies/')
  .catch(function(err){
    console.log(err,'axios 안되누 ㅡ')
  })
  .then(function (response) {
    // 성공 핸들링
    // console.log(response.data);
    info_data = response.data
    for (const iterator of info_data) {

      slides.push(<img src={ iterator['img'] }></img>)
      text_data.push(<h1>{iterator['title']}</h1>)
    }
  })
  
  // document.onkeydown = arrow_keys;
  
  // document.addEventListener("keydown")
  
  // const arrow_keys = function(e, index){
  //   if(e.key == 37 || e.key =="ArrowRight"){
  //     console.log(index)
  //   }
  //   else if(e.key == 39 || e.key =="ArrowLeft"){
  //     console.log(index)
  //   }
  // }

  let movie
  let movie_time = []
  let movie_time_tickets = []
  let seats_info = []
  
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
    console.log(seats_info)
    seats_info.sort()
    console.log(seats_info)
    console.log(seats_info[0][0])

    for (const iterator of temp_data) {
      movie_time.push(
        <button>{iterator.startTime}</button>
      )
    }
    //이거 동기식으로 콜백요청을 해야 할것으로 보임.

    // for (const iterator of info_data) {

    //   slides.push(<img src={ iterator['img'] }></img>)
    //   text_data.push(<h1>{iterator['title']}</h1>)
    // }
  })

  // movie = {
  //   "id": 1,
  //   "title": "인터스텔라",
  //   "movieCode": "1",
  //   "country": "1",
  //   "genre": "1",
  //   "summary": "1",
  //   "runningTime": "1",
  //   "img": "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zDNAeWU0PxKolEX1D8Vn1qWhGjH.jpg",
  //   "rating": "1",
  //   "engTitle": "1",
  //   "ageLimit": "12",
  //   "releaseDate": "2000-11-11T00:00:00",
  //   "director": "1",
  //   "actor": "1",
  //   "totalCustomer": "1",
  //   "moviePlayingInfo": []
  // }

  
  // let time = []

  // const times = [
  //   "16:10", "17:22", "18:11"
  // ]

  for (const iterator of movie_time) {
    movie_time.push(
      <button>{iterator}</button>
    )
  }

  let seats = []
  let seat_num = 1
  for (let index = 0; index < 4; index++) {
    for (let index1 = 0; index1 < 5; index1++) {
      seats.push(
        <button> {seat_num} </button>
        )
        seat_num = seat_num + 1
    }
    seats.push(<br></br>)
  }


const App = () => {
  const movieTitle = useRef(null)
  
  const callback = function(index){
    movieTitle.current.innerText = info_data[index]['title'] 
  }
  
  return (
    // <div className='App'>
    //   <br />
    //   <br />
    //   <br />
    //   <br />

    //   <Carousel slides={slides} arrows={false} autoplay={false} interval={1000} onSlideChange={callback}/>

    //   <Grid
    //     container
    //     direction="column"
    //     justifyContent="center"
    //     alignItems="center">
    //     <br></br>
    //     <br></br>
    //     <br></br>

    //     <h1 ref={ movieTitle }></h1>'

    //       <button>영화 선택</button>

    //     <br></br>
    //     <br></br>
    //     <br></br>
    //     <br></br>

    //     <br></br>
    //     <br></br>
    //     <br></br>
    //   </Grid>
    // </div>
    
    // <div className='App'>
    //    <Grid
    //      container
    //      direction="column"
    //      justifyContent="center"
    //      alignItems="center">

    //     <h1>{ movie.title }</h1>
    //     <img src={ movie.img }></img>
    //     <h3>{ movie.summary }</h3>

    //     { time }
    //     <div>
          
    //     </div>
        
    //     </Grid>
    // </div>

    <div className='App'>
      <h1> 좌석 선택 </h1>
      {/* 대충 스크린 모양
          비어있는 좌석 모양, 선택된 좌석 모양, 예약된 좌석 모양
              1 2 3 4 5
            A
            B
            C
            D
             */}
      { seats }
    </div>
  )
}
export default App