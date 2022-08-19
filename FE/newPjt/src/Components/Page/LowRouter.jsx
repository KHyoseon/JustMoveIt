import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MovieSelectLow from "./MovieSelectLow.jsx";
import MovieDetailLow from "./MovieDetailLow.jsx";
import PayLow from "./PayLow";
import PayendLow from "./PayendLow";

const LowRouter = (props) => {
  return (
    <Routes>
      <Route path="" element={<MovieSelectLow />}></Route>
      <Route path=":movieCode" element={<MovieDetailLow />}></Route>
      <Route path=":movieCode/pay" element={<PayLow />}></Route>
      <Route path=":movieCode/pay/payend" element={<PayendLow />}></Route>
    </Routes>
  );
};
export default LowRouter;
