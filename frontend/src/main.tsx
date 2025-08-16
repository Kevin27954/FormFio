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

const router = createBrowserRouter([
    {
        path: "/",
        Component: NavLayout,
        children: [{ path: "", Component: App }],
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
        path: "/home",
        Component: DashboardLayout,
        children: [{ path: "", Component: NewDash }],
    },
    {
        path: "/auth",
        Component: NavLayout,
        children: [
            { path: "verify", Component: VerifyPage },
            { path: "signup", Component: SignUp },
            { path: "signin", Component: SignIn },
            { path: ":endpoint", Component: Endpoint },
        ],
    },
    {
        path: "/complete",
        Component: CompleteCheckout,
    },
]);

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <RouterProvider router={router} />
        <Toaster />
    </StrictMode>,
);
