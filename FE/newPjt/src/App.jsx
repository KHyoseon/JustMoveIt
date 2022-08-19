// import logo from './logo.svg';
import React, { useState, useEffect } from "react";

import "./App.css";
// import { Button } from "@mui/material";
// import { useNavigate } from "react-router-dom";
// import { CompressOutlined } from "@mui/icons-material";
import Router from "./Router";
// import Face from "./Components/AI/Face";

function App() {
  // const [AppAge, setAge] = useState("0");
  // const [AppGender, setGender] = useState("1");
  const [moviesData, setMoviesData] = useState();

  const websocket = new WebSocket("wss://i7d207.p.ssafy.io/ws/socket");
  let message;

  websocket.onmessage = onMessage;
  websocket.onopen = onOpen;
  websocket.onclose = onClose;

  //채팅창에서 나갔을 때
  function onClose(evt) {
    if (!isOpen(websocket)) return;
  }

  //채팅창에 들어왔을 때
  function onOpen(evt) {
    if (!isOpen(websocket)) return;
  }

  function onMessage(msg) {
    if (!isOpen(websocket)) return;

    var data = msg.data;
    message = null;

    //현재 세션에 로그인 한 사람
    message = data;
    // console.log(message);
    if (window.location.pathname == "/") {
      if (Number(message) >= 150) {
        // window.location.assign(window.location.href + "default");
        document.getElementById("toDefault").click();
      } else if (Number(message) < 150 && 100 < Number(message)) {
        // window.location.assign(window.location.href + "low");
        document.getElementById("toLow").click();
      }
    }
    return message;
  }

  function isOpen(ws) {
    return ws.readyState === ws.OPEN;
  }

  // console.log(window.location.pathname);

  // const navigate = useNavigate();
  // const { navigation } = this.props;

  // const dh = 190;

  // console.log("https://i7d207.p.ssafy.io/api/movies/" + movie_code[0]);
  // axiosS
  //   .get("https://i7d207.p.ssafy.io/api/movies/" + movie_code[0])
  //   .catch(function (err) {
  //     console.log(err, "default 데이터x");
  //   })
  //   .then(function (response) {
  //     // 성공 핸들링
  //     console.log(response.data);
  //     state = response.data;
  //   });
  // console.log(state);

  // var timer = setInterval(function () {
  //   // console.log("Hello!!");
  //   // axios
  //   //   .get("https://i7d207.p.ssafy.io/api/sensor")
  //   //   .catch(function (err) {
  //   //     console.log(err, "센서값 못받아옴");
  //   //   })
  //   //   .then(function (response) {
  //   //     // console.log(response.data.lengthValue);
  //   //     if (window.location.pathname == "/") {
  //   //       // console.log("아 응애");
  //   //       // navigation.navigate("default");
  //   //       // navigate("/default");

  //   //       if (response.data.lengthValue >= 150) {
  //   //         window.location.assign(window.location.href + "default");
  //   //       } else if (
  //   //         response.data.lengthValue < 150 &&
  //   //         100 < response.data.lengthValue
  //   //       ) {
  //   //         window.location.assign(window.location.href + "low");
  //   //       }
  //   //     }
  //   //   });
  // }, 1000);

  /////////////////////////////////////////////////////////////

  // function displayCamera() {}

  /////////////////////////////////////////////////////////////

  // var timer = setInterval(function () {
  //   if (window.location.pathname == "/") {
  //     message = onMessage();
  //     if (Number(message) >= 150) {
  //       window.location.assign(window.location.href + "default");
  //     } else if (Number(message) < 150 && 100 < Number(message)) {
  //       window.location.assign(window.location.href + "low");
  //     }
  //   }
  // }, 1000);

  // useEffect(() => {
  //   const axios = require("axios").default;

  //   const body = {
  //     age: AppAge,
  //     gender: AppGender,
  //   };

  //   axios
  //     .post("https://i7d207.p.ssafy.io/api/recommend/", body)
  //     .catch(function (err) {
  //       console.log(err, "추천영화 못받아옴");
  //     })
  //     .then(function (response) {
  //       setMoviesData(response.data);
  //     });
  //   // console.log("아 돌아간다~");
  // }, [AppAge]);

  return (
    <div className="App">
      <Router state={moviesData} />
      {/* <h3>
        나이는 : {AppAge}, 성별은 : {AppGender}
      </h3>
      <h3>방향은 현재 {dirction} 을 가리키고 있다!</h3> */}

      <button style={{ display: "none" }}>영상 시작</button>
      {/* <Face
        setAge={setAge}
        setGender={setGender}
        setMoviesData={setMoviesData}
        AppAge={AppAge}
        AppGender={AppGender}
        moviesData={moviesData}
      ></Face> */}
    </div>
  );
}

export default App;
