import AppSideBar from "@/components/app-sidebar";
import NavMenu from "@/components/NavMenu";
import ProtectedRoute from "@/components/protect-route";
import { SidebarProvider } from "@/components/ui/sidebar";
import { AuthContext } from "@/hooks/auth-context";
import { FormDataProvider } from "@/hooks/form-context";
import { SubmissionProvider } from "@/hooks/submission-context";
import { useContext } from "react";
import { Outlet } from "react-router";

function DashboardLayout() {
    const authContext = useContext(AuthContext);
    return (
        <div className="flex flex-col w-full">
            <ProtectedRoute user={authContext.user}>
                <NavMenu />
                <SidebarProvider className="bg-accent flex-1 min-h-0">
                    <FormDataProvider>
                        <SubmissionProvider>
                            <div className="flex w-full max-h-[calc(100vh-5rem)]">
                                <AppSideBar />
                                <main className="p-8 w-full overflow-auto">
                                    <Outlet />
                                </main>
                            </div>
                        </SubmissionProvider>
                    </FormDataProvider>
                </SidebarProvider>
            </ProtectedRoute>
        </div>
    );
}

export default DashboardLayout;
