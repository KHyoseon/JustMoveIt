import React, { useState } from "react";
import "./Seats.css";

function Seats(props) {
  const seatsCatogory = [
    // {
    //   name: "Club",
    //   price: 236,
    //   seats: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    //   occupied: [2, 3]
    // },
    {
      name: "스크린",
      seats: [
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      ],
      // occupied: [3,4,5,6,8,10,15,16,17,18,26,]
      occupied: [],
    },
  ];
  // console.log(props["data"]);
  const seatData = props["data"].split(",");
  // console.log(seatData);
  for (const iterator of seatData) {
    // console.log(iterator);
    let result = 0;
    switch (iterator[0]) {
      case "A":
        break;
      case "B":
        result = 5;
        break;
      case "C":
        result = 10;
        break;
      case "D":
        result = 15;
        break;
      default:
        break;
    }
    result += Number(iterator.slice(1));
    seatsCatogory[0]["occupied"].push(result);
    // console.log(result);
  }

  // const [selectedSeats, setSelectedSeats] = useState([]);
  const [selectedCatogory, setSelectedCatogory] = useState(null);

  const handleOnClick = (seat, catogory) => {
    const isSelected = props.selectedSeats.indexOf(seat) > -1;
    if (isSelected) {
      const newSelectedSeats = props.selectedSeats.filter(
        (selectedSeat) => selectedSeat !== seat
      );
      props.setSelectedSeats(newSelectedSeats);
    } else {
      if (
        props.selectedSeats.length !== 0 &&
        selectedCatogory &&
        selectedCatogory.name !== catogory.name
      ) {
        alert("Select seats from same catogory");
      } else if (props.selectedSeats.length >= props.person) {
        alert("인원 보다 많은 좌석을 선택했어요!");
      } else {
        props.setSelectedSeats([...props.selectedSeats, seat]);
        setSelectedCatogory(catogory);
      }
    }
    console.log(props.selectedSeats);
  };

  return (
    <div className="Seats">
      <h3>좌석을 선택하세요</h3>
      <div className="screen">
        {seatsCatogory.map((catogory) => {
          const noOfRows = Math.ceil(catogory.seats.length / 5);
          const newSeatList = [];
          for (var i = 0; i < noOfRows; i++) {
            newSeatList[i] = catogory.seats.slice(i * 5 + 0, i * 5 + 5);
          }
          // console.log(newSeatList);
          return (
            <div className="seats-section">
              <h5>{catogory.name}</h5>
              {newSeatList.map((seats, i) => (
                <div key={i} className="seats">
                  {seats.map((seat, j) => {
                    const isSelected = props.selectedSeats.indexOf(seat) > -1;
                    const isOccupied = catogory.occupied.indexOf(seat) > -1;
                    return (
                      <div
                        key={`seat-${seat + j}`}
                        className={`seat ${isSelected ? "selected" : ""} ${
                          isOccupied ? "occupied" : ""
                        }`}
                        onClick={() => {
                          if (!isOccupied) {
                            handleOnClick(seat, catogory);
                          } else {
                          }
                        }}
                      />
                    );
                  })}
                </div>
              ))}
            </div>
          );
        })}
      </div>
    </div>
  );
}
export default Seats;
