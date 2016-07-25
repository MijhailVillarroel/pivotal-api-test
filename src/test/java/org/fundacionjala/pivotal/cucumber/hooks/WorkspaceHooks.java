package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.After;

import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllWorkspaces;

/**
 * Created by danielgonzales
 */
public class WorkspaceHooks {

    @After("@deleteAllWorkspaces")
    public void deleteWorkspace() {
        deleteAllWorkspaces();
    }

}
