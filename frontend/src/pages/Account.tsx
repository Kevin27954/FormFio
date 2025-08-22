import { Button } from "@/components/ui/button";
import {
    Card,
    CardContent,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { AuthContext } from "@/hooks/auth-context";
import { updateEmailAPI } from "@/services/users";
import getServer from "@/util/getserver";
import { useContext, useRef } from "react";
import { toast } from "sonner";

function Account() {
    const authContext = useContext(AuthContext);
    const emailRef = useRef<HTMLInputElement>(null);

    function actionFn() {
        const url = `/api${getServer()}/users/api/update/email`;

        const email = emailRef.current?.value;

        if (email === null || email === "" || email === undefined) {
            toast("Email can not be empty");
            console.log(email);
            return;
        }

        updateEmailAPI(url, {
            method: "POST",
            body: JSON.stringify({ email: email }),
        }).then(() => {
            authContext.auth.changeEmail(email);
        });
    }

    async function signOut() {
        console.log("account ", authContext.auth);
        authContext.auth.signOut().then(() => {
            console.log("I logged out");
            authContext.setUser(null);
        });
    }

    return (
        <Card>
            <CardHeader className="space-y-4 pb-6">
                <CardTitle className="text-3xl font-bold text-gray-900">
                    Account
                </CardTitle>
            </CardHeader>
            <CardContent>
                <Label htmlFor="email">Email</Label>
                <Input
                    className="mt-2"
                    id="email"
                    name="email"
                    defaultValue={authContext.user?.email}
                    ref={emailRef}
                />
                <Button onClick={actionFn} type="submit">
                    Submit
                </Button>
            </CardContent>
            <CardFooter>
                <Button onClick={signOut}>Logout</Button>
            </CardFooter>
        </Card>
    );
}

export default Account;
