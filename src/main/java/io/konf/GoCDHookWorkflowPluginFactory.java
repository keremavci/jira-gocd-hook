package io.konf;

import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginFunctionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.FunctionDescriptor;

import java.util.Map;

/**
 * Created by kerem on 02/10/16.
 */
public class GoCDHookWorkflowPluginFactory extends AbstractWorkflowPluginFactory implements
        WorkflowPluginFunctionFactory {


    private static final String GOCDURL="gocdUrl";
    private static final String GOCDPIPELINE="gocdPipeline";
    private static final String GOCDUSERNAME="gocdUsername";
    private static final String GOCDPASSWORD="gocdPassword";


    protected void getVelocityParamsForInput(Map<String, Object> map) {
        map.put(GOCDURL,"");
        map.put(GOCDPIPELINE,"");
        map.put(GOCDUSERNAME,"");
        map.put(GOCDPASSWORD,"");

    }

    protected void getVelocityParamsForEdit(Map<String, Object> map, AbstractDescriptor abstractDescriptor) {
        getVelocityParamsForInput(map);
        getVelocityParamsForView(map,abstractDescriptor);

    }

    protected void getVelocityParamsForView(Map<String, Object> map, AbstractDescriptor abstractDescriptor) {
        if (!(abstractDescriptor instanceof FunctionDescriptor)) {
            throw new IllegalArgumentException("Descriptor must be a FunctionDescriptor.");
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor)abstractDescriptor;
        map.put(GOCDURL,functionDescriptor.getArgs().get(GOCDURL).toString());
        map.put(GOCDPIPELINE,functionDescriptor.getArgs().get(GOCDPIPELINE).toString());
        map.put(GOCDUSERNAME,functionDescriptor.getArgs().get(GOCDUSERNAME).toString());
        map.put(GOCDPASSWORD,functionDescriptor.getArgs().get(GOCDPASSWORD).toString());

    }

    public Map<String, ?> getDescriptorParams(Map<String, Object> map) {


        map.put(GOCDURL,extractSingleParam(map,GOCDURL));
        map.put(GOCDPIPELINE,extractSingleParam(map,GOCDPIPELINE));
        map.put(GOCDUSERNAME,extractSingleParam(map,GOCDUSERNAME));
        map.put(GOCDPASSWORD,extractSingleParam(map,GOCDPASSWORD));
        return map;

    }

}
