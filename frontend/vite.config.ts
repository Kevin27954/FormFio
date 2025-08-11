import { defineConfig, loadEnv } from "vite";
import path from "node:path";
import react from "@vitejs/plugin-react-swc";
import tailwindcss from "@tailwindcss/vite";

// https://vite.dev/config/
export default defineConfig(({ command: _command, mode }) => {
  const env = loadEnv(mode, process.cwd(), "");

  function getServer(): string {
    const ENV_VAL = env.VITE_ENV;

    if (ENV_VAL === "dev") {
      return env.VITE_DEV_SERVER!;
    } else if (ENV_VAL === "prod") {
      return env.VITE_PROD_SERVER!;
    } else {
      throw new Error(`ENV ${ENV_VAL} UNKNOWN`);
    }
  }

  return {
    plugins: [react(), tailwindcss()],
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "./src"),
      },
    },
    server: {
      proxy: {
        "/api": {
          target: getServer(),
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/api/, ""),
        },
      },
    },
  };
});
