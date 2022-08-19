import React, { useState } from "react";
import { Button } from "@mui/material";
import { Link, useNavigate, useLocation } from "react-router-dom";

import "./Pay.css";

// const movies = [];

// axios
//   .get("https://i7d207.p.ssafy.io/api/movies")
//   .catch(function (err) {
//     // console.log(err, "movies 데이터x");
//   })
//   .then(function (response) {
//     // 성공 핸들링
//     // console.log(response.data);
//     const info_data = response.data;

//     for (const movieData of info_data) {
//       movies.push(movieData);
//     }
//   });

function Pay() {
  const navigate = useNavigate();
  const location = useLocation();
  const axios = require("axios").default;

  const state = location.state;
  const seatList = [];
  let classification = "";
  for (const iterator of state.seats.selectedSeats) {
    // console.log(iterator);
    // a.push(Number(iterator));
    const a = parseInt(iterator / 5);
    const b = iterator % 5;
    let seat = "";
    switch (a) {
      case 0:
        seat = seat + "A";
        break;
      case 1:
        seat = seat + "B";
        break;
      case 2:
        seat = seat + "C";
        break;
      case 3:
        seat = seat + "D";
        break;

      default:
        break;
    }
    seat = seat + `0${b}`;
    seatList.push(seat);
  }
  seatList.sort();
  // console.log(seatList);
  let seatTicket = "";
  for (const iterator of seatList) {
    seatTicket += iterator + ",";
  }
  // console.log(seatTicket.slice(0, -1));
  // console.log(a);
  // a.sort();
  // console.log(a);
  // let a = Object.assign(state.seats.selectedSeats);
  // a.sort();
  // console.log(a);

  for (let index = 0; index < state.adult.defaultPerson; index++) {
    classification += "ADULT,";
  }
  for (let index = 0; index < state.child.kisPerson; index++) {
    classification += "KIDS,";
  }
  // console.log(classification);
  console.log(state.PlayingInfoID.moviePlayingId);
  // * state.adult.defaultPerson;
  // "KIDS," * state.child.kisPerson;
  const goBack = () => {
    navigate(-1);
  };

  function cost() {
    return (
      <div>
        {/* <h1>영화: {state["movie"]}</h1> */}
        {/* <h2>성인: {props.adult} 명</h2>
        <h2>청소년: {props.child} 명</h2>
        <hr />
        <h3>총게 : {15000 * props.adult + 10000 * props.child}</h3> */}
      </div>
    );
  }
  function ticketPost() {
    // var form = document.createElement('form');
    // form.setAttribute('method', 'post')
    // form.setAttribute('action', 'https://i7d207.p.ssafy.io/api/tickets')
    const body = {
      classification: classification.slice(0, -1),
      moviePlayingInfoId: state.PlayingInfoID.moviePlayingId,
      phoneNumber: "",
      seat: seatTicket.slice(0, -1),
      totalCost: `${
        12000 * state.adult.defaultPerson + 10000 * state.child.kisPerson
      }`,
    };
    axios
      .post("https://i7d207.p.ssafy.io/api/tickets/", body)
      .then((res) => console.log(body));
    // console.log(body);
  }
  return (
    <div className="Pay">
      <h1>결제하기</h1>
      {/* <Link to='/movies'></Link> */}
      {/* <h1>{props.movie}</h1> */}
      {/* <div>
        <h2>성인: {state.adult} 명</h2>
        <h2>청소년: {state.child} 명</h2>
        <hr />
        <h3>총게 : {15000 * state.adult + 10000 * state.child}</h3>
      </div> */}

      <div className="reserve_box">
        <div className="reserve_info">
          <img className="poster" src={state["img"]} alt="사진이 없어용 ㅠ" />
          <table className="reserve_info_detail">
            <tr>
              <td colSpan={2}>
                <h2>{state["movie"]}</h2>
              </td>
            </tr>
            <tr>
              <td>
                <h3>성인</h3>
              </td>
              <td>
                <h3>{state.adult.defaultPerson}명</h3>
              </td>
            </tr>
            <tr>
              <td>
                <h3>청소년</h3>
              </td>
              <td>
                <h3>{state.child.kisPerson} 명</h3>
              </td>
            </tr>
            <tr>
              <td colSpan={2}>
                <hr />
              </td>
            </tr>
            <tr>
              <td>
                <h4>총 결제 금액</h4>
              </td>
              <td>
                {12000 * state.adult.defaultPerson +
                  10000 * state.child.kisPerson}
              </td>
            </tr>
          </table>
        </div>
      </div>

      <hr />

      <div className="button_section">
        <button id="before" onClick={goBack} variant="contained">
          결제 취소
        </button>
        <span>&nbsp;&nbsp;&nbsp;</span>
        <Link
          onClick={ticketPost}
          to="./payend"
          style={{ textDecoration: "none" }}
          state={{
            movie: state["movie"],
            img: state["img"],
            adult: state.adult.defaultPerson,
            child: state.child.kisPerson,
            seats: seatTicket.slice(0, -1),
          }}
        >
          <button id="next" variant="contained">
            결제 완료
          </button>
        </Link>
      </div>
    </div>
  );
}
export default Pay;
