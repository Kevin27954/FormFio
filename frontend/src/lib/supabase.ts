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
    this.client.refreshSession();
  }

  getUserInfo() {
    return this.client.user();
  }

  async signUpPassword(email: string, password: string) {
    return this.client
      .signUp({
        email: email,
        password: password,
      })
      .then((res) => {
        if (res.error != null) {
          console.log("error occured: ", res.error);
          return;
        }

        signUpAPI("/api/users/api/register", {
          method: "POST",
          body: JSON.stringify(res.user),
        }).then((res) => {
          return res;
        });
      });
  }

  async signInPassword(email: string, password: string) {
    return this.client
      .signIn({ email: email, password: password })
      .then((res) => {
        if (res.error !== null) {
          console.log("error occured: ", res.error);
          return;
        }
        return res.user;
      });
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

  async signOut() {
    this.client.signOut();
  }

  async changeEmail(newEmail: string) {
    await this.client.update({ email: newEmail });
  }
}

function initSupabaseAuth() {
  return new SupabaseAuth();
}

export default initSupabaseAuth;
