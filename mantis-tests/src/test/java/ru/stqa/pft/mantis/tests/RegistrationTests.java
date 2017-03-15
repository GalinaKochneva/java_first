package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findAnyLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @Test
  public void testResetPassword() throws IOException {
    app.session().login("administrator", "root");
//    app.getDriver().get(app.getProperty("web.baseUrl"));
    app.navigate().manageUsers();
    //найти там первого пользователя
    String user = app.accounts().findAnyUser();
    app.accounts().openUserByName(user);
    app.accounts().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String resetPasswordLink = findAnyLink(mailMessages, user + "@localhost.localdomain");
    long now = System.currentTimeMillis();
    String password = String.format("password%s", now);
    app.registration().finish(resetPasswordLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findAnyLink(List<MailMessage> mailMessages, String email) {
    @SuppressWarnings("OptionalGetWithoutIsPresent") MailMessage mailMessage =
            mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopmailServer() {
    app.mail().stop();
  }
}
