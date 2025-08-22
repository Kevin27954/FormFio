import NavMenu from "@/components/NavMenu";
import { Outlet } from "react-router";

function NavLayout() {
    return (
        <div className="flex flex-col w-full">
            <NavMenu />
            <main className="flex flex-1 justify-center items-center p-8 bg-accent">
                <Outlet />
            </main>
        </div>
    );
}

export default NavLayout;
