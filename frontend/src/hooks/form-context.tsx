import { getFormsAPI } from "@/services/forms";
import { createContext, useContext, useEffect, useState } from "react";

import type { FormDTO } from "@/types/forms";

const FormDataContext = createContext<any | null>(null);

function useFormDataContext(): React.Context<any> {
	const context = useContext(FormDataContext);
	if (!context)
		throw new Error(
			"useFormDataContext must be used inside a FormDataProvider",
		);
	return context;
}

function FormDataProvider({ children }: { children: React.ReactNode }) {
	const [formData, setFormData] = useState<FormDTO[]>([]);

	const [isFormDataLoading, setIsFormDataLoading] = useState<boolean>(false);

	useEffect(() => {
		setIsFormDataLoading(true);

		async function getEndpoints() {
			getFormsAPI("/api/forms/api/get", {
				method: "GET",
			}).then((data) => {
				setFormData(data);
				setIsFormDataLoading(false);
			});
		}

		// TODO REMOVE, THIS IS FOR TESTING
		setTimeout(() => {
			getEndpoints();
		}, 1000);
	}, []);

	return (
		<FormDataContext.Provider value={{ formData, isFormDataLoading }}>
			{children}
		</FormDataContext.Provider>
	);
}

export { FormDataContext, useFormDataContext, FormDataProvider };
