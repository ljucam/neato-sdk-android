package com.neatorobotics.sdk.android.authentication;

/**
 * Neato-SDK
 * Created by Marco on 06/05/16.
 * Copyright © 2016 Neato Robotics. All rights reserved.
 */
public enum NeatoOAuth2Scope {
    PUBLIC_PROFILE("public_profile"),
    CONTROL_ROBOTS("control_robots"),
    MAPS("maps");

    private final String permission;

    NeatoOAuth2Scope(final String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return permission;
    }
}