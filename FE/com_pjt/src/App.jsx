import React, {StyleSheet, useState, useRef } from 'react'
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import { useSelector } from 'react-redux';
// import { Carousel } from '3d-react-carousal';
// import Grid from '@mui/material/Grid';
// import { MovieDetail } from './route/MovieDetail'
// import { Container } from '@material-ui/core';
import styles from "./App.css";
// import ReactDOM from "react-dom";

// import Seats from "./components/Page/Seats";
// import DialPage from "./components/Page/DialPage";
// import MovieSelect from "./components/Page/MovieSelect";


let movie_detail = []




  
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
  
  const enterKey = function(e){
    if (window.Event.KeyCode == 13){
      console.log("으아앙")
      // return (
      //   <h1>엔터키다!</h1>
      // )
    }
  }

  function handleEvent(e){
    e.preventDefault();
    console.log("응애!")
    // document.location.href = "./";
  }

  const appState = useSelector( (state) => state );
  console.log(appState)

  return (
    <div className='App'>
      {/* <Seats /> */}
      {/* <MovieSelect /> */}
      {/* <DialPage /> */}

      {/* <BrowserRouter>
        <Routes>
          <Route path='/movies' element={ <MovieSelect /> }>
          </Route>
        </Routes>
      </BrowserRouter> */}

      <Link to='/movies'><Button type='submit'>
          <img src="https://play-lh.googleusercontent.com/pmWpm2WTx2-9dTGeAfU0LMyofNgPNZlbzbFhYxFm0c9BOaJl83jxoVQLxJcxThGiHxE" alt="" />
        </Button></Link>
    </div>
  )
}
export default App


//  정보 받아오는것은 콜백 요청으로 동기화 된상태에서 렌더링 하게끔 해줘야 하는거같음.