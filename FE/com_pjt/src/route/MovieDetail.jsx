import React, {StyleSheet, useState} from 'react'

const axios = require('axios').default;

axios.get('http://localhost/justmoveit/movies/')
  .catch(function(err){
    console.log(err,'axios 안되누 ㅡ')
  })
  .then(function (response) {
    // 성공 핸들링
    // console.log(response.data);
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

const MovieDetail = () => {

  return (
    <div>
      <h1>아 여는 페이지다!</h1>
    </div>
  )
}
export default MovieDetail