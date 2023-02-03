import React from "react";

import { BrowserRouter, Route, Routes } from "react-router-dom";

import Login from "../pages/login";
import Register from "../pages/register";
import Home from "../pages/Home";
import AddColis from "../pages/Colis/AddColis";
import NotFound from "../pages/NotFound";
import SideBar from "../components/SideBar";

import { useAuthContext } from "../context/AuthContext";
import { Grid } from "@mui/material";
import Colis from "../pages/Colis/Colis";

const index = () => {
  const { userId } = useAuthContext();
  console.log(userId);
  return (
    <BrowserRouter>
      {userId? (
        <div
          style={{
            display: "flex",
            width: "100vw",
            position: "absolute",
            top: 0,
            left: 0,
          }}
        >
          <SideBar />
          <Grid
            container
            height={"100vh"}
            width={"100%"}
            sx={{ position: "relative"}}
            // justifyContent={"center"}
            // alignItems={"center"}
          >
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/add-colis" element={<AddColis />} />
              <Route path="/colis" element={<Colis/>} />
              <Route path="/*" element={<NotFound />} />

            </Routes>
          </Grid>
        </div>
      ) : (
        <React.Fragment>
          
                <Routes>
                  <Route path="/login" element={<Login />} />
                  <Route exact path="/register/" element={<Register />} />
                  <Route path="/*" element={<NotFound />} />
                </Routes>
        </React.Fragment>
      )}
    </BrowserRouter>
  );
};

export default index;
