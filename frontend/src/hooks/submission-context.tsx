import { getSubmissionsAPI } from "@/services/submissions";
import type { FormDTO } from "@/types/forms";
import type { SubmissionDTO } from "@/types/submissions";
import { createContext, useContext, useEffect, useState } from "react";

type DataOptions = {
    size: number;
    last: number;
    col: "time";
    direction: "ASC" | "DESC";
    change: number;
};

const DEFAULT_OPTIONS: DataOptions = {
    size: 15,
    last: 0,
    col: "time",
    direction: "ASC",
    change: 1,
};

const itemOptions = [15, 20, 30, 40, 50, 60, 80, 100];

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

    const [itemNum, setItemNum] = useState(itemOptions[0]);

    const [isSubmissionLoading, setIsSubmissionLoading] =
        useState<boolean>(false);

    async function getSubmission(userOptions?: DataOptions) {
        if (!form || form.endpoint === null || form.endpoint === undefined) {
            return;
        }

        const options: DataOptions = {} as DataOptions;

        if (userOptions !== undefined) {
            for (const key of Object.keys(DEFAULT_OPTIONS) as (keyof DataOptions)[]) {
                options[key] = userOptions[key] ?? DEFAULT_OPTIONS[key];
            }
        }

        const option = options ?? DEFAULT_OPTIONS;
        const optionsParams = Object.keys(option)
            .map((key) => {
                return `${key}=${option[key]}`;
            })
            .join("&");

        getSubmissionsAPI(
            `/api/${form ? form.endpoint : "null"}?${optionsParams}`,
            {
                method: "GET",
            },
        ).then((data) => {
            data.map((submission) => {
                submission.data = JSON.parse(submission.data);
            });

            setSubmission(data);
            // setIsSubmissionLoading(false);
        });
    }

    useEffect(() => {
        // setIsSubmissionLoading(true);
        DEFAULT_OPTIONS.size = itemNum;
        console.log(itemNum);
        getSubmission(DEFAULT_OPTIONS);
    }, [form]);

    return (
        <SubmissionContext.Provider
            value={{
                form,
                setForm,
                isSubmissionLoading,
                submissions,
                getSubmission,
                itemNum,
                setItemNum,
            }}
        >
            {children}
        </SubmissionContext.Provider>
    );
}

export { SubmissionContext, useSubmissionContext, SubmissionProvider };
