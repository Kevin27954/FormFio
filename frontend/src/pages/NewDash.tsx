import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { useContext, useEffect, useState } from "react";
import getServer from "@/util/getserver";
import { SubmissionContext } from "@/hooks/submission-context";
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
import { toast } from "sonner";

const itemOptions = [15, 20, 30, 40, 50, 60, 80, 100];

function NewDash() {
    const submissionContext = useContext(SubmissionContext);
    // const [itemNum, setItemNum] = useState(itemOptions[0]);
    const [pageNum, setPageNum] = useState(0);
    const [isFirst, setIsFirst] = useState(true);

    console.log("New Dash");

    useEffect(() => {
        setIsFirst(true);
        setPageNum(0);
    }, [submissionContext.form]);

    function actionFn(formData: FormData) {
        const url = `/api${getServer()}/${submissionContext.form ? submissionContext.form.endpoint : "null"}`;

        const formBody = Array.from(formData)
            .map(([key, value]) => {
                return `${key}=${value.toString()}`;
            })
            .join("&");

        submitSubmissionAPI(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: formBody,
        });
    }

    function changeItemNum(num: number) {
        submissionContext.setItemNum(num);
        submissionContext.getSubmission({
            size: num,
        });
    }

    function changePageNum(num: number, change: number) {
        if (
            change > 0 &&
            submissionContext.submissions.length <= submissionContext.itemNum
        ) {
            toast("can't go past the limit");
            return;
        }

        setIsFirst(num === 0);
        setPageNum(num);

        const idx =
            change > 0
                ? Math.min(
                    submissionContext.submissions.length - 1,
                    submissionContext.itemNum - 1,
                )
                : 0;

        const last = submissionContext.submissions[idx].id;

        submissionContext.getSubmission({
            size: submissionContext.itemNum,
            last: last,
            change: change,
        });
    }

    return (
        <>
            <div className="bg-primary-foreground w-full h-full flex flex-col rounded-2xl border-primary/40 border-1">
                <DataTable
                    submissions={submissionContext.submissions}
                    totalRow={submissionContext.itemNum}
                />
                <div className="flex p-4 mt-auto">
                    <div className="mr-auto">
                        <DropdownMenu>
                            <DropdownMenuTrigger asChild>
                                <div>
                                    Rows Per Page:{" "}
                                    <Button variant="outline">
                                        {" "}
                                        {submissionContext.itemNum}
                                    </Button>
                                </div>
                            </DropdownMenuTrigger>
                            <DropdownMenuContent className="w-56">
                                <DropdownMenuLabel>Appearance</DropdownMenuLabel>
                                <DropdownMenuSeparator />
                                {itemOptions.map((itemOption) => {
                                    return (
                                        <DropdownMenuCheckboxItem
                                            onClick={() => changeItemNum(itemOption)}
                                            key={itemOption}
                                        >
                                            {itemOption}
                                        </DropdownMenuCheckboxItem>
                                    );
                                })}
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </div>

                    <div className="space-x-6 ml-auto">
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => {
                                changePageNum(pageNum - 1, -1);
                            }}
                            disabled={isFirst}
                        >
                            Previous
                        </Button>
                        <Button
                            variant="outline"
                            size="sm"
                            onClick={() => {
                                changePageNum(pageNum + 1, 1);
                            }}
                            disabled={
                                submissionContext.submissions.length <=
                                submissionContext.itemNum
                            }
                        >
                            Next
                        </Button>
                    </div>
                </div>
            </div>

            <h3 className="pt-4">
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
        </>
    );
}

export default NewDash;
