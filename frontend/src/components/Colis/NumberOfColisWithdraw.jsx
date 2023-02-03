import { Remove, SendRounded } from "@mui/icons-material";
import { Box, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import React from "react";

import {ImBoxRemove} from "react-icons/im"

const useStyles = makeStyles((theme) => ({
  container: {
    // background: "rgba(255,255,255,.5)",
    opacity: 0.5,
    borderRadius: theme.shape.borderRadius,
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: theme.spacing(2),
    gap: theme.spacing(1),
    cursor: "pointer",
    background: " rgba( 119, 153, 221, 0.5 )",
    boxShadow: "0 8px 32px 0 rgba( 31, 38, 135, 0.37 )",
    backdropFilter: "blur( 6px )",
    "-webkit-backdrop-filter": "blur( 6px )",
    border: "1px solid rgba( 255, 255, 255, 0.18 )",
  },
  icon: {
    fontSize: 100,
    transform:'rotate: "45deg"',
  },
}));
const NumberOfColisWithdraw = ({value=0}) => {
  const classes = useStyles();

  return (
    <Box className={classes.container}>
      <Box>
      <Typography fontWeight={"600"} variant={"h1"}>
        {value}
      </Typography>
      <Typography>Withdraw</Typography>
      </Box>
      <ImBoxRemove className={classes.icon} />
    </Box>
  );
};

export default NumberOfColisWithdraw;
