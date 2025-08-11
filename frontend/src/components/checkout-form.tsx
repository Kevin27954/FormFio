import { Button } from "@/components/ui/button";
import { PaymentElement, useCheckout } from "@stripe/react-stripe-js";

function CheckoutForm() {
    const checkout = useCheckout();

    const handleSubmit = async (event) => {
        event.preventDefault();

        checkout.updateEmail("example@gmail.com").then((res) => {
            if (res.type === "error") {
                console.log(res.error);
                console.log(res);
            }
        });

        const result = await checkout.confirm();

        if (result.type === "error") {
            // Show error to your customer (for example, payment details incomplete)
            console.log(result.error.message);
        } else {
            // Your customer will be redirected to your `return_url`. For some payment
            // methods like iDEAL, your customer will be redirected to an intermediate
            // site first to authorize the payment, then redirected to the `return_url`.
        }
    };

    const handleClick = () => {
        checkout.confirm().then((result) => {
            if (result.type === "error") {
                console.log(result.error);
            }
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            {JSON.stringify(checkout.lineItems, null, 2)}
            Total: {checkout.total.total.amount}
            <PaymentElement />
            <Button onClick={handleClick}>Submit</Button>
        </form>
    );
}

export default CheckoutForm;
