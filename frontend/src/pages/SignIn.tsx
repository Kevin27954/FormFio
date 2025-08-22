import { Input } from "@/components/ui/input";
import { useContext, useRef } from "react";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";

import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";

import { Link, useNavigate } from "react-router";
import { Label } from "@/components/ui/label";
import { AuthContext } from "@/hooks/auth-context";
import type { User } from "@supabase/supabase-js";

function SignIn() {
    const authContext = useContext(AuthContext);

    const user = useRef<HTMLInputElement>(null);
    const pass = useRef<HTMLInputElement>(null);

    const navigate = useNavigate();

    async function handleClick() {
        if (user.current === null || pass.current === null) {
            toast("user and pass can't be null");
            return;
        }

        authContext.auth
            .signInPassword(user.current.value, pass.current.value)
            .then((user: User | null) => {
                authContext.setUser(user);
                navigate("/dashboard");
            });
    }

    return (
        <Card className="p-12 max-w-lg w-full">
            <CardHeader className="mb-10">
                <CardTitle>
                    <div className="flex-shrink-0 mb-5">
                        <h1 className="text-2xl font-bold text-foreground">FormKio</h1>
                    </div>
                </CardTitle>
                <CardTitle>
                    <div className="flex-shrink-0">
                        <h1 className="text-3xl font-bold text-foreground">Welcome Back</h1>
                    </div>
                </CardTitle>
                <CardDescription>Sign in to continue</CardDescription>
            </CardHeader>
            <CardContent className="space-y-6">
                <p>kevinl33078@gmail.com</p>
                <p>example-password</p>
                <Label htmlFor="email">Email</Label>
                <Input
                    id="email"
                    type="email"
                    placeholder="joe@example.com"
                    ref={user}
                    className="w-full px-4 py-4"
                />
                <Label htmlFor="password">Password</Label>
                <Input
                    id="password"
                    type="password"
                    placeholder="Enter Your Password"
                    ref={pass}
                    className="w-full px-4 py-4"
                />
                <Button onClick={handleClick}>Sign In</Button>
            </CardContent>
            <CardFooter className="flex flex-col">
                Don't Have account? Sign up here
                <Link
                    className="text-primary font-medium hover:underline transition-colors"
                    to="/auth/signup"
                >
                    Sign Up
                </Link>
            </CardFooter>
        </Card>
    );
}

export default SignIn;
