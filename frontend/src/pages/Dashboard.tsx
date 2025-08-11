import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import SupabaseAuth from "@/lib/supabase";
import { Label } from "@radix-ui/react-label";
import { useRef, useState } from "react";
import { toast } from "sonner";
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Link } from "react-router";
import { createFormAPI, getFormsAPI, testFormsAPI } from "@/services/forms";
import type { FormDTO } from "@/types/forms";
import { getSubmissionsAPI } from "@/services/submissions";

function Dashboard() {
    const auth = SupabaseAuth;

    const formName = useRef<HTMLInputElement>(null);
    const [formData, setFormData] = useState<FormDTO[]>([]);

    async function createForm() {
        if (formName.current === null || formName.current.value === null) {
            toast("Name must not be null");
            return;
        }

        createFormAPI("/api/forms/api/create", {
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
        const data = await getFormsAPI("/api/forms/api/get", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${await auth.getToken()}`,
            },
        });

        setFormData(data);
    }

    async function getSubmissions() {
        const res = await getSubmissionsAPI(
            "/api/aa5d6fbb-2ddf-4aa9-abe7-12078409fc81",
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${await auth.getToken()}`,
                },
            },
        );

        console.log(res);
    }

    async function test() {
        const res = await testFormsAPI("/api/forms/test", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        console.log(res);
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
            <Button onClick={getSubmissions}>Get Submissions</Button>
            <Button onClick={getSubmissions}>Get Submissions</Button>
            <Button onClick={test}>Get Test</Button>

            <Card>
                <CardHeader>
                    <CardTitle>Card Title</CardTitle>
                    <CardDescription>Card Description</CardDescription>
                </CardHeader>
                <CardContent>
                    <div className="flex items-center space-x-2 flex-row">
                        {formData.map((element) => {
                            return (
                                <Link key={element.endpoint} to={`/auth/${element.endpoint}`}>
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
