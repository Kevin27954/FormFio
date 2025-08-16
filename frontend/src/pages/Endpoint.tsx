import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import getServer from "@/util/getserver";
import { useParams } from "react-router";

function Endpoint() {
    const params = useParams();

    const url = `/api${getServer()}/${params.endpoint}`;
    //<!--<form action="https://formspree.io/f/xkgzgyop" method="post">-->
    return (
        <>
            <h3>{params.endpoint}</h3>
            <form action={url} method="post">
                <div className="flex items-center space-x-2">
                    <Label>name </Label> <Input type="text" name="name" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>password</Label> <Input type="password" name="password" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>number </Label> <Input type="text" name="number" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>email </Label> <Input type="email" name="email" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>text </Label> <Input type="text" name="text" />
                </div>
                <div className="flex items-center space-x-2">
                    <Label>random nullshit </Label>
                </div>
                <div className="flex items-center space-x-2">
                    <Input type="text" name="random nullshit" />
                </div>
                <Button type="submit">Submit</Button>
            </form>
        </>
    );
}

export default Endpoint;
