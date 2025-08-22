import type { User } from "@supabase/supabase-js";
import { Navigate } from "react-router";

function ProtectedRoute({
	user,
	children,
}: {
	user: User | null;
	children: React.ReactNode;
}) {
	if (!user) {
		return <Navigate to={"/auth/signin"} replace />;
	}

	return children;
}

export default ProtectedRoute;
