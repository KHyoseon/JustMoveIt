import React from 'react';
import ReactDOM from 'react-dom';
// import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import reducer from './reducer';
import { composeWithDevTools } from "redux-devtools-extension";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MovieSelect from './components/Page/MovieSelect';
import MovieDetail from './components/Page/MovieDetail';

const store = createStore(reducer, composeWithDevTools());
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  // store 연결
  <Provider store={store}> 
    <React.StrictMode>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<App />}></Route>
          <Route path='/movies' element={<MovieSelect />}></Route>
          <Route path='/movies/*' element={<MovieDetail />}></Route>
        </Routes>
      </BrowserRouter>
    </React.StrictMode>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
