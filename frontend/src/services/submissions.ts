import type { SubmissionDTO } from "@/types/submissions";
import { apiRequest } from "./api";

export async function getSubmissionsAPI(uri: string, data: any) {
  return apiRequest<SubmissionDTO>(uri, data);
}
