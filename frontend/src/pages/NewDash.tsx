import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { FormDataContext } from "@/hooks/form-context";
import { useContext, useState, type FormEventHandler } from "react";
import getServer from "@/util/getserver";
import { SubmissionContext } from "@/hooks/submission-context";
import type { SubmissionDTO } from "@/types/submissions";
import DataTable from "@/components/data-table";
import { submitSubmissionAPI } from "@/services/submissions";

import {
    DropdownMenu,
    DropdownMenuCheckboxItem,
    DropdownMenuContent,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

const pageOptions = [15, 20, 30, 40, 50, 60, 80, 100];

function NewDash() {
    const submissionContext = useContext(SubmissionContext);
    const [pageNum, setPageNum] = useState(pageOptions[0]);

    console.log("New Dash");

    function actionFn(formData: FormData) {
        const url = `/api${getServer()}/${submissionContext.form ? submissionContext.form.endpoint : "null"}`;

        const formBody = Array.from(formData)
            .map(([key, value]) => {
                return `${key}=${value.toString()}`;
            })
            .join("&");

        console.log(formBody);

        // url = "https://formspree.io/f/xkgzgyop";
        submitSubmissionAPI(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: formBody,
        });
    }

    return (
        <>
            <h3>
                {submissionContext.form ? submissionContext.form.endpoint : "null"}
            </h3>
            <form action={actionFn} method="post">
                <div className="flex items-center space-x-2">
                    <Label>name </Label> <Input type="text" name="name" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>password</Label> <Input type="password" name="password" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>random nullshit </Label>
                    <Input type="text" name="random nullshit" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>random 222ullshit </Label>
                    <Input type="text" name="random 22ullshit" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>skldfskj 222ullshit </Label>
                    <Input type="text" name="asdf 22ullshit" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>asdf 222ullshit </Label>
                    <Input
                        type="text"
                        name="test long text like super long text that should trigger overflow"
                    />
                </div>

                <Button type="submit">Submit</Button>
            </form>

            <div className="bg-primary-foreground w-full h-full flex flex-col">
                <DataTable
                    submissions={submissionContext.submissions}
                    totalRow={pageNum}
                />
                <div className="flex p-4 mt-auto">
                    <div className="mr-auto">
                        <DropdownMenu>
                            <DropdownMenuTrigger asChild>
                                <Button variant="outline">{pageNum}</Button>
                            </DropdownMenuTrigger>
                            <DropdownMenuContent className="w-56">
                                <DropdownMenuLabel>Appearance</DropdownMenuLabel>
                                <DropdownMenuSeparator />
                                {pageOptions.map((pageOption) => {
                                    return (
                                        <DropdownMenuCheckboxItem
                                            onClick={() => setPageNum(pageOption)}
                                            key={pageOption}
                                        >
                                            {pageOption}
                                        </DropdownMenuCheckboxItem>
                                    );
                                })}
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </div>

                    <div className="space-x-6 ml-auto">
                        <Button variant="outline" size="sm">
                            Previous
                        </Button>
                        <Button variant="outline" size="sm">
                            Next
                        </Button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default NewDash;
