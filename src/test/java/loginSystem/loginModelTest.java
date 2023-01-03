package loginSystem;

import nz.ac.waikato.modeljunit.*;
import cps3230.loginSystem.enums.LoginEnum;

import static org.junit.Assert.assertEquals;

public class loginModelTest implements FsmModel {
    Login sut = new Login();
    LoginEnum stateEnum = LoginEnum.LOGGEDIN; //starting state
    boolean loggedIn = false;
    boolean inAlertsPage = false;
    boolean valid_credentials = false;

    @Override
    public LoginEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) { //updating to general state
        if (b) {
            sut = new Login();
        }
        loggedIn = false;
        inAlertsPage = false;
        valid_credentials = false;
        stateEnum = LoginEnum.LOGGEDOUT;
    }

    public boolean badLoginGuard() {
        return getState().equals(LoginEnum.LOGGEDOUT);
    }

    public @Action void badLogin() {
        sut.badLogin();

        stateEnum = LoginEnum.LOGGEDOUT;
        loggedIn = false;
        valid_credentials = false;
        inAlertsPage = false;

        assertEquals(loggedIn, sut.isLoggedIn()); //both should return false
    }

    public boolean goodLoginGuard() {
        return getState().equals(LoginEnum.LOGGEDOUT);
    }

    public @Action void goodLogin() {
        sut.goodLogin();

        stateEnum = LoginEnum.LOGGEDIN;
        loggedIn = true;
        valid_credentials = true;

        assertEquals(loggedIn, sut.isLoggedIn()); //both should return true
    }

    public boolean visitAlertsGuard() {
        return getState().equals(LoginEnum.LOGGEDIN);
    }

    public @Action void visitAlerts() {
        sut.visitAlerts();

        stateEnum = LoginEnum.VIEWALERTS;
        loggedIn = true;
        inAlertsPage = true;

        assertEquals(inAlertsPage, sut.isInAlertsPage()); //both should return true
    }

    public boolean LogoutGuard() {
        return getState().equals(LoginEnum.LOGGEDIN);
    }

    public @Action void Logout() {
        sut.Logout();
        if (inAlertsPage) {
            inAlertsPage = false;
        }
        stateEnum = LoginEnum.LOGGEDOUT;
        loggedIn = false;

        assertEquals(loggedIn, sut.isLoggedOut());
    }

}

