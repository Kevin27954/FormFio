import initSupabaseAuth from "@/lib/supabase";
import getServer from "@/util/getserver";

/**
 * This returns a json of type T (you pass in the type that you expect), so it only consumes JSON APIs
 */
const apiRequest = async <T>(
  uri: string,
  options: any,
  withJWT = false,
): Promise<T> => {
  const auth = initSupabaseAuth();
  uri = parseUrl(uri);
  const url = `${getServer()}${uri}`;

  const token = withJWT ? await auth.getToken() : null;
  const authHeader = withJWT && { Authorization: `Bearer ${token}` };

  const config = {
    ...options,
    headers: {
      ...(options.headers ?? { "Content-Type": "application/json" }),
      ...authHeader,
    },
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
