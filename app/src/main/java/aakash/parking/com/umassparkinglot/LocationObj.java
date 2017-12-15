package aakash.parking.com.umassparkinglot;

import java.util.Objects;



public class LocationObj {

    private String locationName;
    private String Lat;
    private String Lon;
    private String seekCount;



    public void setLocationName(String locationName){
        this.locationName = locationName;
    }

    public String getLocationName(){
      return locationName;

    }

    public void setLat(String Lat){
        this.Lat = Lat;
    }

    public String getLat(){
        return Lat;
    }
    public void setLon(String Lon){
        this.Lon = Lon;
    }
    public String getLon(){
        return Lon;
    }
    public void setseekCount(String seekCount){
        this.seekCount = seekCount;
    }
    public String getseekCount(){
        return seekCount;
    }
}
