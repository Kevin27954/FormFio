import { createContext, useContext } from "react";
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

    return <AuthContext.Provider value={auth}>{children}</AuthContext.Provider>;
}

export { AuthContext, useAuthContext, AuthProvider };
