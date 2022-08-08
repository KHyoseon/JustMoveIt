import React from "react";
import {ButtonGroup, Button} from "@mui/material";
// import {pink} from "@mui/material/colors"
// import styles from "./DialPage.css";

function DialPage() {


    return(
        <div className="DialPage">
            {/* <h1>응애 나 다이얼</h1> */}
            {/* variant="contained" */}
            <ButtonGroup color="secondary" disableElevation>
                <Button>응애</Button>
                <Button>응애1</Button>
                <Button>응애2</Button>

            </ButtonGroup>
        </div>
    )
}
export default DialPage