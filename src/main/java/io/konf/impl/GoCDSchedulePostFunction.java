package io.konf.impl;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.workflow.WorkflowFunctionUtils;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;
import io.konf.client.GoCDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kerem on 29/09/16.
 */
public class GoCDSchedulePostFunction extends AbstractJiraFunctionProvider {

    private static final Logger log = LoggerFactory.getLogger(GoCDSchedulePostFunction.class);

    public void execute(Map transientVars, Map args, PropertySet propertySet) throws WorkflowException {



        MutableIssue issue = getIssue(transientVars);
      //  CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();

        String gocdURL= (String) args.get("gocdUrl");
        String gocdPipeline= (String) args.get("gocdPipeline");
        String gocdUser= (String) args.get("gocdUsername");
        String gocdPassword= (String) args.get("gocdPassword");

        //String issueKey= issue.getKey();
        // CustomField projectName = customFieldManager.getCustomFieldObject("customfield_10001");



        Map<String,String> params = new HashMap<String, String>(){{
            //put("variables[testvar]","test");

        }};
        String gocdApiUrl=gocdURL + "/go/api/pipelines/" + gocdPipeline + "/schedule";
        GoCDClient goCDClient = new GoCDClient(gocdUser,gocdPassword);
        try {
            int responseCode=goCDClient.executeHttpPost(gocdApiUrl,params);
            if (responseCode != 202){
                throw new RuntimeException("Failed HTTP Error Code :" + responseCode);
            }
        } catch (IOException e) {
            log.error("Failed",e);
        }

    }




}
