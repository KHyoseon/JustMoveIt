import React from 'react';
import ReactDOM from 'react-dom';
// import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import reducer from './reducer';
import { composeWithDevTools } from "redux-devtools-extension";
import { BrowserRouter } from "react-router-dom";

const store = createStore(reducer, composeWithDevTools());
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  // store 연결
  <Provider store={store}> 
    <BrowserRouter>
      <React.StrictMode>
        <div className='App'>
          <App />
        </div>
      </React.StrictMode>
    </BrowserRouter>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
