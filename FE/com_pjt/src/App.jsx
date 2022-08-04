import React, {StyleSheet, useState, useRef } from 'react'
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import { Carousel } from '3d-react-carousal';
import Grid from '@mui/material/Grid';
import { MovieDetail } from './route/MovieDetail'
import { BrowserRouter, Route, Switch } from "react-router-dom"
import { Container } from '@material-ui/core';
import styles from "./App.css";


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
  let times = []
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
  const sr = ["A","B","C","D",]
  const sc = ['1','2','3','4','5']

  for (const row of sr) {
    for (const col of sc) {
      seats.push(
        <button id={row + "0" + col}>{row}0{col}</button>
        )
    }
    seats.push(<br></br>)
  }
  console.log(seats)
  // console.log(seats[0].props.className)

  let sr_info = []
  let sc_info = []
  for (const iterator of sr) {
    sr_info.push(<span>{iterator}</span>)
  }
  for (const iterator of sc) {
    sr_info.push(<span>{iterator}</span>)
  }




const App = () => {
  const movieTitle = useRef(null)
  
  
  const callback = function(index){
    movieTitle.current.innerText = info_data[index]['title'] 
  }
  
  // const face = function(){
  //   const spawn = require('child_process').spawn;
  //   const result = spawn('python', ['gad.py']);
    
  //   result.stdout.on('data', function(data) {
  //     console.log(data.toString());
  //   });
  
  //   result.stderr.on('data', function(data) {
  //     console.log(data.toString());
  //   });
  // }
  
  return (
    <div className='App'>
      {/* <br />
      <br />
      <br />
      <br />

      <Carousel slides={slides} arrows={false} autoplay={false} interval={1000} onSlideChange={callback}/>

      <div>
        <br></br>
        <br></br>
        <br></br>

        <h1 ref={ movieTitle }></h1>'

          <button>영화 선택</button>

        <br></br>
        <br></br>
        <br></br>
        <br></br>

        <br></br>
        <br></br>
        <br></br>
      </div> */}

      {/* <div>
        <img src={ movie.img }></img>
        <h1>{ movie.title }</h1>
        <h3>{ movie.summary }</h3>

        { movie_time }
        <br></br>
        <button>좌석 선택</button>
        <div>
          
        </div>
      </div> */}
        

      {/* 대충 스크린 모양
      비어있는 좌석 모양, 선택된 좌석 모양, 예약된 좌석 모양
              1 2 3 4 5
            A 
            B
            C
            D
          */}

      {/* <div className='container'>
      <div className='row'>
      <div className='column row-2'></div>
      <div className='column row-10'></div>
      <div className='column row2'></div>
      <div className='column row10'></div>
      </div>
    </div> */}

      {/* <h1> 좌석 선택 </h1> */}
      {/* {sr_info}
      {sc_info} */}
      { seats }

      {/* <div className={ styles.screen }></div> */}
      
      {/* <ul className={styles.showcase}>
      <li>
      <div className={ styles.seat }></div>
      <small>N/A</small>
      </li>
      <li>
      <div className={styles.seat.selected}></div>
      <small>Selected</small>
      </li>
      <li>
      <div className={styles.seat.occupied}></div>
      <small>Occupied</small>
      </li>
      </ul>
      
      <div className={styles.container}>
      <div className={styles.screen}></div>
      
      <div className={ styles.row }>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      </div>
      <div className={ styles.row }>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat.occupied}></div>
      <div className={ styles.seat.occupied}></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      </div>
      <div className={ styles.row }>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
      <div className={ styles.seat.occupied}></div>
      <div className={ styles.seat.occupied}></div>
      </div>
      <div className={ styles.row }>
      <div className={ styles.seat }></div>
      <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          </div>
          <div className={ styles.row }>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat.occupied}></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat.occupied}></div>
          <div className={ styles.seat }></div>
          </div>
          <div className={ styles.row }>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat }></div>
          <div className={ styles.seat.occupied}></div>
          <div className={ styles.seat.occupied}></div>
          </div>
        </div> */}

      {/* <Button onClick={ face }>하이하이</Button> */}



    </div>
  )
}
export default App


//  정보 받아오는것은 콜백 요청으로 동기화 된상태에서 렌더링 하게끔 해줘야 하는거같음.