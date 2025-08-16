import {
    NavigationMenu,
    NavigationMenuList,
    NavigationMenuItem,
    NavigationMenuLink,
} from "@/components/ui/navigation-menu";
import { NavLink } from "react-router";

import { useLocation } from "react-router";

function NavMenu() {
    const location = useLocation();
    console.log(location.pathname);

    return (
        <nav className="sticky top-0 left-0 right-0 z-50 bg-background border-b border-border shadow-sm">
            <div className="max-w-8xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-20">
                    {/* Logo/Brand */}
                    <div className="flex-shrink-0">
                        <h1 className="text-2xl font-bold text-foreground">FormKio</h1>
                    </div>

                    <NavigationMenu>
                        <NavigationMenuList className="space-x-5">
                            <NavigationMenuItem>
                                <NavigationMenuLink asChild>
                                    <NavLink
                                        to="/home"
                                        data-active={location.pathname === "/home"}
                                    >
                                        Home
                                    </NavLink>
                                </NavigationMenuLink>
                            </NavigationMenuItem>

                            <NavigationMenuItem>
                                <NavigationMenuLink asChild>
                                    <NavLink
                                        to="/plans"
                                        data-active={location.pathname === "/plans"}
                                    >
                                        Plans
                                    </NavLink>
                                </NavigationMenuLink>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <NavigationMenuLink asChild>
                                    <NavLink
                                        to="/auth/signup"
                                        data-active={location.pathname === "/auth/signup"}
                                    >
                                        Sign Up
                                    </NavLink>
                                </NavigationMenuLink>
                            </NavigationMenuItem>
                            <NavigationMenuItem>
                                <NavigationMenuLink asChild>
                                    <NavLink
                                        to="/auth/signin"
                                        data-active={location.pathname === "/auth/signin"}
                                    >
                                        Sign In
                                    </NavLink>
                                </NavigationMenuLink>
                            </NavigationMenuItem>
                        </NavigationMenuList>
                    </NavigationMenu>
                </div>
            </div>
        </nav>
    );
}

export default NavMenu;
