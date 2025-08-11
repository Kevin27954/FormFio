/**
 * Gets the server, changes depending on the env set in .env
 * @returns true if dev false if prod
 */
function getServer(): string {
  const ENV = import.meta.env.VITE_ENV;

  if (ENV === "dev") {
    return "";
  } else if (ENV === "prod") {
    return import.meta.env.VITE_PROD_SERVER;
  } else {
    throw new Error(`ENV ${ENV} UNKNOWN`);
  }
}

export default getServer;
