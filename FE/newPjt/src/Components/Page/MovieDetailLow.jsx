import React, { useState } from "react";
import { Button, Modal, Box } from "@mui/material";
import { Link, useNavigate, useLocation } from "react-router-dom";
import SeatData from "./SeatsData";
import Seat from "./Seats";
import PersonButton from "../Elements/PersonButton";
import "./MovieDetail.css";

const style = {
  position: "absolute",
  top: "70%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  height: 380,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

function MovieDetail() {
  // const axios = require("axios").default;
  // console.log("https://i7d207.p.ssafy.io/api/movies/" + movie_code[0]);
  // axios
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

  const navigate = useNavigate();

  const goBack = () => {
    navigate(-1);
  };

  let juso = window.location.href.split("/");
  const movie_code = juso.slice(-1);
  let movie_data;
  // console.log(movie_code[0]);

  const [open, setOpen] = useState(false);
  const [seatInfo, setSeatInfo] = useState("11");
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [defaultPerson, setDefaultPersonInfo] = useState(0);
  const [kisPerson, setkidPersonInfo] = useState(0);
  const [ticketSeats, setTicketSeatData] = useState();
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [moviePlayingId, setMoviePlayingId] = useState();

  const handleSeatData = (id, sData, e) => {
    console.log({ sData });
    setDefaultPersonInfo(0);
    setkidPersonInfo(0);
    setSelectedSeats([]);
    setSeatInfo(sData);
    setMoviePlayingId(id);
    console.log(id);
  };

  const location = useLocation();
  const state = location.state;

  // console.log(state["moviePlayingInfoList"]);
  let moviePlayingInfo = state["moviePlayingInfoList"];
  const timeButton = [];

  for (const info of moviePlayingInfo) {
    // console.log(info["tickets"]);
    const ticketsData = [];

    // console.log("무비", info);
    const infoId = info["moviePlayingInfoId"];
    // console.log("무비인포", infoId);

    for (const ticket of info["tickets"]) {
      // console.log(ticket["seat"]);
      for (const seat of ticket["seat"].split(",")) {
        ticketsData.push(seat);
      }
    }
    ticketsData.sort();

    let k = "";
    for (const iterator of ticketsData) {
      k = k + iterator + ",";
    }

    // console.log(k);
    timeButton.push(
      <span className="time_button">
        <button
          id="timeButton"
          variant="outlined"
          onClick={(e) => {
            handleSeatData(infoId, k, e);
          }}
        >
          {info["startTime"]}
        </button>
      </span>
    );
  }
  function ad() {
    console.log(defaultPerson);
    // return defaultPerson;
  }
  function kid() {
    console.log(kisPerson);
    // return kisPerson;
  }

  let [ticketData, setTicketData] = useState({
    movie: state["title"],
    adult: 1,
    child: 2,
    seats: { selectedSeats },
  });

  return (
    <div className="MovieDetailLow">
      <div className="make_lower"></div>
      <h1 id="timeSelect">시간 선택</h1>

      <div id="container">
        <div id="box2">
          <h2>{state["title"]}</h2>
          <div className="align_container">
            <p>평점 : {state["rating"]}</p>
            <p>총 관객수 : {Number(state["totalCustomer"])} 명</p>
            <p>연령 : {state["ageLimit"].slice(0, 3)}</p>
          </div>
        </div>
      </div>

      <br />
      <hr />
      <br />

      <div id="seat_section">
        <div className="reservation">
          <div className="align_container">
            <div id="timeContainer" className="container">
              <div id="timeBox">{timeButton}</div>
            </div>
            <SeatData data={seatInfo} />
          </div>
        </div>
      </div>

      <div>
        <button id="before" onClick={goBack}>
          뒤로가기
        </button>
        <button id="next" onClick={handleOpen}>
          좌석 선택
        </button>
      </div>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className="breadCrumble">
            <h2>예매 1/2</h2>
          </div>
          <h3>인원을 선택하세요</h3>
          <div className="modal_box">
            <PersonButton
              text="성인"
              chosePerson={defaultPerson}
              default={defaultPerson}
              kid={kisPerson}
              setPerson={setDefaultPersonInfo}
              // setTicketData={setTicketData}
            />
            <PersonButton
              text="청소년"
              chosePerson={kisPerson}
              default={defaultPerson}
              kid={kisPerson}
              setPerson={setkidPersonInfo}
              // setTicketData={setTicketData}
            />
          </div>
          {/* <Link to='/pay'><Button>결제</Button></Link> */}
          {/* <Link to="./pay" state={ticketData}> */}

          <div className="button_section">
            <Button variant="outlined" onClick={handleClose}>
              취소
            </Button>
            <span>&nbsp;&nbsp;&nbsp;</span>

            <ChildModal />
          </div>
        </Box>
      </Modal>
    </div>
  );

  function ChildModal() {
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => {
      setOpen(true);
    };
    const handleClose = () => {
      setOpen(false);
    };

    return (
      <React.Fragment>
        <Button variant="contained" onClick={handleOpen}>
          다음
        </Button>
        <Modal
          hideBackdrop
          open={open}
          aria-labelledby="child-modal-title"
          aria-describedby="child-modal-description"
        >
          <Box sx={style}>
            <div className="breadCrumble">
              <h2>예매 2/2</h2>
            </div>
            <Seat
              data={seatInfo}
              person={defaultPerson + kisPerson}
              setSelectedSeats={setSelectedSeats}
              selectedSeats={selectedSeats}
            />
            <div className="button_section">
              <Button variant="outlined" onClick={handleClose}>
                이전
              </Button>
              <span>&nbsp;&nbsp;&nbsp;</span>
              <Link
                to="./pay"
                style={{ textDecoration: "none" }}
                state={{
                  movie: state["title"],
                  img: state["img"],
                  adult: { defaultPerson },
                  child: { kisPerson },
                  seats: { selectedSeats },
                  PlayingInfoID: { moviePlayingId },
                }}
              >
                <Button variant="contained">결제</Button>
              </Link>
            </div>
          </Box>
        </Modal>
      </React.Fragment>
    );
  }
}

export default MovieDetail;
