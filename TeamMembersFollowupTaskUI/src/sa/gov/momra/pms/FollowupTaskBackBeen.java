package sa.gov.momra.pms;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.worklist.adf.ADFWorklistBeanUtil;
import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

public class FollowupTaskBackBeen {
   
    public String getBpmUserName() {
        FacesContext context = FacesContext.getCurrentInstance();
        String ctx =
            (String)context.getApplication().evaluateExpressionGet(context,
                                                                   "#{pageFlowScope.bpmWorklistContext}",
                                                                   String.class);
        IWorkflowServiceClient workflowSvcClient =
            ADFWorklistBeanUtil.getWorkflowServiceClient();
        ITaskQueryService wfQueryService =
            workflowSvcClient.getTaskQueryService();
        IWorkflowContext wfContext;
        String userName = "";
        try {
            wfContext = wfQueryService.getWorkflowContext(ctx);
            userName = wfContext.getUser();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
        return userName;
    }
}