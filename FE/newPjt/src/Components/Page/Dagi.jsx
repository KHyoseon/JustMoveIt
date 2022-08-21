import React from "react";
import { Link } from "react-router-dom";
import { Button } from "@mui/material";
import "./Dagi.css";

function Dagi(props) {
  // console.log(props.moviesData);?
  // const location = useLocation();
  // const state = location.state;
  // console.log(location);
  // console.log(2, props.state);

  return (
    <div className="Dagi">
      <div>
        <img src="img/logo1.png" className="main"></img>
      </div>
      <Link to="./default" state={props.state} id="toDefault">
        {/* <Button>평범한 ui로 이동하기</Button> */}
      </Link>
      <Link to="./low" state={props.state} id="toLow">
        {/* <Button>작은 ui로 이동하기</Button> */}
      </Link>
    </div>
  );
}
export default Dagi;
