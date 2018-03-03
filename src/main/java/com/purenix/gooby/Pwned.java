package com.purenix.gooby;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;

public class Pwned {

    private String name;
    private String email;
    private String password;
    private String ownedNum;

    public Pwned() {
      this.ownedNum = "0";
    }

    public Pwned(String password) {
      this.password = password;
      this.ownedNum = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getPwnedNum() {
      return ownedNum;
    }

    /* Uses Troy Hunt's `Am I pwoned` project's api
       to check if the password is in the pwned database.
       https://haveibeenpwned.com/API/v2#PwnedPasswords
    */
    public boolean isPwned(String password) {
      RestTemplate restTemplate = new RestTemplate();
      try {
        this.ownedNum = restTemplate.getForObject("https://api.pwnedpasswords.com/pwnedpassword/" + password, String.class);
      } catch (HttpClientErrorException e) {
        if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
          // We hit a 404, the password is not in the pwoned database
          return false;
        }
      }
      return true;
    }
}
