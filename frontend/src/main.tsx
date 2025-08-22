import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { createBrowserRouter, RouterProvider } from "react-router";
import VerifyPage from "./pages/VerifyPage.tsx";
import SignUp from "./pages/SignUp.tsx";
import SignIn from "./pages/SignIn.tsx";
import NavLayout from "./layout/NavLayout.tsx";
import { Toaster } from "sonner";
import Endpoint from "./pages/Endpoint.tsx";
import Plans from "./pages/Plans.tsx";
import Checkout from "./pages/Checkout.tsx";
import CompleteCheckout from "./pages/CompleteCheckout.tsx";
import NewDash from "./pages/NewDash.tsx";
import DashboardLayout from "./layout/DashboardLayout.tsx";
import Account from "./pages/Account.tsx";
import Analytic from "./pages/Analytic.tsx";
import { AuthProvider } from "./hooks/auth-context.tsx";
import "./App.css";
import Home from "./pages/Home.tsx";

const router = createBrowserRouter([
	{
		path: "/",
		Component: NavLayout,
		children: [{ index: true, Component: Home }],
	},
	{
		path: "/plans",
		Component: NavLayout,
		children: [
			{ path: "", Component: Plans },
			{ path: ":plan", Component: Checkout },
		],
	},
	{
		path: "/dashboard",
		Component: DashboardLayout,
		children: [
			{ index: true, Component: NewDash },
			{ path: "account", Component: Account },
			{ path: "analytic", Component: Analytic },
		],
	},
	{
		path: "/auth",
		Component: NavLayout,
		children: [
			{ path: "verify", Component: VerifyPage },
			{ path: "signup", Component: SignUp },
			{ path: "signin", Component: SignIn },
		],
	},
	{
		path: "/complete",
		Component: CompleteCheckout,
	},
	{
		path: "*",
		Component: Endpoint,
	},
]);

createRoot(document.getElementById("root")!).render(
	<StrictMode>
		<AuthProvider>
			<RouterProvider router={router} />
			<Toaster />
		</AuthProvider>
	</StrictMode>,
);
