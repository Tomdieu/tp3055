import { Box } from '@mui/material'
import { makeStyles } from '@mui/styles'
import React from 'react'
import ColisForm from '../../components/Colis/ColisForm'

const useStyles = makeStyles((theme)=>({
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100%',
    width: '100%',
    backgroundColor:"rgb(51, 51, 51)	"
  }
}))

const AddColis = () => {
  const classes = useStyles()
  return (
    <Box className={classes.container}>
      <ColisForm />
    </Box>
  )
}

export default AddColis
