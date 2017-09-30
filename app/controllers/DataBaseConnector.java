package controllers;
import com.typesafe.config.ConfigFactory;
import models.TrackData;
import play.api.Play;

import java.sql.*;

public class DataBaseConnector {
    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConfigFactory.load().getString("db.default.url"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static TrackData insert(TrackData trackData) {
        String sql = "INSERT INTO track_data(user_name,time_stamp,longitude,latitude,distance) VALUES(?,?,?,?,?)";


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trackData.getUserName());
            pstmt.setLong(2, trackData.getTimeStamp());
            pstmt.setDouble(3, trackData.getLongitude());
            pstmt.setDouble(4, trackData.getLatitude());
            pstmt.setDouble(5, trackData.getDistance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return trackData;
    }

    public static TrackData getPreviousRecord(TrackData trackData){
        String sql = "SELECT time_stamp, longitude, latitude, distance "
                + "FROM track_data where user_name=? ORDER BY time_stamp DESC LIMIT 1";
        TrackData td=null;
        try (Connection conn = connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1,trackData.getUserName());

            ResultSet rs  = pstmt.executeQuery();
            if(rs.next()) {
                td=new TrackData();
                td.setUserName(trackData.getUserName());
                td.setTimeStamp(rs.getLong("time_stamp"));
                td.setLatitude(rs.getDouble("latitude"));
                td.setLongitude(rs.getDouble("longitude"));
                td.setDistance(rs.getDouble("distance"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return td;
    }

}

