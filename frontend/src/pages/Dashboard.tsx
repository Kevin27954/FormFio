import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import SupabaseAuth from "@/lib/supabase";
import getServer from "@/util/getserver";
import { Label } from "@radix-ui/react-label";
import { useRef, useState } from "react";
import { toast } from "sonner";
import {
    Card,
    CardAction,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Link } from "react-router";

function Dashboard() {
    const auth = SupabaseAuth;

    const formName = useRef(null);
    const [formData, setFormData] = useState([]);

    async function createForm() {
        if (formName.current === null || formName.current.value === null) {
            toast("Name must not be null");
            return;
        }

        fetch(`${getServer()}/forms/api/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${await auth.getToken()}`,
            },
            body: JSON.stringify({ name: formName.current.value }),
        });
    }

    async function getSession() {
        console.log(await auth.getToken());
    }

    async function getEndpoints() {
        const res = await fetch(`${getServer()}/forms/api/get`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${await auth.getToken()}`,
            },
        });

        const data: [object] = JSON.parse(await res.json());
        setFormData(data);
    }

    return (
        <>
            <div className="flex items-center space-x-2">
                <Label htmlFor="form_name">Form Name</Label>
                <Input
                    id="form_name"
                    name="form_name"
                    placeholder="Form Name"
                    ref={formName}
                />
            </div>
            <Button onClick={createForm}>Create Form</Button>
            <Button onClick={getSession}>Get Session</Button>
            <Button onClick={getEndpoints}>Get Endpoints</Button>

            <Card>
                <CardHeader>
                    <CardTitle>Card Title</CardTitle>
                    <CardDescription>Card Description</CardDescription>
                </CardHeader>
                <CardContent>
                    <div className="flex items-center space-x-2 flex-row">
                        {formData.map((element) => {
                            return (
                                <Link to={`/auth/${element.endpoint}`}>
                                    {" "}
                                    {element.endpoint}
                                </Link>
                            );
                        }) || <p>No endpoints</p>}
                    </div>
                </CardContent>
            </Card>
        </>
    );
}

export default Dashboard;
