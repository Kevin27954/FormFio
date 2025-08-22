import {
    Card,
    CardContent,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";

function InfoCard({
    title,
    description,
}: {
    title: string;
    description: string;
}) {
    return (
        <Card>
            <CardHeader className="space-y-4 pb-6">
                <CardTitle className="text-3xl font-bold text-gray-900">
                    {title}
                </CardTitle>
            </CardHeader>
            <CardContent>{description}</CardContent>
            <CardFooter></CardFooter>
        </Card>
    );
}

export default InfoCard;
