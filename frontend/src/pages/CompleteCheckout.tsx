import { fulfillOrder } from "@/services/stripe";
import { useEffect, useState } from "react";
import SupabaseAuth from "@/lib/supabase";
import { Link, useSearchParams } from "react-router";

function CompleteCheckout() {
    const auth = SupabaseAuth;

    const [searchParams, setSearchParams] = useSearchParams();
    const [sessionStatus, setSessionStatus] = useState();

    const sessionId = searchParams.get("session_id");

    useEffect(() => {
        auth.getToken().then((token) => {
            fulfillOrder(`/api/fulfill/${sessionId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            }).then((res) => {
                console.log(res);
                setSessionStatus(res);
            });
        });
    }, []);

    return (
        <>
            <Link to="/auth">Auth Page</Link>
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
