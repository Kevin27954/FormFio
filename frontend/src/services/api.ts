import getServer from "@/util/getserver";

/**
 * This returns a json of type T (you pass in the type that you expect), so it only consumes JSON APIs
 */
const apiRequest = async <T>(uri: string, options: any): Promise<T> => {
  uri = parseUrl(uri);
  const url = `${getServer()}${uri}`;

  const config = {
    headers: {
      "Content-Type": "application/json",
    },
    ...options,
  };

  return fetch(url, config)
    .then((res) => {
      if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
      }

      return res.json();
    })
    .then((json) => {
      return json as T;
    });
};

function parseUrl(uri: string) {
  const ENV = import.meta.env.VITE_ENV;
  if (ENV === "prod") {
    return uri.substring(4);
  }

  return uri;
}

export { apiRequest };
