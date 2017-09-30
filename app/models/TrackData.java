package models;

public class TrackData {
    private String userName;
    private long timeStamp;
    private double latitude;
    private double longitude;
    private double distance;
    private boolean start;

    public boolean isStart() {
        return start;
    }

    @Override
    public String toString() {
        return "TrackData{" +
                "userName='" + userName + '\'' +
                ", timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                ", start=" + start +
                '}';
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
