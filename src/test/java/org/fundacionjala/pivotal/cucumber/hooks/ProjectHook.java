package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.After;

import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllProjects;

/**
 * This class will clean the environment after all Comments features have been executed.
 *
 * @author Bruno Barrios.
 */
public class ProjectHook {

    /**
     * This hook method will delete the project that is created into the Comments scenarios.
     */
    @After("@deleteAllProject")
    public void deleteProject() {
        deleteAllProjects();
    }

}
