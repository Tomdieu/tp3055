import { Alert } from "@mui/material";
import React from "react";
import Bar from "./Bar";

const Warning = ({ message, open }) => {
  return (
    <Bar _open={open}>
      <Alert severity="warning" sx={{ width: "100%" }}>
        {message}
      </Alert>
    </Bar>
  );
};

export default Warning;
