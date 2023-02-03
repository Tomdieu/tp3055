import { Alert } from "@mui/material";
import React from "react";
import Bar from "./Bar";

const Error = ({message,open}) => {
  return (
    <Bar _open={open}>
      <Alert severity="error" sx={{ width: "100%" }}>
        {message}
      </Alert>
    </Bar>
  );
};

export default Error;
