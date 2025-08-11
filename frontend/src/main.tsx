import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./assets/index.css";
import App from "./App.tsx";
import { createBrowserRouter, RouterProvider } from "react-router";
import VerifyPage from "./pages/VerifyPage.tsx";
import SignUp from "./pages/SignUp.tsx";
import SignIn from "./pages/SignIn.tsx";
import Dashboard from "./pages/Dashboard.tsx";
import NavLayout from "./layout/NavLayout.tsx";
import { Toaster } from "sonner";
import Endpoint from "./pages/Endpoint.tsx";
import Plans from "./pages/Plans.tsx";
import Checkout from "./pages/Checkout.tsx";

const router = createBrowserRouter([
    {
        path: "/",
        Component: App,
    },
    {
        path: "/auth",
        Component: NavLayout,
        children: [
            { path: "verify", Component: VerifyPage },
            { path: "signup", Component: SignUp },
            { path: "signin", Component: SignIn },
            { path: "dashboard", Component: Dashboard },
            { path: "plans", Component: Plans },
            { path: "plans/:plan", Component: Checkout },
            { path: ":endpoint", Component: Endpoint },
        ],
    },
]);

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <RouterProvider router={router} />
        <Toaster />
    </StrictMode>,
);
