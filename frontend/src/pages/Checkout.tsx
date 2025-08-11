import CheckoutForm from "@/components/checkout-form";
import Stripe from "@/components/stripe";
import { useParams } from "react-router";

function Checkout() {
    const params = useParams();

    return (
        <Stripe plan={params.plan ?? "solo"}>
            <CheckoutForm />
        </Stripe>
    );
}

export default Checkout;
