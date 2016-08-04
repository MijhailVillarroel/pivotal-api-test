package org.fundacionjala.pivotal.cucumber.steps;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotal.StorySteps.*;

/**
 * Created by Charito on 7/22/2016.
 */
public class StoryResources {
    private ApiResources apiResources;
    public StoryResources(ApiResources apiResources){
        this.apiResources = apiResources;
    }
    public Map<String, Object> assertionMap() {
        Map<String, Object> assertionMap = new HashMap<>();
        assertionMap.put(String.valueOf(name), getStoryName());
        assertionMap.put(String.valueOf(description), getDescription());
        assertionMap.put(String.valueOf(estimate), getEstimate());
        assertionMap.put(String.valueOf(story_type), getStoryType());
        return assertionMap;
    }

    private String getStoryName() {
        return apiResources.getResponse().jsonPath().get("name").toString();
    }

    private String getDescription() {
        return apiResources.getResponse().jsonPath().get("description").toString();
    }

    private String getEstimate() {
        return apiResources.getResponse().jsonPath().get("estimate").toString();
    }

    private String getStoryType() {
        return apiResources.getResponse().jsonPath().get("story_type").toString();
    }
}
