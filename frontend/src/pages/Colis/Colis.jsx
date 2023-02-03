import React, { useEffect,useState } from "react";
import ApiService from "../../utils/ApiService";

import {
  TableContainer,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  Grid,
  Typography,
} from "@mui/material";
import {useAuthContext} from '../../context/AuthContext'

const column = [{ field: "id", headerName: "ID", width: 70 }];

const Colis = () => {
  const [row, setRow] = useState([]);
  const {userInfo} = useAuthContext()
  useEffect(() => {
    ApiService.getColisListByTown(userInfo.user.town)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setRow(data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  return (
    <Grid container sx={(theme)=>({padding:theme.spacing(2),backgroundColor:"rgb(51, 51, 51)	"})} width={'100%'}>

      <Grid item columns={12} md>
      <Typography variant="h3" textAlign={"left"}>Colis</Typography>
      <TableContainer component={Paper} sx={{width:'100%'}}>
        <Table aria-label="colis-table" stickyHeader>
          <TableHead>
            <TableRow sx={(theme)=>({backgroundColor:theme.palette.primary})}>
              <TableCell>ID</TableCell>
              <TableCell>Description</TableCell>
              <TableCell>Sender CNI</TableCell>
              <TableCell>Sender Name</TableCell>
              <TableCell>Reciever Name</TableCell>
              <TableCell>Reciever Phone Number</TableCell>
              <TableCell>From Town</TableCell>
              <TableCell>To Town</TableCell>
              <TableCell>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {row?.map((data) => (
              <TableRow
                key={data.id}
                sx={{ "&:last-child td,&:last-child th": { border: 0 } }}
              >
                <TableCell>{data.id}</TableCell>
                <TableCell>{data.description}</TableCell>
                <TableCell>{data.clientCNI}</TableCell>
                <TableCell>{data.clientName}</TableCell>
                <TableCell>{data.recieverName}</TableCell>
                <TableCell>{data.recieverPhone}</TableCell>
                <TableCell>{data.fromTown}</TableCell>
                <TableCell>{data.toTown}</TableCell>
                <TableCell>{data.state}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      </Grid>
    </Grid>
  );
};

export default Colis;
