package GPSInfo;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by X84 on 2015/7/11.
 */
public class GPSListener {
    LocationManager locationManager;
    Criteria criteria = new Criteria();
    String bestProvider = null;
    //LocationProvider locationProvider = null;
    //Location location = null;
    /*LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };*/

    public GPSListener(Activity activity){
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }
    public void setCriteria(){
        criteria = new Criteria();
       // criteria.setAccuracy(5);
       // criteria.setCostAllowed(false);
       // criteria.setSpeedRequired(true);
        //bestProvider = locationManager.getBestProvider(this.criteria, true);
        //locationProvider = locationManager.getProvider(bestProvider);
    }
    public GPSPoint getGPSPoint(){
       /* if(locationProvider == null) {
            String bestProvider = locationManager.getBestProvider(this.criteria, true);
            locationProvider = locationManager.getProvider(bestProvider);
        }*/
        Log.i("111111111", "222222333333333333333333333333333333333444444444444444444444444444455555555555555555555555555566661212121212121212121212121212121212");

        if(bestProvider == null) {
           bestProvider = locationManager.getBestProvider(this.criteria, true);
        }
        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666131313131313131313131313131313131313");
        Log.e("gps=====", bestProvider);
        List<String> list = locationManager.getAllProviders();
        Location location = locationManager.getLastKnownLocation("network");
        for(String s:list)
            Log.e("Installent",s);
        if (location == null)
            Log.e("123444","23123124");
        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666600000000000000000000000000000000000000000");
       // Log.e("1234454", location.toString());
        Log.i("111111111", "22222233333333333333333333333333333333344444444444444444444444444445555555555555555555555555556666787878787878787878787878787878787878787");
        //Log.e("1234454",(new Double(location.getLongitude())).toString());
        GPSPoint gpsPoint = new GPSPoint();
        gpsPoint.setDate(new Date());
        Log.i("111111111", "2222223");

        gpsPoint.setLatitude(location.getLatitude());
        //gpsPoint.setLatitude(2.3);
        Log.i("111111111", "2222223333333333333333333333333333333334444444444444444444444444444555555555555555555555555555666gggggggggggggggggggggggggggggggggggggg");

        gpsPoint.setLongitude(location.getLongitude());
        //gpsPoint.setLongitude(5.1);
        Log.i("111111111111111111111","53453456467546783498983498343434347743785757454");
        return gpsPoint;
    }
}
