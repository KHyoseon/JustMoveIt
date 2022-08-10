import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
// import store from './app/store';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
// import reducer from './reducer';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MovieSelect from './components/Page/MovieSelect';
import MovieDetail from './components/Page/MovieDetail';

const axios = require('axios').default;
const root = ReactDOM.createRoot(document.getElementById('root'));
const initialState = { movie: [] }

initialState['movieCode'] = '0'

axios.get('https://i7d207.p.ssafy.io/api/movies')
  .catch(function(err){
    console.log(err,'axios 안되누 ㅡ')
  })
  .then(function (response) {
    // 성공 핸들링
    // console.log(response.data);
    const info_data = response.data;
    initialState['movies'] = info_data;
    
    for (const iterator of info_data) {
      initialState['movie'][iterator['movieCode']] = iterator
    }
    // console.log(initialState['movie'])
    // for (const iterator of info_data) {

    //   slides.push(<img src={ iterator['img'] }></img>)
    //   text_data.push(<h1>{iterator['title']}</h1>)
    // }

  })

function reducer(state = initialState, action){
  // if (action.type === 'MC'){
  //   state['movieCode'] = mcValue;
  //   return state
  // }
  return state
}

const store = createStore(reducer)

root.render(
  // store 연결
  <Provider store={store}> 
    <React.StrictMode>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<App />}></Route>
          <Route path='/movies' element={<MovieSelect />}></Route>
          <Route path='/movies/:movieCode' element={<MovieDetail />}></Route>
        </Routes>
      </BrowserRouter>
    </React.StrictMode>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
