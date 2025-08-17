import { Button } from "@/components/ui/button";
import initSupabaseAuth from "@/lib/supabase";
import { PaymentElement, useCheckout } from "@stripe/react-stripe-js";
import { toast } from "sonner";

function CheckoutForm() {
	const checkout = useCheckout();
	const auth = initSupabaseAuth();

	const handleSubmit = async (event) => {
		event.preventDefault();

		const email = auth.getUserInfo()?.email;

		if (email === null || email === "" || email === undefined) {
			toast("email was null");
			return;
		}

		await checkout.updateEmail(email).then((res) => {
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

	return (
		<form onSubmit={handleSubmit}>
			{JSON.stringify(checkout.lineItems, null, 2)}
			Total: {checkout.total.total.amount}
			<PaymentElement />
			<Button>Submit</Button>
		</form>
	);
}

export default CheckoutForm;
