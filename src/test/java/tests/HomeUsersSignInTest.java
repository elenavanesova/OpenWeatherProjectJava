package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.home.HomeUsersPasswordPage;
import utils.GmailUtils;
import utils.ProjectConstants;
import utils.TestData;
import pages.home.HomeUsersSignInPage;

public class HomeUsersSignInTest extends BaseTest {

    @Test
    public void testSignOut() {
        String actualSignOutMessage = openBaseURL()
                .getTopMenu()
                .signIn()
                .getTopMenu()
                .signOut()
                .getNotification();

        Assert.assertEquals(actualSignOutMessage, "You need to sign in or sign up before continuing.");
    }

    @Test(dataProvider = "SignInCredentials", dataProviderClass = TestData.class)
    public void testSignInWithInvalidCredentials(String scenario, String userEmail, String userPassword, String expectedNoticeMessage, String expectedSignInMenuText) {

        final String oldSignInMenuText = openBaseURL()
                .getTopMenu()
                .clickSignInMenu()
                .getSignInText();

        HomeUsersSignInPage homeUsersSignInPage = new HomeUsersSignInPage(getDriver());

        homeUsersSignInPage
                .clickClearInputRegularUserEmail(userEmail)
                .clickClearInputRegularUserPassword(userPassword)
                .clickSubmitButton();

        String actualNoticeMessage = homeUsersSignInPage.getNotification();
        String actualSignInMenuText = homeUsersSignInPage.getSignInText();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
        Assert.assertEquals(actualSignInMenuText, oldSignInMenuText);
        Assert.assertEquals(actualSignInMenuText, expectedSignInMenuText);
    }

    @BeforeTest
    public void testDeleteAllEmails() throws Exception {
        try {
            GmailUtils.deleteAllMessages(GmailUtils.getGmailService());
        } catch (Exception e) {
            System.out.println("No mails exist" + e);
        }
    }

    @Test
    public void testRecoverPassword() {
        final String expectedNoticeMessage = "You will receive an email with instructions on how to reset your password "
                + "in a few minutes.";

        String actualNoticeMessage = openBaseURL()
                .getTopMenu()
                .clickSignInMenu()
                .clickRecoverLink()
                .inputRecoverPasswordEmail()
                .clickSendButton()
                .getNotification();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
    }

    @Test(dependsOnMethods = {"testRecoverPassword"})
    public void testRepeatRecoverPassword() {
        final String expectedNoticeMessage = "You have recently requested password recovery. Please try again later";

        String actualNoticeMessage = openBaseURL()
                .getTopMenu()
                .clickSignInMenu()
                .clickRecoverLink()
                .inputRecoverPasswordEmail()
                .clickSendButton()
                .getNotification();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword"})
    public void testEmptyRecoveryEmail() {
        final String expectedNoticeMessage = "Email can't be blank";

        String actualNoticeMessage = openBaseURL()
                .getTopMenu()
                .clickSignInMenu()
                .clickRecoverLink()
                .clickClearRecoverPasswordEmail()
                .clickSendButton()
                .getNotification();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword", "testEmptyRecoveryEmail"})
    public void testEmailResetPasswordReceived() throws Exception {
        String expectedSubject = "Reset password instructions";

        GmailUtils.getGmailService();
        boolean exist = GmailUtils.isMailExist(expectedSubject);

        if (!exist) {
            System.out.println("Email with the subject " + expectedSubject + " not found");
        }
        Assert.assertTrue(exist, "No email with subject " + expectedSubject + " received");
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword", "testEmptyRecoveryEmail",
            "testEmailResetPasswordReceived"})
    public void testLinkToResetPasswordIsWorking() throws Exception {
        String linkUrl = GmailUtils.getHrefLink(GmailUtils.getGmailService(), "Reset password instructions");
        getDriver().get(linkUrl);

        HomeUsersPasswordPage homeUsersPasswordPage = new HomeUsersPasswordPage(getDriver());

        Assert.assertEquals(homeUsersPasswordPage.getTitle(), "Members");
        Assert.assertEquals(homeUsersPasswordPage.getH3Header(), "Change your password");
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword", "testEmptyRecoveryEmail",
            "testEmailResetPasswordReceived", "testLinkToResetPasswordIsWorking"})
    public void testChangePasswordThroughEmail() throws Exception {
        String linkUrl = GmailUtils.getHrefLink(GmailUtils.getGmailService(), "Reset password instructions");
        getDriver().get(linkUrl);

        String actualAlertMessage = new HomeUsersPasswordPage(getDriver())
                .inputPassword(ProjectConstants.NEW_USER_PASSWORD)
                .inputRepeatPassword(ProjectConstants.NEW_USER_PASSWORD)
                .clickChangePasswordButton()
                .getAlertMessage();

        Assert.assertEquals(actualAlertMessage, "Your password has been changed successfully.");
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword", "testEmptyRecoveryEmail",
            "testEmailResetPasswordReceived", "testLinkToResetPasswordIsWorking", "testChangePasswordThroughEmail"})
    public void testLoginWithNewPassword() {
        String expectedAlertMessage = "Signed in successfully.";

        openBaseURL()
                .getTopMenu()
                .clickSignInMenu();

        new HomeUsersSignInPage(getDriver())
                .clickClearInputRegularUserEmail(ProjectConstants.GMAIL_EMAIL)
                .clickClearInputRegularUserPassword(ProjectConstants.NEW_USER_PASSWORD)
                .clickSubmitButton();

        String actualAlertMessage = new HomePage(getDriver()).getNotification();

        Assert.assertEquals(actualAlertMessage, expectedAlertMessage);
    }

    @Test(dependsOnMethods = {"testRecoverPassword", "testRepeatRecoverPassword", "testEmptyRecoveryEmail",
    "testEmailResetPasswordReceived", "testLinkToResetPasswordIsWorking", "testChangePasswordThroughEmail",
    "testLoginWithNewPassword"})
    protected void testAfterMethod_ChangePasswordInUserProfile() {
        final String expectedNoticeMessage = "Password was changed successfully";

        String actualNoticeMessage = openBaseURL()
                .getTopMenu()
                .signInGmailUserNewPassword()
                .getUserTopMenu()
                .clickMyProfileNavTabLink()
                .clickClearInputPasswordMyProfile()
                .clickClearInputConfirmPasswordMyProfile()
                .clickChangePasswordButton()
                .getNotification();

        Assert.assertEquals(actualNoticeMessage, expectedNoticeMessage);
    }
}

