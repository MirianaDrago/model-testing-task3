package alertSystem;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Alerts {

    public static ArrayList<alert> alerts = new ArrayList<>();
    int numOfAlerts = alerts.size();
    private static HttpURLConnection connection;

    public int getNumOfAlerts() {
        return numOfAlerts;
    }
    public static void addAlert() throws Exception {
        alert newAlert = new alert(
                1,
                "Testing heading",
                "Testing description",
                "https://www.scan.co.uk/products/156-asus-x515ja-bq2059w-fhd-i5-1035g1-8gb-ddr4-512gb-nvme-ssd-intel-uhd-graphics-usb-32-gen1-win11-h",
                "https://www.scan.co.uk/images/products/3431211-a.jpg",
                "bb59b5de-29c0-44a1-9788-d3f03719a886", 500000
        ); //creating a dummy alert
        int status_code = sendRequest(newAlert);
        if (status_code == 200) {
            alerts.add(newAlert); //only add alerts if alerts were successfully added to website with POST HTTP reqeust
        }
    }
    /*
    function: sends the alert to the website API as a POST HTTP request in order to update 'My Alerts'
    params: the alert we want to send
     */
    public static int sendRequest(alert Alert) throws Exception {
        URL url = new URL("https://api.marketalertum.com/Alert");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept","application/json");
        connection.setDoInput(true);
        int status_code = 0;
        return status_code;
    }
}
