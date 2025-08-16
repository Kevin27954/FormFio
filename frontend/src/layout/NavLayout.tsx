import NavMenu from "@/components/NavMenu";
import { Outlet } from "react-router";

function NavLayout() {
    return (
        <div className="flex-col w-full">
            <NavMenu />
            <main className="flex justify-center items-center m-8">
                <Outlet />
            </main>
        </div>
    );
}

export default NavLayout;
