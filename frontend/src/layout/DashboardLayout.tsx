import AppSideBar from "@/components/app-sidebar";
import NavMenu from "@/components/NavMenu";
import { SidebarProvider } from "@/components/ui/sidebar";
import { FormDataProvider } from "@/hooks/form-context";
import { SubmissionProvider } from "@/hooks/submission-context";
import { Outlet } from "react-router";

function DashboardLayout() {
    return (
        <div className="flex flex-col w-full">
            <NavMenu />
            <SidebarProvider className="bg-accent flex-1 min-h-0">
                <FormDataProvider>
                    <SubmissionProvider>
                        <div className="flex w-full">
                            <AppSideBar />
                            <main className="p-8 w-full overflow-auto">
                                <Outlet />
                            </main>
                        </div>
                    </SubmissionProvider>
                </FormDataProvider>
            </SidebarProvider>
        </div>
    );
}

export default DashboardLayout;
