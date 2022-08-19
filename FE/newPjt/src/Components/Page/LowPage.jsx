import React from "react";
import Header from "../Elements/Header";
import Footer from "../Elements/Footer";
import LowRouter from "./LowRouter";

function LowPage(props) {
  return (
    <div className="LowPage">
      <Header />
      <Footer />
      <LowRouter />
    </div>
  );
}
export default LowPage;
