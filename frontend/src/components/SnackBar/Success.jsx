import { Alert } from '@mui/material'
import React from 'react'
import Bar from './Bar'

const Success = ({open,message}) => {
  return (
    <Bar  _open={open}>
      <Alert severity="success" sx={{ width: "100%" }}>
        {message}
      </Alert>
    </Bar>
  )
}

export default Success
