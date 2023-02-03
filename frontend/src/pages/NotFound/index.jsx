import { ErrorOutline } from '@mui/icons-material'
import { Container, Typography } from '@mui/material'
import { makeStyles } from '@mui/styles'
import React from 'react'

const useStyles = makeStyles((theme)=>({
  root:{
    display:"flex",
    flexDirection:"row",
    alignItems:"center",
    marginTop:theme.spacing(10),
    backgroundColor:"#fff",
    padding:theme.spacing(2),
    borderRadius:theme.shape.borderRadius,
    gap:theme.spacing(2)
  },
  icon:{
    fontSize:"10rem",
    color:theme.palette.error.main
  },
  message:{
    marginTop:theme.spacing(2),
    color:theme.palette.error.main
  }
}))

const index = () => {
  const classes = useStyles();
  return (
    <Container maxWidth="sm">
      <div className={classes.root}>
        <ErrorOutline className={classes.icon}/>
        <Typography variant='h5' className={classes.message}>
          404 NOT FOUND
        </Typography>
      </div>
    </Container>
  )
}

export default index
