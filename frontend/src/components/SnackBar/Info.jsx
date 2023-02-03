import React from 'react'
import Bar from './Bar'

const Info = ({open,message}) => {
  return (
    <Bar  _open={open}>
      <Alert severity="info" sx={{ width: "100%" }}>{message}</Alert>
    </Bar>
  )
}

export default Info
