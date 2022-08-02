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

  const movie = {
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
  
  let time = []

  const times = [
    "16:10", "17:22", "18:11"
  ]
  for (const iterator of times) {
    time.push(
      <button>{iterator}</button>
    )
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
    
    <div className='App'>
      <Grid>
        <h1>{ movie.title }</h1>
        <img src={ movie.img }></img>
        <h3>{ movie.summary }</h3>

        { time }
        <div>
          
        </div>
        
      </Grid>
    </div>
  )
}
export default App