import {
    NavigationMenu,
    NavigationMenuList,
    NavigationMenuItem,
    NavigationMenuLink,
} from "@/components/ui/navigation-menu";
import { AuthContext } from "@/hooks/auth-context";
import { useContext } from "react";
import { Link, NavLink } from "react-router";

import { useLocation } from "react-router";

function NavMenu() {
    const authContext = useContext(AuthContext);
    const location = useLocation();

    return (
        <nav className="sticky top-0 left-0 right-0 z-50 bg-background border-b border-border shadow-sm">
            <div className="max-w-8xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-20">
                    {/* Logo/Brand */}
                    <div className="flex-shrink-0">
                        <Link to="/" className="text-2xl font-bold text-foreground">
                            FormKio
                        </Link>
                    </div>

                    {location.pathname === "/" && (
                        <div className="absolute left-1/2 transform -translate-x-1/2">
                            <NavigationMenu>
                                <NavigationMenuList className="space-x-5">
                                    <NavigationMenuItem>
                                        <NavigationMenuLink asChild>
                                            <a href="#features">Features</a>
                                        </NavigationMenuLink>
                                    </NavigationMenuItem>
                                    <NavigationMenuItem>
                                        <NavigationMenuLink asChild>
                                            <a href="#how-it-works">How It Works</a>
                                        </NavigationMenuLink>
                                    </NavigationMenuItem>
                                </NavigationMenuList>
                            </NavigationMenu>
                        </div>
                    )}

                    <NavigationMenu>
                        <NavigationMenuList className="space-x-5">
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
                            {authContext.user ? (
                                <NavigationMenuItem>
                                    <NavigationMenuLink asChild>
                                        <NavLink
                                            to="/dashboard"
                                            data-active={location.pathname === "/dashboard"}
                                        >
                                            Dashboard
                                        </NavLink>
                                    </NavigationMenuLink>
                                </NavigationMenuItem>
                            ) : (
                                <>
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
                                </>
                            )}
                        </NavigationMenuList>
                    </NavigationMenu>
                </div>
            </div>
        </nav>
    );
}

export default NavMenu;
