import { Input } from "@/components/ui/input";
import { useRef } from "react";
import SupabaseAuth from "@/lib/supabase";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";

function SignIn() {
    const auth = SupabaseAuth;

    const user = useRef(null);
    const pass = useRef(null);

    async function handleClick() {
        if (user.current === null || pass.current === null) {
            toast("user and pass can't be null");
            return;
        }
        await auth.signInPassword(user.current.value, pass.current.value);
    }

    async function getSession() {
        console.log(await auth.getToken());
    }

    return (
        <>
            <p>kevinl33078@gmail.com</p>
            <p>example-password</p>
            <Input type="email" placeholder="joe@example.com" ref={user} />
            <Input type="password" placeholder="Enter Your Password" ref={pass} />
            <Button onClick={handleClick}>Sign In</Button>
            <Button onClick={getSession}>Get Session</Button>
        </>
    );
}

export default SignIn;
