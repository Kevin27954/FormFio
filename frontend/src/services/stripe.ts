import { apiRequest } from "./api";

export async function getCheckoutSession(uri: string, data: any) {
  return apiRequest<any>(uri, data);
}

export async function fulfillOrder(uri: string, data: any) {
  return apiRequest<any>(uri, data);
}
