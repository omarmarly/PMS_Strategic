package PreformTasks;

import com.bea.security.UserInfo;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;

import oracle.tip.pc.services.common.ServiceFactory;
import oracle.tip.pc.services.identity.BPMGroup;
import oracle.tip.pc.services.identity.BPMIdentity;
import oracle.tip.pc.services.identity.BPMIdentityException;
import oracle.tip.pc.services.identity.BPMIdentityNotFoundException;
import oracle.tip.pc.services.identity.BPMIdentityService;
import oracle.tip.pc.services.identity.BPMIdentityType;
import oracle.tip.pc.services.identity.BPMProvider;
import oracle.tip.pc.services.identity.BPMUser;


public class BPMUsers
{
  public ServiceFactory service = new ServiceFactory();
  
  public BPMUsers()
  {
    super();
  }
  
  public List<UserInfo> getAllAdvisors() {
          if (service == null) {
              service = ServiceFactory.getIdentityServiceInstance();
          }
          List<UserInfo> advisors = new ArrayList<UserInfo>();
          try {
              Set<BPMIdentity> identities =
                  service.getGranteesToAppRole("Advisor",
                                               "OracleBPMProcessRolesApp",
                                               false);
              for (BPMIdentity identity : identities) {
                  if (identity.getIdentityType() == BPMIdentityType.USER) {
                      identity.getAttributes();
                      String firstName =
                          (String)identity.getAttribute("firstName");
                      String lastName =
                          (String)identity.getAttribute("lastName");
                      String fullName = firstName + " " + lastName;
                      UserInfo advisor =
                          new UserInfo(identity.getName(), fullName);
                      advisors.add(advisor);
                  }

              }
          } catch (Throwable e) {
              e.printStackTrace();
          }
          return advisors;
  }
}
