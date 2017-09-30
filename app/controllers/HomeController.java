package controllers;

import Util.Util;
import com.fasterxml.jackson.databind.JsonNode;
import models.TrackData;
import play.Logger;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;

import javax.inject.Inject;

public class HomeController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }

    @Inject
    FormFactory formFactory;
    public Result handleupdates(){

        JsonNode json = request().body().asJson();
        if (json == null){
            return badRequest(Util.createResponse(
                    "Expecting Json data", false));
        }
        try {
            TrackData currentTrackData=(TrackData) Json.fromJson(json, TrackData.class);
            TrackData prevTrackData=DataBaseConnector.getPreviousRecord(currentTrackData);
            if(!currentTrackData.isStart() && prevTrackData!=null){
                if(prevTrackData.getLatitude()==currentTrackData.getLatitude() && prevTrackData.getLongitude()==currentTrackData.getLongitude()){
                    currentTrackData.setDistance(prevTrackData.getDistance());
                }else{
                    currentTrackData.setDistance(prevTrackData.getDistance()+distance(prevTrackData.getLatitude(),prevTrackData.getLongitude(),currentTrackData.getLatitude(),currentTrackData.getLongitude()));
                }
            } else{
                currentTrackData.setDistance(0);
            }
            if(currentTrackData.getLongitude()!=400 || currentTrackData.getLatitude()!=400)
                currentTrackData = DataBaseConnector.insert(currentTrackData);
            JsonNode jsonObject = Json.toJson(currentTrackData);
            Logger.debug(currentTrackData.toString());
            return created(Util.createResponse(""+currentTrackData.getDistance()+"", true));
        }catch (Exception e){
            return created(Util.createResponse(e.getMessage(), false));
        }

    }

    /* Code below referenced from http://www.geodatasource.com/developers/java licensed under LGPLv */
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}

