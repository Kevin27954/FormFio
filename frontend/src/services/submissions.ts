import type { SubmissionDTO } from "@/types/submissions";
import { apiRequest } from "./api";

export async function getSubmissionsAPI(uri: string, data: any) {
  return apiRequest<SubmissionDTO[]>(uri, data, true);
}

export async function submitSubmissionAPI(uri: string, data: any) {
  return apiRequest<void>(uri, data, true);
}
