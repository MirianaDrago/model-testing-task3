package alertSystem;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Alerts {

    public ArrayList<alert> alerts = new ArrayList<>();
    public String userID = "bb59b5de-29c0-44a1-9788-d3f03719a886";
    int numOfAlerts = alerts.size();
    boolean valid_properties = false;
    private static HttpURLConnection connection;

    public int getNumOfAlerts() {
        return numOfAlerts;
    }

    public void checkAlerts() {
        for (alert Alert : alerts) {
            if (Alert.getAlertType() != 0 && Alert.getDescription() != null && Alert.getHeading() != null && Alert.getImageUrl()!= null && Alert.getPostedBy() != null && Alert.getPriceInCents() != 0) {
                valid_properties = true;
            } else {
                valid_properties = false;
            }
        }
    }
    public void deleteAlerts() {
        alerts.clear(); //removing all elements from the list
        System.out.println(numOfAlerts);
    }
    public void addAlert() throws Exception {
        alert newAlert = new alert(
                1,
                "Testing heading",
                "Testing description",
                "https://www.scan.co.uk/products/156-asus-x515ja-bq2059w-fhd-i5-1035g1-8gb-ddr4-512gb-nvme-ssd-intel-uhd-graphics-usb-32-gen1-win11-h",
                "https://www.scan.co.uk/images/products/3431211-a.jpg",
                userID, 500000
        );
        //creating a dummy alert
        int status_code = sendRequest(newAlert);
        if (status_code == 201) {
            alerts.add(newAlert); //only add alerts if alerts were successfully added to website with POST HTTP reqeust
        }
    }
    /*
    function: sends the alert to the website API as a POST HTTP request in order to update 'My Alerts'
    params: the alert we want to send
     */
    public static int sendRequest(alert Alert) throws Exception {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(Alert); //saving the response in order to be able to check that the request was successful
        System.out.println("The JSON request is: " + jsonRequest);

        URL url = new URL("https://api.marketalertum.com/Alert");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonRequest.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        int status_code = connection.getResponseCode();
        return status_code;
    }

    public static void main(String[] args) throws Exception {
        Alerts alertSystem = new Alerts();
        alertSystem.addAlert();
        alertSystem.addAlert();
        alertSystem.checkAlerts();
        System.out.println(alertSystem.alerts.size());
    }
}
