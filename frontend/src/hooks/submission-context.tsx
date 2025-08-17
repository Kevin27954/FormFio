import { getSubmissionsAPI } from "@/services/submissions";
import type { FormDTO } from "@/types/forms";
import type { SubmissionDTO } from "@/types/submissions";
import { createContext, useContext, useEffect, useState } from "react";

const SubmissionContext = createContext<any | null>(null);

function useSubmissionContext(): React.Context<any> {
    const context = useContext(SubmissionContext);
    if (!context)
        throw new Error(
            "useFormDataContext must be used inside a FormDataProvider",
        );
    return context;
}

function SubmissionProvider({ children }: { children: React.ReactNode }) {
    const [form, setForm] = useState<FormDTO>();
    const [submissions, setSubmission] = useState<SubmissionDTO[]>([]);

    const [isSubmissionLoading, setIsSubmissionLoading] =
        useState<boolean>(false);

    useEffect(() => {
        // setIsSubmissionLoading(true);

        async function getSubmission() {
            if (!form || form.endpoint === null || form.endpoint === undefined) {
                return;
            }

            getSubmissionsAPI(`/api/${form ? form.endpoint : "null"}`, {
                method: "GET",
            }).then((data) => {
                data.map((submission) => {
                    submission.data = JSON.parse(submission.data);
                });

                console.log(data);
                setSubmission(data);
                // setIsSubmissionLoading(false);
            });
        }

        getSubmission();
    }, [form]);

    return (
        <SubmissionContext.Provider
            value={{
                form,
                setForm,
                isSubmissionLoading,
                submissions,
            }}
        >
            {children}
        </SubmissionContext.Provider>
    );
}

export { SubmissionContext, useSubmissionContext, SubmissionProvider };
