package org.fundacionjala.pivotal.util;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;

/**
 * This class will let us to use constants variables through the classes.
 *
 * @author Bruno Barrios
 */
public final class Constants {

    public static final int SUCCESS_STATUS_CODE = 200;

    public static final String PROJECTS_ENDPOINT = "/projects/";

    public static final String WORKSPACES_ENDPOINT = "/my/workspaces/";

    public static final String ATTRIBUTE_ID = "id";

    private Constants() {
    }
}
