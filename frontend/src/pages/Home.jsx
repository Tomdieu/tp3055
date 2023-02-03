import { Grid,Box, Typography } from "@mui/material";
import React from "react";

import NumberOfColisRecieve from '../components/Colis/NumberOfColisRecieve'
import NumberOfColisSave from "../components/Colis/NumberOfColisSave";
import NumberOfColisSend from '../components/Colis/NumberOfColisSend'
import NumberOfColisWithdraw from '../components/Colis/NumberOfColisWithdraw'


const Home = () => {
  return (
    <Grid container>
      <Grid item md={10}>
        <Typography style={{ color: "white", marginLeft: "2rem" }} textAlign={"left"} variant={"h2"}>General Colis</Typography>
        <Box sx={(theme)=>({
          padding:theme.spacing(2),
          display:'flex',
          justifyContent:"space-between",
          alignItems:"center",
          gap:theme.spacing(3),
          '& > *':{
            width:'400px'
          }
        })}>
          <NumberOfColisSave value={10}/>
          <NumberOfColisRecieve />
          <NumberOfColisSend />
          <NumberOfColisWithdraw />
        </Box>
      </Grid>
    </Grid>
  );
};

export default Home;
