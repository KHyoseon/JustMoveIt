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



const MovieDetail = () => {

  return (
    <div>
      <h1>아 여는 페이지다!</h1>
    </div>
  )
}
export default MovieDetail