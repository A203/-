package GPSInfo;

import java.util.Date;

/**
 * Created by X84 on 2015/7/11.
 */
public class GPSPoint {
    private Date date;
    private double longitude;
    private double latitude;
    private float speed;

    public Date getDate() {
        return date;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
