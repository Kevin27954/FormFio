import { createClient } from "@supabase/supabase-js";
import type { SupabaseAuthClient } from "@supabase/supabase-js/dist/main/lib/SupabaseAuthClient";
import { redirect } from "react-router";

import { signUpAPI } from "@/services/users.ts";

const URL = import.meta.env.VITE_SUPABASE_URL;
const KEY = import.meta.env.VITE_SUPABASE_ANON_KEY;

class SupabaseAuth {
  client: SupabaseAuthClient;

  constructor() {
    this.client = createClient(URL, KEY).auth;
  }

  async signUpPassword(email: string, password: string) {
    const {
      user,
      session: _session,
      error,
    } = await this.client.signUp({
      email: email,
      password: password,
    });

    if (error != null) {
      console.log("error occured: ", error);
      return;
    }

    await signUpAPI("/api/users/api/register", {
      method: "POST",
      body: JSON.stringify(user),
    });

    redirect("/auth/verify");
  }

  async signInPassword(email: string, password: string) {
    const res = await this.client.signIn({ email: email, password: password });
    if (res.error !== null) {
      console.log("error occured: ", res.error);
      return;
    }
  }

  async getToken() {
    const session = this.client.session();
    if (session === null) {
      // TODO implement
      console.log("Redirect to LOGIN page");
      redirect("/");
      return;
    }
    return session.access_token;
  }
}

export default new SupabaseAuth();
