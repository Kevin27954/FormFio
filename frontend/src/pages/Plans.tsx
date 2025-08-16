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
    return (
        <div className="flex flex-col text-center">
            <h1 className="font-bold text-3xl p-4">Choose your plan</h1>
            <div className="gap-5 flex flex-row">
                {PLANS.map((plan) => {
                    return (
                        <div key={plan.plan_id} className="p-2">
                            <div className="max-w-sm mx-auto">
                                <Card
                                    key={plan.plan_id}
                                    className="hover:transform hover:-translate-y-2 hover:shadow-3xl transition-all duration-300 relative overflow-hidden rounded-3xl p-12 shadow-2xl"
                                >
                                    <CardHeader className="space-y-4 pb-6">
                                        <CardTitle className="text-3xl font-bold text-gray-900">
                                            {plan.plan_name}
                                        </CardTitle>
                                        <CardDescription className="text-gray-600 text-base">
                                            {plan.description}
                                        </CardDescription>
                                        <div className="text-5xl font-extrabold text-primary">
                                            ${plan.price}
                                            <span className="text-base text-gray-600 font-normal">
                                                /month
                                            </span>
                                        </div>
                                    </CardHeader>
                                    <CardContent>
                                        <ul className="space-y-3 text-left">
                                            {plan.plan_benefits.map((benefit) => {
                                                return (
                                                    <li
                                                        key={benefit}
                                                        className="flex items-center gap-3 text-gray-700"
                                                    >
                                                        <span className="text-emerald-600 font-bold text-lg">
                                                            ✓
                                                        </span>
                                                        {benefit}
                                                    </li>
                                                );
                                            })}
                                        </ul>
                                    </CardContent>
                                    <CardFooter>
                                        <Link to={`/plans/${plan.plan_id}`}>
                                            <Button
                                                className="
                                        w-full py-5 text-base font-semibold bg-gradient-to-r from-primary to-primary/90 hover:from-primary/20 hover:to-primary/10 hover:shadow-lg text-primary-foreground hover:text-accent-foreground"
                                                variant={"link"}
                                            >
                                                BUY PLAN FOR ${plan.price}.00
                                            </Button>
                                        </Link>
                                    </CardFooter>
                                </Card>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default Plans;
