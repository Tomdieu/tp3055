import { Snackbar } from "@mui/material";
import React, { useState } from "react";

const Bar = ({ children, _open = false }) => {
  const [open, setOpen] = useState(_open);
  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  return (
    <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
      {children}
    </Snackbar>
  );
};

export default Bar;
