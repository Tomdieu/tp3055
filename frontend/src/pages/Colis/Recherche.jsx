import {
  Box,
  Button,
  Grid,
  MenuItem,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import React, { useEffect, useState } from "react";

import { makeStyles } from "@mui/styles";
import ApiService from "../../utils/ApiService";
import { useAuthContext } from "../../context/AuthContext";

import moment from "moment";

const useStyles = makeStyles((theme) => ({
  container: {
    backgroundColor: "#rgb(51, 51, 51)	",
    padding: theme.spacing(2),
  },
  box: {
    display: "flex",
    justifyContent: "space-around",
    alignItems: "center",
    gap: theme.spacing(2),
    width: "100%",
  },
  innerBox: {
    flex: 1,
  },
}));

const states = [
  {
    value: "",
    label: "Select A State",
  },
  {
    value: "ATTENTE",
    label: "En ATTENTE",
  },
  {
    value: "EXPEDIE",
    label: "Deja Expedier",
  },
  {
    value: "ARRIVER",
    label: "Deja Arriver",
  },
  {
    value: "RETIRER",
    label: "Deja Retire",
  },
];

const columns = [
  { headerName: "Id", field: "id" },
  { headerName: "Description", field: "description", width: 150 },
  { headerName: "Client CNI", field: "clientCNI", width: 150 },
  { headerName: "Client Name", field: "clientName", width: 150 },
  { headerName: "Reciever Name", field: "recieverName", width: 150 },
  { headerName: "Reciever Phone Number", field: "recieverPhone", width: 150 },
  { headerName: "From Town", field: "fromTown", width: 150 },
  { headerName: "To Town", field: "toTown", width: 150 },
  {
    headerName: "state",
    field: "state",
    width: 150,
    editable: true,
    type: "singleSelect",
    valueOptions: ["ATTENTE", "EXPEDIE", "ARRIVER", "RETIRER"],
  },
  {
    headerName: "Save At",
    field: "saveDate",
    width: 150,
    type: "date",
    renderCell: (params) =>
      moment(params.row.saveDate).format("YYYY-MM-DD HH:mm:ss"),
  },
];

const Recherche = () => {
  const classes = useStyles();
  const { userInfo } = useAuthContext();
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [state, setState] = useState("");
  const [colis, setColis] = useState([]);
  const [action,setAction] = useState("")
  const [loading, setLoading] = useState(false);
  const [selectedRows, setSelectedRows] = useState([]);

  const [selectedIds, setSelectedIds] = useState([]);

  const [snackMessages, setSnackMessages] = useState([]);

  useEffect(() => {
    if (colis) {
      setLoading(false);
    }
  }, [colis]);

  const loadColis = () => {
    setLoading(true);
    ApiService.getColisListByTown(userInfo.user.town)
      .then((res) => res.json())
      .then((data) => {
        setColis(data || []);
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    loadColis();
  }, []);

  const formatDate = (date) => {
    return moment(date).format("DD/MM/YYYY");
  };

  useEffect(() => {
    if (fromDate && toDate && state !== "") {
      setLoading(true);

      ApiService.filterColis(
        userInfo.user.town,
        formatDate(fromDate),
        formatDate(toDate),
        state
      )
        .then((res) => res.json())
        .then((data) => {
          console.log(data);

          setColis(data || []);
        })
        .catch((err) => console.log(err));
    }
    if (fromDate && toDate && state === "") {
      setLoading(true);

      ApiService.filterColis(
        userInfo.user.town,
        formatDate(fromDate),
        formatDate(toDate)
      )
        .then((res) => res.json())
        .then((data) => {
          setColis(data || []);
          console.log(data);
        })
        .catch((err) => console.log(err));
    }
  }, [fromDate, toDate, state]);

  const handleExpedition = (colis) => {
    setLoading(true);
    ApiService.sendColis(userInfo.user.id, colis.id)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        // setSnackMessages(data);
        setLoading(false);
        setTimeout(() => {
          loadColis();
        }, 1000);
      })
      .catch((err) => console.log(err));
  };

  const handleArriver = (colis) => {
    setLoading(true);
    ApiService.markColisAsArrive(userInfo.user.id, colis.id)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        // setSnackMessages(data);
        setLoading(false);
        setTimeout(() => {
          loadColis();
        }, 1000);
      })
      .catch((err) => console.log(err));
  };

  const handleRetirer = (colis) => {
    setLoading(true);
    ApiService.withdDrawColis(userInfo.user.id, colis.id)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        // setSnackMessages(data);
        setLoading(false);
        setTimeout(() => {
          loadColis();
        }, 1000);
      })
      .catch((err) => console.log(err));
  };

  const handleAction = (value) => {
    console.log(value);
    selectedRows.map((row) => {
      if (value === "EXPEDIE") {
        if (row.state === "ATTENTE") {
          console.log("In");
          return handleExpedition(row);
        }
      }

      if ("ARRIVER" === value) {
        if (row.state === "EXPEDIE") {
          return handleArriver(row);
        }
      }

      if ("RETIRER" === value) {
        if (row.state == "ARRIVER") {
          return handleRetirer(row);
        }
      }
    });
  };
  return (
    <Grid container className={classes.container} component={Paper}>
      <Grid item md>
        <Typography variant="h3" textAlign={"left"}>
          Search A Package
        </Typography>
        <Box className={classes.box}>
          <Box className={classes.innerBox}>
            <Typography textAlign={"left"}>From Date</Typography>
            <TextField
              name="fromDate"
              type={"date"}
              fullWidth
              value={fromDate}
              onChange={(e) => setFromDate(e.target.value)}
            />
          </Box>
          <Box className={classes.innerBox}>
            <Typography textAlign={"left"}>To Date</Typography>
            <TextField
              name="toDate"
              type={"date"}
              fullWidth
              value={toDate}
              onChange={(e) => setToDate(e.target.value)}
            />
          </Box>
          <Box className={classes.innerBox}>
            <Typography textAlign={"left"}>State</Typography>
            <TextField
              select
              fullWidth
              value={state}
              onChange={(e) => setState(e.target.value)}
            >
              {states.map((st) => (
                <MenuItem key={st.value} value={st.value}>
                  {st.label}
                </MenuItem>
              ))}
            </TextField>
          </Box>
        </Box>
        {selectedIds.length > 0 && (
          <Box
            sx={(theme) => ({
              margin: theme.spacing(2),
              maxWidth: 300,
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              gap: theme.spacing(2),
            })}
          >
            <Box sx={{ flex: 1 }}>
              <Typography textAlign={"left"}>Actions</Typography>
              <TextField select fullWidth value={action} onChange={(e)=>setAction(e.target.value)}>
                {["", "EXPEDIE", "ARRIVER", "RETIRER"].map((action) => (
                  <MenuItem key={action} value={action}>
                    {action}
                  </MenuItem>
                ))}
              </TextField>
            </Box>
            <Box sx={{display: "flex",
              justifyContent: "center",
              alignItems: "center", }}>
              <Button variant="contained" onClick={()=>handleAction(action)}>Valider</Button>
            </Box>
          </Box>
        )}

        <DataGrid
          sx={(theme) => ({
            margin: theme.spacing(2),
            maxHeight: 600,
          })}
          headerName={"Colis"}
          rows={colis}
          columns={columns}
          pageSize={50}
          loading={loading}
          rowsPerPageOptions={[10, 20, 30, 50]}
          checkboxSelection
          disableSelectionOnClick
          experimentalFeatures={{ newEditingApi: true }}
          onSelectionModelChange={(ids) => {
            setSelectedIds(ids);
            console.log(ids);
            const selectedRowsData = ids.map((id) =>
              colis.find((row) => row.id === id)
            );
            console.log(selectedRowsData);
            setSelectedRows(selectedRowsData);
          }}
        />
      </Grid>
    </Grid>
  );
};

export default Recherche;
