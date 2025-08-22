import InfoCard from "@/components/info-card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Link } from "react-router";

const features = [
    {
        title: "Advanced Analytics",
        description:
            "Deep insights into form performance, submission patterns, and user behavior. Track what matters most to your business. ",
    },
    {
        title: "Secure Data Storage",
        description:
            "Your form data is encrypted and stored securely. Enterprise-grade security with compliance standards you can trust. ",
    },
    {
        title: "Form Management",
        description:
            "Organize and manage all your forms in one central location. Track status, submissions, and performance at a glance. ",
    },
    {
        title: "Real-time Reporting",
        description:
            " Get instant updates on form submissions and performance metrics. Make data-driven decisions with live insights. ",
    },
    {
        title: "Lightning Fast",
        description:
            " Process thousands of submissions instantly. Built for scale and performance when you need it most. ",
    },
];

const steps = [
    {
        title: "Connect Your Forms",
        description:
            "Add your existing forms to FormFlow. We support all major form platforms and custom implementations. ",
    },
    {
        title: "Track & Analyze",
        description:
            "Watch submissions flow in real-time. Get detailed analytics on performance, completion rates, and user behavior. ",
    },
    {
        title: "Optimize & Improve",
        description:
            "Use insights to improve your forms. Export data, generate reports, and make data-driven decisions. ",
    },
];

function Home() {
    return (
        <div className="flex-col text-center w-full h-full">
            <Badge className="p-2 px-8 rounded-full mb-8 border-primary bg-accent text-primary font-bold">
                <p className="text-lg">Powerful form analytics and management</p>
            </Badge>

            <section id="header" className="mb-12">
                <h1 className="font-extrabold text-7xl pb-8 max-w-250 m-auto">
                    Manage & Analyze <span className="text-primary">Form Data</span> Like
                    Never Before
                </h1>
                <p className="text-4xl text-gray-500 max-w-200 m-auto">
                    The ultimate platform for form analytics and secure data management.
                    Track performance, manage submissions, and gain insights from your
                    forms.
                </p>
            </section>

            <Link to="/auth/signup">
                <Button className="mb-12 font-semibold text-xl p-6">
                    Sign up to get started
                </Button>
            </Link>

            <div className="h-100">ANAYLTICAL DATA TO DISLAY AS EXAMPLE</div>

            <section id="features" className="bg-primary-foreground pt-8">
                <div className="flex flex-col text-center mb-12">
                    <h1 className="font-extrabold text-5xl pb-8 max-w-250 m-auto">
                        Everything you Need
                    </h1>
                    <h3 className="text-3xl text-gray-500 max-w-200 m-auto">
                        Powerful analytics and management tools designed for form data
                        professionals
                    </h3>
                </div>

                <div className="grid grid-cols-3 gap-8 mb-12 p-12">
                    {features.map(({ title, description }) => {
                        return (
                            <InfoCard key={title} title={title} description={description} />
                        );
                    })}
                </div>
            </section>

            <section id="how-it-works" className="mb-12">
                <h1 className="font-extrabold text-5xl pb-8 max-w-250 m-auto">
                    How it works
                </h1>
                <div className="grid grid-cols-3 gap-8 mb-12 p-12">
                    {steps.map(({ title, description }) => {
                        return (
                            <InfoCard key={title} title={title} description={description} />
                        );
                    })}
                </div>
            </section>

            <section id="call-to-action" className="mb-32">
                <Link to="/auth/signup">
                    <Button className="font-extrabold text-2xl max-w-250 p-8">
                        Unlock Your Form Analytics Now
                    </Button>
                </Link>
            </section>

            <section id="footer">&copy; FormKio 2025</section>
        </div>
    );
}

export default Home;
