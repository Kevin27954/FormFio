import { apiRequest } from "@/services/api";
import { CheckoutProvider } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";

// Make sure to call `loadStripe` outside of a component’s render to avoid
// recreating the `Stripe` object on every render.

const stripePromise = loadStripe(import.meta.env.VITE_STRIPE_KEY);

function Stripe(props: { children: React.ReactNode; plan: string }) {
    async function fetchClientSecret() {
        return apiRequest(`/api/create/checkout/${props.plan}`, {
            method: "POST",
        }).then((json: any) => json.checkoutSessionClientSecret);
    }

    return (
        <CheckoutProvider stripe={stripePromise} options={{ fetchClientSecret }}>
            {props.children}
        </CheckoutProvider>
    );
}

export default Stripe;
