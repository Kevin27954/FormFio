import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import SupabaseAuth from "@/lib/supabase";
import { useRef } from "react";
import { toast } from "sonner";

import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Link } from "react-router";
import { Label } from "@/components/ui/label";
import initSupabaseAuth from "@/lib/supabase";

function SignUp() {
    const auth = initSupabaseAuth();

    const user = useRef<HTMLInputElement>(null);
    const pass = useRef<HTMLInputElement>(null);

    async function handleClick() {
        if (user.current === null || pass.current === null) {
            toast("user and pass can't be null");
            return;
        }
        await auth.signUpPassword(user.current.value, pass.current.value);
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
                        <h1 className="text-3xl font-bold text-foreground">
                            Create Account
                        </h1>
                    </div>
                </CardTitle>
                <CardDescription>Forms Here</CardDescription>
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
                <Button onClick={handleClick}>Sign Up</Button>
            </CardContent>
            <CardFooter className="flex flex-col">
                Have account? Sign in here
                <Link
                    className="text-primary font-medium hover:underline transition-colors"
                    to="/auth/signin"
                >
                    Sign In
                </Link>
            </CardFooter>
        </Card>
    );
}

export default SignUp;
