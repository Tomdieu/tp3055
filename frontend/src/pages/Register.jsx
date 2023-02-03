import { Button, Grid, Paper, TextField, Typography,MenuItem,Link } from '@mui/material'
import { Form, Formik } from 'formik';
import React from 'react'

import { registerSchema } from '../schema';

import { makeStyles } from '@mui/styles'

const useStyles = makeStyles((theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    // height: '100vh', 
  },
  form:{
    display:'flex',
    flexDirection:'column',
    alignItems:"center",
    justifyContent:"center",
    gap:theme.spacing(2),
    minWidth:500,
    margin:theme.spacing(1),
    padding:theme.spacing(2)
  }
}));

var towns = [  "YAOUNDE",  "DOUALA",  "BAFOUSSAM",  "NGAOUNDERE",  "MAROUA",  "BAMENDA",  "GAROUA",  "KOUSSEI",  "BERTOUA",  "LIMBE"];

const Register = () => {
  const classes = useStyles();
  return (
    <Grid container justifySelf={"center"} className={classes.container}>
      <Paper>
        <Typography variant='h3'>Create Account</Typography>
        <Formik
          validationSchema={registerSchema}
          initialValues={{
            username:"",
    firstname:"",
    lastname:"",
    password:"",
    confirmPassword:"",
    town:"",
    isSaver:false,
    isSender:false
          }}
        >
          {({values,handleBlur,handleSubmit,handleChange,errors,touched,isValid})=>(
            <Form className={classes.form}>
            <TextField 
              id="username"
              name='username'
              label={"Username"}
              value={values.username}
              onChange={handleChange('username')}
              onBlur={handleBlur("username")}
              error={touched.username && Boolean(errors.username)}
              helperText={touched.username && errors.username}
              fullWidth
            />
            <TextField 
              id="firstname"
              name='firstname'
              label={"First name"}
              value={values.firstname}
              onChange={handleChange('firstname')}
              onBlur={handleBlur("firstname")}
              error={touched.firstname && Boolean(errors.firstname)}
              helperText={touched.firstname && errors.firstname}
              fullWidth

            />
            <TextField 
              id="lastname"
              name='lastname'
              label={"Last name"}
              value={values.lastname}
              onChange={handleChange('lastname')}
              onBlur={handleBlur("lastname")}
              error={touched.lastname && Boolean(errors.lastname)}
              helperText={touched.lastname && errors.lastname}
              fullWidth

            />
            <TextField 
              id="password"
              name='password'
              label={"Password"}
              value={values.password}
              onChange={handleChange('password')}
              onBlur={handleBlur("password")}
              error={touched.password && Boolean(errors.password)}
              helperText={touched.password && errors.password}

              fullWidth

            />
            <TextField 
              id="confirmPassword"
              name='confirmPassword'
              label={"Confirm password"}
              value={values.confirmPassword}
              onChange={handleChange('confirmPassword')}
              onBlur={handleBlur("confirmPassword")}
              error={touched.confirmPassword && Boolean(errors.confirmPassword)}
              helperText={touched.confirmPassword && errors.confirmPassword}
              fullWidth

            />
            <TextField
              select
              id="town"
              name='town'
              label={"Town"}
              value={values.town}
              onChange={handleChange('town')}
              onBlur={handleBlur("town")}
              error={touched.town && Boolean(errors.town)}
              helperText={touched.town && errors.town}
              fullWidth

            >
            {towns.map((town)=>(
              <MenuItem key={town} value={town}>
              {town}
            </MenuItem>
            ))}
            </TextField>
            <Button disabled={Boolean(isValid)} fullWidth type="submit" variant="contained" color="primary">Create Account</Button>
            <Typography>Already have an account ? <Link href="/login">Login</Link></Typography>
          </Form>
          )}
        </Formik>

      </Paper>
    </Grid>
  )
}

export default Register
