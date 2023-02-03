import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
// import StimulusHMR from "vite-plugin-stimulus-hmr";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react({ include: ["**/*.jsx", "**/**/*.jsx", "**/**/**/*.jsx"] }),
    // StimulusHMR(),
  ],
});
