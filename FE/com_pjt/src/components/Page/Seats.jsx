import React, {StyleSheet, useState, useRef } from 'react'
import styles from "./Seats.css";

const seatsCatogory = [
  // {
  //   name: "Club",
  //   price: 236,
  //   seats: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
  //   occupied: [2, 3]
  // },
  {
    name: "좌석 선택",
    seats: [
      1,2,3,4,5,6,7,8,9,10,
      11,12,13,14,15,16,17,18,19,20,
    ],
    occupied: [3,4,5,6,8,10,15,16,17,18,26,]
  }
];

function Seats() {
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [selectedCatogory, setSelectedCatogory] = useState(null);

  const handleOnClick = (seat, catogory) => {
    const isSelected = selectedSeats.indexOf(seat) > -1;
    if (isSelected) {
      const newSelectedSeats = selectedSeats.filter(
        (selectedSeat) => selectedSeat !== seat
      );
      setSelectedSeats(newSelectedSeats);
    } else {
      if (
        selectedSeats.length !== 0 &&
        selectedCatogory &&
        selectedCatogory.name !== catogory.name
      ) {
        alert("Select seats from same catogory");
      } else if (selectedSeats.length > 4) {
        alert("인원 보다 많은 좌석을 선택했어요!");
      } else {
        setSelectedSeats([...selectedSeats, seat]);
        setSelectedCatogory(catogory);
      }
    }
  };

  {/* 대충 스크린 모양
  비어있는 좌석 모양, 선택된 좌석 모양, 예약된 좌석 모양
          1 2 3 4 5
        A 
        B
        C
        D
      */}

  return(
    <div className='Seats'>
      <div className="screen">
        {seatsCatogory.map((catogory) => {
            const noOfRows = Math.ceil(catogory.seats.length / 5);
            const newSeatList = [];
            for (var i = 0; i < noOfRows; i++) {
            newSeatList[i] = catogory.seats.slice(i * 5 + 0, i * 5 + 5);
            }
            console.log(newSeatList);
            return (
            <div className="seats-section">
                <h4>{catogory.name}</h4>
                {newSeatList.map((seats, i) => (
                <div key={i} className="seats">
                    {seats.map((seat, j) => {
                    const isSelected = selectedSeats.indexOf(seat) > -1;
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
    {/* <div className="total">
        <span> Seats Count: {selectedSeats.length}</span>{" "}
        <span>
        Price: $
        {selectedCatogory ? selectedSeats.length * selectedCatogory.price : 0}
        </span>
    </div> */}
    </div>
  </div>
  )
}
export default Seats;

//   function Seats() {

//     return (
        
//     )
//   }
//   export default Seats

