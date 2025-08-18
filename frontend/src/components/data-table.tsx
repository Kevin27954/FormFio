import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table";
import type { SubmissionDTO } from "@/types/submissions";
import { Checkbox } from "./ui/checkbox";

function DataTable({
    submissions,
    totalRow,
}: {
    submissions: SubmissionDTO[];
    totalRow?: number;
}) {
    function extractKeys() {
        const allKeys = new Set();
        submissions.forEach((submission) => {
            if (submission.data && typeof submission.data === "object") {
                Object.keys(submission.data).forEach((key) => allKeys.add(key));
            }
        });
        return Array.from(allKeys);
    }

    const dataKeys = extractKeys();

    return submissions.length === 0 ? (
        <div className="h-full rounded-t-2xl">
            <Table rounded="rounded-t-2xl">
                <TableHeader className="sticky top-0 z-50">
                    <TableRow className="bg-green-200">
                        <TableHead>
                            <Checkbox className="bg-primary-foreground border-primary" />
                        </TableHead>
                        <TableHead className="text-center">Source</TableHead>
                        <TableHead className="text-center">Created At</TableHead>
                        {/* Dynamic columns for data keys */}
                        {dataKeys.map((key) => (
                            <TableHead key={key} className="whitespace-normal text-center">
                                {key}
                            </TableHead>
                        ))}
                    </TableRow>
                </TableHeader>
            </Table>
            <div className="flex-auto text-center content-center h-full">
                No Submissions Yet!
            </div>
        </div>
    ) : (
        <Table rounded="rounded-t-2xl">
            <TableHeader className="sticky top-0 bg-green-200 z-50">
                <TableRow>
                    <TableHead>
                        <Checkbox className="bg-primary-foreground border-primary" />
                    </TableHead>
                    <TableHead>Source</TableHead>
                    <TableHead>Created At</TableHead>
                    {/* Dynamic columns for data keys */}
                    {dataKeys.map((key) => (
                        <TableHead key={key} className="whitespace-nowrap">
                            {key}
                        </TableHead>
                    ))}
                </TableRow>
            </TableHeader>
            <TableBody>
                {submissions.length > 0 ? (
                    submissions.slice(0, totalRow).map((submission, index) => (
                        <TableRow key={index}>
                            <TableCell>
                                <div className="flex">
                                    <Checkbox className="border-primary" />
                                </div>
                            </TableCell>
                            <TableCell>{submission.source}</TableCell>
                            <TableCell>
                                {new Date(submission.createdDate).toDateString()}
                            </TableCell>
                            {/* Dynamic cells for data values */}
                            {dataKeys.map((key) => (
                                <TableCell
                                    key={key}
                                    className="max-w-[200px] break-words whitespace-normal text-center"
                                >
                                    {submission.data && submission.data[key] !== undefined
                                        ? submission.data[key]
                                        : "-"}
                                </TableCell>
                            ))}
                        </TableRow>
                    ))
                ) : (
                    <TableRow>
                        <TableCell
                            colSpan={3 + dataKeys.length}
                            className="h-24 text-center"
                        >
                            No submissions found.
                        </TableCell>
                    </TableRow>
                )}
            </TableBody>
        </Table>
    );
}

export default DataTable;
