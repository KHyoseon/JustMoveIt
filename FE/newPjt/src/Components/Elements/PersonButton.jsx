import React, { useState } from "react";
import { Button, ButtonGroup } from "@mui/material";

function PersonButton(props) {
  let [zButton, setZButton] = useState("outlined");
  let [oButton, setOButton] = useState("outlined");
  let [twButton, setTwButton] = useState("outlined");
  let [trButton, setTrButton] = useState("outlined");
  let [fButton, setFButton] = useState("outlined");

  function getVariant(data) {
    if (props.chosePerson === data) {
      return "contained";
    }
  }
  function setPersonData(data) {
    props.setPerson(data);
    switch (data) {
      case 0:
        setZButton("contained");
        setOButton("outlined");
        setTwButton("outlined");
        setTrButton("outlined");
        setFButton("outlined");
        break;
      case 1:
        setZButton("outlined");
        setOButton("contained");
        setTwButton("outlined");
        setTrButton("outlined");
        setFButton("outlined");
        break;
      case 2:
        setZButton("outlined");
        setOButton("outlined");
        setTwButton("contained");
        setTrButton("outlined");
        setFButton("outlined");
        break;
      case 3:
        setZButton("outlined");
        setOButton("outlined");
        setTwButton("outlined");
        setTrButton("contained");
        setFButton("outlined");
        break;
      case 4:
        setZButton("outlined");
        setOButton("outlined");
        setTwButton("outlined");
        setTrButton("outlined");
        setFButton("contained");
        break;

      default:
        break;
    }
  }

  function setDisabled() {
    return;
  }

  return (
    <div>
      <h4 style={{ marginBottom: "10px" }}>
        {props.text}&nbsp;&nbsp;{props.chosePerson}
      </h4>
      <ButtonGroup color="error">
        <Button onClick={() => setPersonData(0)} variant={zButton}>
          0
        </Button>
        <Button onClick={() => setPersonData(1)} variant={oButton}>
          1
        </Button>
        <Button onClick={() => setPersonData(2)} variant={twButton}>
          2
        </Button>
        <Button onClick={() => setPersonData(3)} variant={trButton}>
          3
        </Button>
        <Button onClick={() => setPersonData(4)} variant={fButton}>
          4
        </Button>
      </ButtonGroup>
    </div>
  );
}
export default PersonButton;
