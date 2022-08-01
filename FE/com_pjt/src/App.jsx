import React, {StyleSheet, useState} from 'react'
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import { Carousel } from '3d-react-carousal';
import Grid from '@mui/material/Grid';
import { MovieDetail } from './route/MovieDetail'



let slides = [];
let text_data = []
let info_data 

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

  const callback = function(index){
    console.log(index)
  }

const App = () => {

  return (
    <div className='App'>
      <Carousel slides={slides} arrows={false} autoplay={false} interval={1000} onSlideChange={callback}/>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center">
        <br></br>
        <br></br>
        <br></br>
        {/* <h1>{ info_data[i].title }</h1> */}
        {/* <h1>제목</h1> */}'
        <Link to='/MovieDetail'>
          <button>영화 선택</button>
        </Link>
        <br></br>
        <br></br>
        <br></br>
        <br></br>

        <br></br>
        <br></br>
        <br></br>
      </Grid>
    </div>
  )
}
export default App