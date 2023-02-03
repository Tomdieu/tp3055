import "./App.css";

import { createTheme, responsiveFontSizes, ThemeProvider } from "@mui/material";
import { ProSidebarProvider } from "react-pro-sidebar";
import Navigation from "./navigation";
import AuthProvider from "./provider/AuthProvider";

const theme = createTheme();
const responsive = responsiveFontSizes(theme);
const App = () => {
  return (
    <ThemeProvider theme={responsive}>
      <AuthProvider>
        <ProSidebarProvider>
          <Navigation />
        </ProSidebarProvider>
      </AuthProvider>
    </ThemeProvider>
  );
};

export default App;
