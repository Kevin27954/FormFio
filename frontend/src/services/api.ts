import getServer from "@/util/getserver";
import SupabaseAuth from "@/lib/supabase";

/**
 * This returns a json of type T (you pass in the type that you expect), so it only consumes JSON APIs
 */
const apiRequest = async <T>(
  uri: string,
  options: any,
  withJWT = false,
): Promise<T> => {
  const auth = SupabaseAuth;
  uri = parseUrl(uri);
  const url = `${getServer()}${uri}`;

  const config = {
    headers: {
      ...(options.header === null || options.header === undefined
        ? { "Content-Type": "application/json" }
        : options.header),
      ...(withJWT && { Authorization: `Bearer ${await auth.getToken()}` }),
    },
    ...options,
  };

  console.log(config);

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
