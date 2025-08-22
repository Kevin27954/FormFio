import { createContext, useContext, useState } from "react";
import initSupabaseAuth from "@/lib/supabase";

const AuthContext = createContext<any | null>(null);

function useAuthContext(): React.Context<any> {
	const context = useContext(AuthContext);
	if (!context)
		throw new Error(
			"useFormDataContext must be used inside a FormDataProvider",
		);
	return context;
}

function AuthProvider({ children }: { children: React.ReactNode }) {
	const auth = initSupabaseAuth();
	console.log(auth);
	console.log(auth.getUserInfo());

	const [user, setUser] = useState(auth.getUserInfo());

	return (
		<AuthContext.Provider value={{ auth, user, setUser }}>
			{children}
		</AuthContext.Provider>
	);
}

export { AuthContext, useAuthContext, AuthProvider };
