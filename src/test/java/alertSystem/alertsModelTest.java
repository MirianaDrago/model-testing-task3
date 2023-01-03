package alertSystem;

import nz.ac.waikato.modeljunit.*;
import cps3230.alertSystem.enums.AlertsEnum;

import static org.junit.Assert.assertEquals;

public class alertsModelTest implements FsmModel {
    Alerts sut = new Alerts(); //system under test
    AlertsEnum stateEnum = AlertsEnum.VIEWALERTS; //starting state
    int alerts = 0;
    boolean valid_properties = false;

    @Override
    public AlertsEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) {
        if (b) {
            sut = new Alerts();
        }
        alerts = 0;
        valid_properties = false;
        stateEnum = AlertsEnum.VIEWALERTS;
    }

    public boolean addAlertGuard() { return getState().equals(AlertsEnum.VIEWALERTS); }
    public @Action void addAlert() throws Exception {
        sut.addAlert();

        stateEnum = AlertsEnum.VIEWALERTS;
        alerts++;

        assertEquals(alerts, sut.getNumOfAlerts()); //making sure that there is same amount of alerts
    }
}
