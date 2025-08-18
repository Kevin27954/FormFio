import {
	Sidebar,
	SidebarContent,
	SidebarGroup,
	SidebarGroupContent,
	SidebarGroupLabel,
	SidebarHeader,
	SidebarMenu,
	SidebarMenuButton,
	SidebarMenuItem,
} from "@/components/ui/sidebar";
import { useContext, useState } from "react";
import { FormDataContext } from "@/hooks/form-context";
import { Button } from "@/components/ui/button";
import {
	Dialog,
	DialogClose,
	DialogContent,
	DialogDescription,
	DialogFooter,
	DialogHeader,
	DialogTitle,
	DialogTrigger,
} from "@/components/ui/dialog";

import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { createFormAPI } from "@/services/forms";
import { toast } from "sonner";
import type { FormDTO } from "@/types/forms";
import { SubmissionContext } from "@/hooks/submission-context";
import { Link } from "react-router";

const sideActBtn =
	"border-r-primary border-r-5 text-primary font-bold bg-primary/30";
const sideInactBtn = "";

function AppSideBar({ className }: React.ComponentProps<typeof Sidebar>) {
	console.log("appsidebar");
	const formContext = useContext(FormDataContext);
	const submissionContext = useContext(SubmissionContext);

	const [dialogOpen, setDialogOpen] = useState(false);
	const [activeBtn, setActiveBtn] = useState("");

	async function actionFn(formData: FormData) {
		if (formData.get("name") === null || formData.get("name") === "") {
			toast("Name must not be empty");
			return;
		}

		createFormAPI("/api/forms/api/create", {
			method: "POST",
			body: JSON.stringify({ name: formData.get("name") }),
		});
	}

	function onFormSelect(form: FormDTO) {
		setActiveBtn(form.endpoint);
		submissionContext.setForm(form);
	}

	return (
		<Sidebar className={className} collapsible="none">
			<SidebarContent className="border-r-primary/40 border-r-1">
				<SidebarGroup className="h-full">
					<SidebarHeader>
						<Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
							<DialogTrigger asChild>
								<Button>Create a new form</Button>
							</DialogTrigger>
							<DialogContent className="sm:max-w-[425px]">
								<DialogHeader>
									<DialogTitle>Create Form</DialogTitle>
									<DialogDescription>
										Click submit when you&apos;re done to create a new form.
									</DialogDescription>
								</DialogHeader>
								<form action={actionFn}>
									<Label htmlFor="form-name">Form Name</Label>
									<Input className="mt-2" id="form-name" name="name" />
									<DialogFooter className="pt-4">
										<DialogClose asChild>
											<Button variant="outline">Cancel</Button>
										</DialogClose>
										<DialogClose asChild>
											<Button type="submit">Submit</Button>
										</DialogClose>
									</DialogFooter>
								</form>
							</DialogContent>
						</Dialog>
					</SidebarHeader>
					<SidebarGroupLabel className="uppercase font-bold text-gray-400">
						Application
					</SidebarGroupLabel>
					<SidebarGroupContent className="h-1/2 overflow-auto">
						<SidebarMenu>
							{formContext.isLoading
								? "Placeholder for spinner"
								: formContext.formData.map((form: FormDTO) => {
										return (
											<SidebarMenuItem key={form.endpoint}>
												<Link to="">
													<SidebarMenuButton
														onClick={() => onFormSelect(form)}
														className={`${
															activeBtn === form.endpoint
																? sideActBtn + ""
																: sideInactBtn
														} text-base`}
														asChild
													>
														<span>{form.formName}</span>
													</SidebarMenuButton>
												</Link>
											</SidebarMenuItem>
										);
									})}
						</SidebarMenu>
					</SidebarGroupContent>

					<SidebarGroupLabel className="uppercase font-bold text-gray-400">
						Tools
					</SidebarGroupLabel>
					<SidebarGroupContent className="overflow-auto">
						<SidebarMenu>
							<SidebarMenuItem>
								<Link to="/dashboard/analytic">
									<SidebarMenuButton
										className={
											activeBtn === "tools" ? sideActBtn : sideInactBtn
										}
										onClick={() => {
											setActiveBtn("tools");
										}}
									>
										<span>Temp Tools</span>
									</SidebarMenuButton>
								</Link>
							</SidebarMenuItem>
						</SidebarMenu>
					</SidebarGroupContent>

					<SidebarGroupLabel className="uppercase font-bold text-gray-400">
						Account
					</SidebarGroupLabel>
					<SidebarGroupContent className="overflow-auto">
						<SidebarMenu>
							<SidebarMenuItem>
								<Link to="/dashboard/account">
									<SidebarMenuButton
										className={
											activeBtn === "account" ? sideActBtn : sideInactBtn
										}
										onClick={() => {
											setActiveBtn("account");
										}}
									>
										<span>Temp Account</span>
									</SidebarMenuButton>
								</Link>
							</SidebarMenuItem>
						</SidebarMenu>
					</SidebarGroupContent>
				</SidebarGroup>
			</SidebarContent>
		</Sidebar>
	);
}

export default AppSideBar;
