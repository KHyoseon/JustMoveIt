import React, { useState, useEffect } from "react";
import Header from "../Elements/Header";
import Footer from "../Elements/Footer";
import DefaultRouter from "./DefaultRouter";

//////1111
// import Face from "../AI/Face";
//////

function DefaultPage(props) {
  // console.log(1, props.state);

  ///////////////1111
  // const [AppAge, setAge] = useState("0");
  // const [AppGender, setGender] = useState("1");
  // const [moviesData, setMoviesData] = useState();

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
  //   console.log("아 돌아간다~", moviesData);
  // }, [AppAge]);
  //////////////
  return (
    <div className="DefaultPage">
      {/* <DefaultRouter /> */}
      <Header />
      <DefaultRouter state={props.state} />
      {/* ////////////////////////////111111111111 */}
      {/* <Face
        setAge={setAge}
        setGender={setGender}
        AppAge={AppAge}
        AppGender={AppGender}
      ></Face> */}
      {/* //////////////////////////// */}
      <Footer />
    </div>
  );
}
export default DefaultPage;
