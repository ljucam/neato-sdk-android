package com.neatorobotics.sdk.android.beehive;

import android.util.Log;

import com.neatorobotics.sdk.android.models.Robot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Neato-SDK
 * Created by Marco on 06/05/16.
 * Copyright © 2016 Neato Robotics. All rights reserved.
 */
public class BeehiveJSONParser {

    private static final String TAG = "BeehiveJSONParser";

    public static ArrayList<Robot> parseRobots(JSONObject json) {
        ArrayList<Robot> robots = new ArrayList<>();
        if (json!= null && json.has("value")) {
            try {
                JSONArray arr = json.getJSONArray("value");
                for (int i = 0; i < arr.length(); i++) {
                    Robot robot = new Robot(arr.getJSONObject(i));

                    if (robot.linkedAt == null || "".equalsIgnoreCase(robot.linkedAt)) {
                        continue;
                    }else robots.add(robot);
                }
            } catch (JSONException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        return robots;
    }
}
