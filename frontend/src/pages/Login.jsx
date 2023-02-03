import {
  Button,
  Grid,
  Link,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { Form, Formik } from "formik";
import React, { useEffect, useState } from "react";

import { makeStyles } from "@mui/styles";

import { loginSchema } from "../schema";
import { useAuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

import Success from "../components/SnackBar/Success";

const useStyles = makeStyles((theme) => ({
  container: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    // height: "100vh",
    // width: "100vw",
  },
  form: {
    padding: theme.spacing(2),
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    gap: theme.spacing(1),
    minWidth: 500,
  },
  field: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
  link: {
    textDecoration: "none",
    "& :hover": {
      cursor: "pointer",
    },
  },
}));

const Login = () => {
  const classes = useStyles();
  const { setUserInfo, setUserId, login,userId } = useAuthContext();
  const [loggedIn, setLoggerIn] = useState(false);
  const navigate = useNavigate();
  useEffect(()=>{
    navigate("/");
  },[])
  return (
    <Grid container justifySelf={"center"} className={classes.container}>
      <Paper className={classes.form}>
        <Typography variant={"h3"}>Login</Typography>
        <Success open={loggedIn} message={"Authentication Successful"} />
        <Formik
          initialValues={{
            username: "",
            password: "",
          }}
          validationSchema={loginSchema}
          onSubmit={(values) => {
            console.log(values);
            login(values)
              .then((res) => res.json())
              .then((data) => {
                setUserInfo(data);
                setUserId(data.user.id);
                setLoggerIn(true);
                setTimeout(() => {
                  navigate("/");
                }, 2000);
              })
              .catch((err) => console.log(err));
          }}
        >
          {({
            handleChange,
            handleBlur,
            handleSubmit,
            values,
            errors,
            touched,
            isValid,
            dirty,
          }) => (
            <Form className={classes.form}>
              <TextField
                className={classes.field}
                fullWidth
                id="username"
                name="username"
                label="Username"
                value={values.username}
                onChange={handleChange("username")}
                onBlur={handleBlur("username")}
                error={touched.username && Boolean(errors.username)}
                helperText={touched.username && errors.username}
              />
              <TextField
                className={classes.field}
                fullWidth
                id="password"
                name="password"
                label="Password"
                value={values.password}
                onChange={handleChange("password")}
                onBlur={handleBlur("password")}
                error={touched.password && Boolean(errors.password)}
                helperText={touched.password && errors.password}
              />
              <Button
                sx={{ p: 2 }}
                type={"submit"}
                variant={"contained"}
                disabled={Boolean(!isValid || !dirty)}
                fullWidth
                onClick={handleSubmit}
              >
                Login
              </Button>
              <Typography>
                Don't have an accout ?{" "}
                <Link href="/register" className={classes.link}>
                  Register
                </Link>
              </Typography>
            </Form>
          )}
        </Formik>
      </Paper>
    </Grid>
  );
};

export default Login;
