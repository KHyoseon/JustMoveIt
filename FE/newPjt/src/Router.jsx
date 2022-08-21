import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Dagi from "./Components/Page/Dagi";
import DefaultPage from "./Components/Page/DefaultPage";
import LowPage from "./Components/Page/LowPage";
// import NotFound from "./Components/Page/NotFound";

const Router = (props) => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dagi state={props.state} />}></Route>
        <Route path="/low/*" element={<LowPage state={props.state} />}></Route>
        <Route
          path="/default/*"
          element={<DefaultPage state={props.state} />}
        ></Route>

        {/* <Route path="/movies" element={<MovieSelect />}></Route>
        <Route path="/movies/:movieCode" element={<MovieDetail />}></Route>
        <Route path="/movies/:movieCode/pay" element={<Pay />}></Route>
        <Route
          path="/movies/:movieCode/pay/payend"
          element={<Payend />}
        ></Route>

        <Route path="/low/movies" element={<MovieSelectLow />}></Route>
        <Route
          path="/low/movies/:movieCode"
          element={<MovieDetailLow />}
        ></Route>
        <Route path="/low/movies/:movieCode/pay" element={<PayLow />}></Route>
        <Route
          path="/low/movies/:movieCode/pay/payend"
          element={<PayendLow />}
        ></Route>
        <Route path="/*" element={<NotFound />}></Route> */}
      </Routes>
    </BrowserRouter>
  );
};
export default Router;
