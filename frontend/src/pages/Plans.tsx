import { Button } from "@/components/ui/button";
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Link } from "react-router";

const PLANS = [
    {
        plan_id: "solo",
        plan_name: "SOLO PLAN",
        description: "PERFECT FOR SOLO DEVELOPERS TRYING TO GET SOME FEEDBACK",
        plan_benefits: ["basic plan", "500 submissions per month", "free support"],
        price: 5,
    },
    {
        plan_id: "team",
        plan_name: "SMALL TEAM",
        description: "PERFECT FOR SMALL BUSINESSES WITH GOOD OUTREACH",
        plan_benefits: ["basic plan", "5000 submissions per month", "free support"],
        price: 15,
    },
    {
        plan_id: "business",
        plan_name: "BUSINESS PLAN",
        description: "PLAN FOR BUSINESSES TO GET THE MOST OUT OF THEIR FORM DATAS",
        plan_benefits: [
            "basic plan",
            "10000 submissions per month",
            "free support",
        ],
        price: 50,
    },
];

function Plans() {
    console.log("I loaded");
    return (
        <div className="flex gap-5">
            {PLANS.map((plan) => {
                return (
                    <Card key={plan.plan_id}>
                        <CardHeader>
                            <CardTitle>{plan.plan_name}</CardTitle>
                            <CardDescription>{plan.description}</CardDescription>
                        </CardHeader>
                        <CardContent>
                            {plan.plan_benefits.map((benefit) => {
                                return <p key={benefit}>{benefit}</p>;
                            })}
                        </CardContent>
                        <CardFooter>
                            <Button>
                                <Link to={`/auth/plans/${plan.plan_id}`}>
                                    BUY PLAN FOR ${plan.price}.00
                                </Link>
                            </Button>
                        </CardFooter>
                    </Card>
                );
            })}
        </div>
    );
}

export default Plans;
