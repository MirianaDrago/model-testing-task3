package alertSystem;

public class  alert {
    private int alertType;
    private String heading;
    private String description;
    private String url;

    private String imageUrl;
    private String postedBy;
    private int priceInCents;

    public int getAlertType() {
        return alertType;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public alert(int alertType, String heading, String description, String url, String imageUrl, String postedBy, int priceInCents) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.postedBy = postedBy;
        this.priceInCents = priceInCents;
    }
}