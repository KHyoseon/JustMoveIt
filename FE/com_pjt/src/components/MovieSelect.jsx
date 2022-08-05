import React, { useRef } from "react";
import { Carousel } from '3d-react-carousal';

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

function MovieSelect(props) {

    const movieTitle = useRef(null)

    const callback = function(index){
        movieTitle.current.innerText = info_data[index]['title'] 
      }

    return (
        <div className='MovieSelect'>

            <Carousel slides={slides} arrows={false} autoplay={false} interval={1000} onSlideChange={callback}/>
    
            <div>
    
                <h1 ref={ movieTitle }></h1>'
    
                <button>영화 선택</button>
    
            </div>
        </div>
    )
}
export default MovieSelect;