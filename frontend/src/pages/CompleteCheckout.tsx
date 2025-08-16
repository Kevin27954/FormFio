import { fulfillOrder } from "@/services/stripe";
import { useEffect, useState } from "react";

function CompleteCheckout() {
	const [searchParams, setSearchParams] = useSearchParams();
	const [sessionStatus, setSessionStatus] = useState();

	const sessionId = searchParams.get("session_id");

	useEffect(() => {
		fulfillOrder(`/api/fulfill/${sessionId}`, {
			method: "POST",
		}).then((res) => {
			console.log(res);
			setSessionStatus(res);
		});
	}, []);

	return (
		<>
			{sessionId}

			<div>
				<p>{sessionStatus?.payment_intent_id}</p>
				<p>{sessionStatus?.payment_intent_status}</p>
				<p>{sessionStatus?.payment_status}</p>
				<p>{sessionStatus?.status}</p>
			</div>
		</>
	);
}

export default CompleteCheckout;
