package com.neatorobotics.sdk.android.authentication;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class NeatoAuthenticationTest {

    private final String FORMATTED_AUTH_URL = "https://beehive.neatocloud.com/oauth2/authorize?client_id=%1$s&scope=%2$s&response_type=token&redirect_uri=%3$s";

    @Test
    public void buildOAuthAuthenticationUrl() throws Exception {
        String clientId = "12323232";
        String redirectUri = "myapp://neato";
        NeatoOAuth2Scope[] scopes = new NeatoOAuth2Scope[]{NeatoOAuth2Scope.ROBOT_COMMANDS};

        NeatoAuthentication neatoAuthentication = new NeatoAuthentication(clientId,redirectUri,scopes);

        assertTrue(neatoAuthentication.buildOAuthAuthenticationUrl(FORMATTED_AUTH_URL,
                clientId,
                neatoAuthentication.buildScopesParameter(scopes),
                redirectUri).equals(
                "https://beehive.neatocloud.com/oauth2/authorize?client_id=12323232&scope=robot_commands&response_type=token&redirect_uri=myapp://neato"));
    }

    @Test
    public void buildOAuthAuthenticationUrlWithNullValues() throws Exception {
        String clientId = null;
        String redirectUri = null;
        NeatoOAuth2Scope[] scopes = null;

        NeatoAuthentication neatoAuthentication = new NeatoAuthentication(clientId,redirectUri,scopes);

        assertTrue(neatoAuthentication.buildOAuthAuthenticationUrl(FORMATTED_AUTH_URL,
                clientId,
                neatoAuthentication.buildScopesParameter(scopes),
                redirectUri).equals(
                "https://beehive.neatocloud.com/oauth2/authorize?client_id=null&scope=&response_type=token&redirect_uri=null"));
    }

    @Test
    public void buildScopesParameter() throws Exception {
        NeatoAuthentication neatoAuthentication = new NeatoAuthentication(null,null,null);

        NeatoOAuth2Scope[] scopes = new NeatoOAuth2Scope[]{NeatoOAuth2Scope.ROBOT_COMMANDS};
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals("robot_commands"));

        scopes = new NeatoOAuth2Scope[]{NeatoOAuth2Scope.ROBOT_COMMANDS, NeatoOAuth2Scope.READ};
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals("robot_commands,read"));

        scopes = new NeatoOAuth2Scope[]{NeatoOAuth2Scope.READ, NeatoOAuth2Scope.ROBOT_COMMANDS};
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals("read,robot_commands"));

        scopes = new NeatoOAuth2Scope[]{NeatoOAuth2Scope.ROBOT_COMMANDS, NeatoOAuth2Scope.READ,NeatoOAuth2Scope.WRITE};
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals("robot_commands,read,write"));

        scopes = new NeatoOAuth2Scope[]{};
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals(""));

        scopes = null;
        assertTrue(neatoAuthentication.buildScopesParameter(scopes).equals(""));
    }
}
