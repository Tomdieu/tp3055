import { Grid, Box, Typography, Paper } from "@mui/material";
import React, { useEffect, useState } from "react";

import NumberOfColisRecieve from "../components/Colis/NumberOfColisRecieve";
import NumberOfColisSave from "../components/Colis/NumberOfColisSave";
import NumberOfColisSend from "../components/Colis/NumberOfColisSend";
import NumberOfColisWithdraw from "../components/Colis/NumberOfColisWithdraw";

import { useAuthContext } from "../context/AuthContext";

import { DataGrid } from "@mui/x-data-grid";

import ApiService from "../utils/ApiService";

import moment from "moment";
import NumberOfColisPending from "../components/Colis/NumberOfColisPending";

// import MaterialTable from 'material-table'

const columns = [
  { headerName: "Id", field: "id" },
  { headerName: "Description", field: "description", width: 150 },
  { headerName: "Client CNI", field: "clientCNI", width: 150 },
  { headerName: "Client Name", field: "clientName", width: 150 },
  { headerName: "Reciever Name", field: "recieverName", width: 150 },
  { headerName: "Reciever Phone Number", field: "recieverPhone", width: 150 },
  { headerName: "From Town", field: "fromTown", width: 150 },
  { headerName: "To Town", field: "toTown", width: 150 },
  { headerName: "state", field: "state", width: 150 },
  { headerName: "Save At", field: "saveDate", width: 150 ,renderCell:params=>moment(params.saveDate).format("YYYY-MM-DD HH:mm:ss")},
];

const Home = () => {
  const [colis, setColis] = useState([]);
  const [numRecieve, setNumRecieve] = useState(0);
  const [numSend, setNumSend] = useState(0);
  const [numWithdraw, setNumWithdraw] = useState(0);
  const [numArrive, setNumArrive] = useState(0);

  const { userInfo } = useAuthContext();
  useEffect(() => {
    ApiService.getColisListByTown(userInfo.user.town)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setColis(data);
        setNumSend(
          data.filter((x) => x.fromTown === userInfo.user.town && (x.state === 'EXPEDIE' || x.state === 'RETIRER' || x.state === 'ARRIVER')).length
        );
        setNumRecieve(
          data.filter((x) => x.toTown === userInfo.user.town &&( x.sate ==='ARRIVER' || x.state === 'RETIRER' || x.state === 'ARRIVER')).length
        );
        setNumWithdraw(
          data.filter((x) => x.toTown === userInfo.user.town && x.state == 'RETIRER').length
        );
        setNumArrive(
          data.filter((x) => x.toTown === userInfo.user.town && x.state == 'RETIRER').length
        );

      })
      .catch((err) => console.log(err));
  }, []);
  return (
      <Grid container sx={(theme)=>({backgroundColor:"rgb(51, 51, 51)	",width:'100%'})}>
        <Grid item md>
          <Typography
            style={{ color: "white", marginLeft: "2rem" }}
            textAlign={"left"}
            variant={"h2"}
          >
            General Colis
          </Typography>
          <Box
            sx={(theme) => ({
              padding: theme.spacing(2),
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              flexWrap: "wrap",
              gap: theme.spacing(3),
              "& > *": {
                width: "200px",
              },
            })}
          >
            <NumberOfColisSave value={colis.length} />
            <NumberOfColisPending value={0} />
            <NumberOfColisRecieve value={numRecieve} />
            <NumberOfColisSend value={numSend} />
            <NumberOfColisWithdraw value={numWithdraw} />
          </Box>
          <Paper
            sx={(theme) => ({
              padding: theme.spacing(2),
              margin: theme.spacing(3),
              // width: "100%",
              height: 500,
            })}
          >
            <DataGrid
              title={"Colis"}
              // sx={{ width: "100%" }}
              rows={colis}
              columns={columns}
              pageSize={100}
              rowsPerPageOptions={[10, 25, 50, 100]}
            />
          </Paper>
        </Grid>
      </Grid>
  );
};

export default Home;
