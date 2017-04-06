package sa.momra.applicationModule.bean;

import com.bea.security.UserInfo;

import com.bea.security.saml2.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;

import javax.naming.Context;

import weblogic.jndi.Environment;

import weblogic.management.MBeanHome;
import weblogic.management.security.authentication.AuthenticationProviderMBean;
import weblogic.management.security.authentication.UserReaderMBean;

import weblogic.security.providers.authentication.DefaultAuthenticatorMBean;
import oracle.tip.pc.services.common.ServiceFactory;
import oracle.tip.pc.services.identity.BPMGroup;
import oracle.tip.pc.services.identity.BPMIdentity;
import oracle.tip.pc.services.identity.BPMIdentityException;
import oracle.tip.pc.services.identity.BPMIdentityNotFoundException;
import oracle.tip.pc.services.identity.BPMIdentityService;
import oracle.tip.pc.services.identity.BPMIdentityType;
import oracle.tip.pc.services.identity.BPMProvider;
import oracle.tip.pc.services.identity.BPMUser;



public class WlUsers
{
  public WlUsers()
  {
    super();
  }
  
  public static  List<String> usersDesc = new ArrayList<String>();
  
    private  static DefaultAuthenticatorMBean defaultAuthenticator()
    {
      MBeanHome home = null;
      DefaultAuthenticatorMBean defaultAuthenticationMBean = null;
      try
      {
        Environment env = new Environment();
        env.setProviderUrl("t3://localhost:7003?");
        env.setSecurityPrincipal("weblogic");
        env.setSecurityCredentials("welcome1");
        Context ctx = env.getInitialContext();
        home = (MBeanHome) ctx.lookup("weblogic.management.adminhome");
        weblogic.management.security.RealmMBean rmBean = home.getActiveDomain().getSecurityConfiguration().getDefaultRealm();
        AuthenticationProviderMBean[] authenticationBeans = rmBean.getAuthenticationProviders();
        defaultAuthenticationMBean = (DefaultAuthenticatorMBean) authenticationBeans[0];
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return defaultAuthenticationMBean;
    }
    
    public static List<String> users()
    {
      List<String> users = new ArrayList<String>();
      UserReaderMBean userReaderMBean = (UserReaderMBean) defaultAuthenticator();
      String userCurName;
      try
      {
        userCurName = userReaderMBean.listUsers("*", 100);
        while (userReaderMBean.haveCurrent(userCurName))
        {
          String user = userReaderMBean.getCurrentName(userCurName);
          {
            users.add(user) ; 
            usersDesc.add(userReaderMBean.getUserDescription(user));
          }
            userReaderMBean.advance(userCurName);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return users;
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
