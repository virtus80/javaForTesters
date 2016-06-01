package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

/**
 * Created by Sasha on 30.05.2016.
 */
public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword () throws IOException, MessagingException {
    List<UserData> users = app.db().users().stream().filter(( u ) -> ! u.getUsername().equals("administrator")).collect(Collectors.toList());
    UserData selectedUser = users.iterator().next();
    app.mail().start();
    app.registration().authorize("administrator", "root");
    app.registration().changeUsersPassword(selectedUser);
    app.registration().logout();

    String email = selectedUser.getEmail();
    String newPassword = "password1";
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 15000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPassword);
    app.mail().stop();
    HttpSession session = app.newSession();
    assertTrue(session.login(selectedUser.getUsername(), newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return  regex.getText(mailMessage.text);
  }
}
