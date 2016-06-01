package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by Sasha on 29.05.2016.
 */
public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(ApplicationManager app) {
   super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void authorize (String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void changeUsersPassword(UserData user) {
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    click(By.linkText("Manage Users"));
    click(By.linkText(user.getUsername()));
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
