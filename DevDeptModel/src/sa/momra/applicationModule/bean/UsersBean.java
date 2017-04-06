package sa.momra.applicationModule.bean;

public class UsersBean
{
  private String userName;
  private String userDesc;
  public UsersBean()
  {
    super();
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserDesc(String userDesc)
  {
    this.userDesc = userDesc;
  }

  public String getUserDesc()
  {
    return userDesc;
  }
}
