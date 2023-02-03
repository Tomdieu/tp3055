import { Button, TextField, Typography, MenuItem, Paper } from "@mui/material";
import { Form, Formik } from "formik";
import React from "react";

import { makeStyles } from "@mui/styles";
import { colisSchema } from "../../schema";

import {useAuthContext} from "../../context/AuthContext"

import ApiService from "../../utils/ApiService";

const useStyles = makeStyles((theme) => ({
  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    minWidth: "600px",
    padding: theme.spacing(2),
  },
  form: {
    margin: theme.spacing(2),
    padding: theme.spacing(2),
    display: "flex",
    width: "100%",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
    gap: theme.spacing(2),
  },
}));

var towns = [
  "YAOUNDE",
  "DOUALA",
  "BAFOUSSAM",
  "NGAOUNDERE",
  "MAROUA",
  "BAMENDA",
  "GAROUA",
  "KOUSSEI",
  "BERTOUA",
  "LIMBE",
];

const ColisForm = () => {
  const classes = useStyles();
  const {userInfo,userId}=useAuthContext();
  return (
    <Paper className={classes.container}>
      <Typography variant="h3">Ajouter Un Colis</Typography>
      <Formik
        initialValues={{
          description: "",
          clientCNI: "",
          clientName: "",
          recieverName: "",
          recieverPhone: "",
          fromTown: userInfo.user.town,
          toTown: "",
        }}
        validationSchema={colisSchema}
        onSubmit={(values) => {
          console.log(values);
          ApiService.addColis(userId,values).then(res=>res.json()).then(data=>{
            console.log(data)
          }).catch((err)=>console.log(err))
        }}
      >
        {({
          values,
          dirty,
          errors,
          isValid,
          touched,
          handleSubmit,
          handleBlur,
          handleChange,
        }) => (
          <Form className={classes.form}>
            <TextField
              fullWidth
              name="description"
              value={values.description}
              onChange={handleChange("description")}
              onBlur={handleBlur("description")}
              label="Description"
              error={touched.description && Boolean(errors.description)}
              helperText={touched.description && errors.description}
            />
            <TextField
              fullWidth
              name="clientCNI"
              value={values.clientCNI}
              onChange={handleChange("clientCNI")}
              onBlur={handleBlur("clientCNI")}
              label="Client CNI"
              error={touched.clientCNI && Boolean(errors.clientCNI)}
              helperText={touched.clientCNI && errors.clientCNI}
            />
            <TextField
              fullWidth
              name="clientName"
              value={values.clientName}
              onChange={handleChange("clientName")}
              onBlur={handleBlur("clientName")}
              label="Client Name"
              error={touched.clientName && Boolean(errors.clientName)}
              helperText={touched.clientName && errors.clientName}
            />
            <TextField
              fullWidth
              name="recieverName"
              value={values.recieverName}
              onChange={handleChange("recieverName")}
              onBlur={handleBlur("recieverName")}
              label="Reciever Name"
              error={touched.recieverName && Boolean(errors.recieverName)}
              helperText={touched.recieverName && errors.recieverName}
            />
            <TextField
              fullWidth
              id="recieverPhone"
              name="recieverPhone"
              value={values.recieverPhone}
              onChange={handleChange("recieverPhone")}
              onBlur={handleBlur("recieverPhone")}
              label="Reciever Phone"
              error={touched.recieverPhone && Boolean(errors.recieverPhone)}
              helperText={touched.recieverPhone && errors.recieverPhone}
            />
            <TextField
              fullWidth
              select
              name="fromTown"
              value={values.fromTown}
              onChange={handleChange("fromTown")}
              onBlur={handleBlur("fromTown")}
              label="From Town"
              error={touched.fromTown && Boolean(errors.fromTown)}
              helperText={touched.fromTown && errors.fromTown}
              disabled
            >
              {towns.map((town) => (
                <MenuItem key={town} value={town}>
                  {town}
                </MenuItem>
              ))}
            </TextField>
            <TextField
              fullWidth
              select
              name="toTown"
              value={values.toTown}
              onChange={handleChange("toTown")}
              onBlur={handleBlur("toTown")}
              label="To Town"
              error={touched.toTown && Boolean(errors.toTown)}
              helperText={touched.toTown && errors.toTown}
            >
              {towns.map((town) => (
                <MenuItem key={town} value={town}>
                  {town}
                </MenuItem>
              ))}
            </TextField>
            <Button
              fullWidth
              variant="contained"
              disabled={Boolean(!isValid || !dirty)}
              onClick={handleSubmit}
              sx={(theme) => ({ fontWeight: "600px", p: theme.spacing(1) })}
            >
              Add Pakage
            </Button>
          </Form>
        )}
      </Formik>
    </Paper>
  );
};

export default ColisForm;
