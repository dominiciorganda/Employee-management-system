import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { CookieService } from "./cookie.service";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  user;
  constructor(private http: HttpClient, private cookieService: CookieService) {}

  /**
   * Returns the current user
   */
  public currentUser() {
    if (!this.user) {
      this.user = JSON.parse(this.cookieService.getCookie("currentUser"));
    }
    return this.user;
  }

  public setUser(newUserData) {
    if (!newUserData) {
      this.cookieService.setCookie(
        "currentUser",
        JSON.stringify(newUserData),
        1
      );
    }
  }

  /**
   * Performs the auth
   * @param email email of user
   * @param password password of user
   */
  login(email: string, password: string) {
    let url = "http://localhost:8081/api/auth";
    return this.http.post<any>(
      url,
      { email, password },
      { observe: "response" }
    );
  }

  /**
   * Logout the user
   */
  logout() {
    // remove user from local storage to log user out
    this.cookieService.deleteCookie("currentUser");
    this.user = null;
  }
}
