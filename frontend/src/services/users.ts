import { apiRequest } from "./api";

export async function signUpAPI(uri: string, data: any) {
  return apiRequest<string>(uri, data);
}
