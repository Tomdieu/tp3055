import {
  Sidebar,
  Menu,
  MenuItem,
  useProSidebar,
  SubMenu,
} from "react-pro-sidebar";

import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import PeopleOutlinedIcon from "@mui/icons-material/PeopleOutlined";
import ContactsOutlinedIcon from "@mui/icons-material/ContactsOutlined";
import ReceiptOutlinedIcon from "@mui/icons-material/ReceiptOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";

import {AiOutlineDashboard} from 'react-icons/ai'

import React from "react";
import { Box, Typography } from "@mui/material";
import { AccountCircle, Logout } from "@mui/icons-material";

const index = () => {
  const { collapseSidebar, toggleSidebar, collapsed, toggled, broken, rtl } =
    useProSidebar();

  const toggle = () => {
    toggleSidebar();
    if (toggled) {
      console.log(true);
      collapseSidebar();
    } else {
      console.log(false);
      collapseSidebar();
    }
  };
  return (
    <Sidebar
      style={{ height: "100vh", backgroundColor: "rgba( 119, 153, 221,.5 )" }}
    >
      <Menu style={{ backgroundColor: "rgba( 119, 153, 221, 0.5 )" }}>
        
        <MenuItem
          icon={<MenuOutlinedIcon />}
          onClick={() => {
            collapseSidebar();
          }}
          style={{ textAlign: "center" }}
        >
          {" "}
          <h2>General Colis</h2>
        </MenuItem>

        <MenuItem icon={<AiOutlineDashboard />} href="/">
          DashBoard
        </MenuItem>
        <MenuItem icon={<PeopleOutlinedIcon />} href={"/add-colis"}>
          Ajouter Colis
        </MenuItem>
        <MenuItem icon={<ContactsOutlinedIcon />} href="/colis">Colis</MenuItem>
        <MenuItem icon={<ContactsOutlinedIcon />} href="/search">Rechercher Colis</MenuItem>
        <MenuItem icon={<ReceiptOutlinedIcon />}>Colis Envoyer</MenuItem>
        <MenuItem icon={<ReceiptOutlinedIcon />}>Colis Arriver</MenuItem>
        <MenuItem icon={<ReceiptOutlinedIcon />}>Colis Retirer</MenuItem>

        <MenuItem icon={<AccountCircle />}>Profile</MenuItem>
        <MenuItem icon={<Logout />}>Logout</MenuItem>
      </Menu>
    </Sidebar>
  );
};

export default index;
