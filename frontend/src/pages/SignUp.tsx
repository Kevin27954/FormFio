import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import SupabaseAuth from "@/lib/supabase";
import { useRef } from "react";
import { toast } from "sonner";

function SignUp() {
    const auth = SupabaseAuth;

    const user = useRef(null);
    const pass = useRef(null);

    async function handleClick() {
        if (user.current === null || pass.current === null) {
            toast("user and pass can't be null");
            return;
        }
        await auth.signUpPassword(user.current.value, pass.current.value);
    }

    return (
        <>
            <p>kevinl33078@gmail.com</p>
            <p>example-password</p>
            <Input type="email" placeholder="joe@example.com" ref={user} />
            <Input type="password" placeholder="Enter Your Password" ref={pass} />
            <Button onClick={handleClick}>Sign Up</Button>
        </>
    );
}

export default SignUp;
