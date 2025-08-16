import type { FormDTO } from "@/types/forms";
import { apiRequest } from "./api";

// TYPES

export async function testFormsAPI(uri: string, data: any) {
  return apiRequest<string>(uri, data);
}

export async function createFormAPI(uri: string, data: any) {
  return apiRequest<string>(uri, data, true);
}

export async function getFormsAPI(uri: string, data: any) {
  return apiRequest<FormDTO[]>(uri, data, true);
}
