import { Button } from "@mui/material";
import React from "react";
import "./Payend.css";
import { Link, useNavigate, useLocation } from "react-router-dom";

function PayendLow() {
  const location = useLocation();
  const state = location.state;

  return (
    <div className="Payend">
      <div className="make_lower"></div>
      <h1>예매가 완료되었습니다</h1>

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
                <h3>{state["adult"]}명</h3>
              </td>
            </tr>
            <tr>
              <td>
                <h3>청소년</h3>
              </td>
              <td>
                <h3>{state["child"]} 명</h3>
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
              <td>{12000 * state["adult"] + 10000 * state["child"]}</td>
            </tr>
          </table>
        </div>
      </div>

      <div className="button_section">
        <Link to="/" style={{ textDecoration: "none" }}>
          <button id="home" href="/" variant="contained">
            첫 화면으로
          </button>
        </Link>
      </div>
    </div>
  );
}
export default PayendLow;
