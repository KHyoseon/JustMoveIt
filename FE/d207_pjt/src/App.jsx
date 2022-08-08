// import logo from './logo.svg';
// import './App.css';
// import { Link } from "react-router-dom";

// function App() {
//   return (
//     <div>
//       {/* <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header> */}
//       <h1>보이니?</h1>
//       {/* <nav
//         style={{
  //           borderBottom: "solid 1px",
  //           paddingBottom: "1rem",
  //         }}
  //       >
  //         <Link to="/invoices">Invoices</Link> |{" "}
  //         <Link to="/expenses">Expenses</Link>
//       </nav> */}
//     </div>
//   );
// }

// export default App;

// import './App.css';
// import { Outlet, Link } from "react-router-dom";

// export default function App() {
//   return (
//     <div>
//       {/* <h1>Bookkeeper</h1> */}
      
//       <nav
//         style={{
//           borderBottom: "solid 1px",
//           paddingBottom: "1rem",
//         }}
//       >
//         <Link to="/invoices">Invoices</Link> |{" "}
//         <Link to="/expenses">Expenses</Link>
//       </nav>
//       <Outlet />

//     </div>
//   );
// }
import React, {StyleSheet, useState} from 'react'
import { StackedCarousel } from 'react-stacked-carousel'
// import 'react-stacked-carousel/dist/index.css';
// import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {Carousel} from '3d-react-carousal';


import { styled } from '@mui/material/styles';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';

let data = [
  {id: 1, img:'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/un8ZDtx2SMwNwXRYy65aItnNjab.jpg'},
  {id: 2, img:'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/odxdUZWZ7fBfy3ZRj063wuJnZvo.jpg'},
  {id: 3, img:'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1heBUD8o0sgdqLWyeXkylR2POKb.jpg'},
  {id: 4, img:'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/p9ZUzCyy9wRTDuuQexkQ78R2BgF.jpg'}
];


// let movies = [
//   {id: 1, movie_name:'토르:러브 앤 썬더', movie_seats:[a1,a2,a3,a4,a5,b1,b2,b3,b4,b5],movie_descript:'어쩌구 저쩌구' , poster_path:'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/un8ZDtx2SMwNwXRYy65aItnNjab.jpg'}
// ];

// let slides = [
//   <img  src={data[0].img} alt="1" />,
//   <img  src={data[1].img} alt="3" />  ,
//   <img  src={data[2].img} alt="4" />  ,
//   <img  src={data[3].img} alt="2" />  ,
// ];

let slides = [
  <Card>
    <CardContent>
      <CardMedia component='img' image='https://www.themoviedb.org/t/p/w600_and_h900_bestv2/un8ZDtx2SMwNwXRYy65aItnNjab.jpg'></CardMedia>
      <Typography>토오오오오르으으응</Typography>
    </CardContent>
  </Card>,
  <Card>
    <CardContent>
      <CardMedia component='img' image='https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1heBUD8o0sgdqLWyeXkylR2POKb.jpg'></CardMedia>
      <Typography>토오오오오르으으응 아님</Typography>
    </CardContent>
  </Card>,
  <Card>
    <CardContent>
      <CardMedia component='img' image='https://www.themoviedb.org/t/p/w600_and_h900_bestv2/p9ZUzCyy9wRTDuuQexkQ78R2BgF.jpg'></CardMedia>
      <Typography>토오오오오르으으응</Typography>
    </CardContent>
  </Card>,
  <Card>
    <CardContent>
      <CardMedia component='img' image='https://www.themoviedb.org/t/p/w600_and_h900_bestv2/odxdUZWZ7fBfy3ZRj063wuJnZvo.jpg'></CardMedia>
      <Typography>토오오오오르으으응 아님</Typography>
    </CardContent>
  </Card>,
  <Card>
    <CardContent>
      <CardMedia component='img' image='data:image/jpeg;base64,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'></CardMedia>
      <Typography>토오오오오르으으응 아님</Typography>
    </CardContent>
  </Card>,
];

// <img src="https://picsum.photos/800/304/?random" alt="5" />   

// const useStyles = makeStyles({
//   root: {
//     maxWidth: 345,
//   },
//   media: {
//     height: 140,
//   },
// });

const callback = function(index){
  console.log("callback",slides[index].props.children.props.children[1].props.children);
}

const App = () => {
  const [card, setCard] = useState(null);
  const onCardChange = (event) => {
    console.log("Card", event);
  }

  return (
    <div>
      <Carousel slides={slides} autoplay={false} interval={1000} onSlideChange={callback}/>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
        >
        <button><p>영화 선택</p></button>  
      </Grid>
    </div>
  )
}
export default App