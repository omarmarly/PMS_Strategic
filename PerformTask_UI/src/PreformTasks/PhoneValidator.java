package PreformTasks;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PhoneValidator
  implements Validator
{
  public PhoneValidator()
  {
    super();
  }

  public void validate(FacesContext facesContext, UIComponent uIComponent,
                       Object object)
    throws ValidatorException
  {
    String phone = object.toString();
    if (phone.length() < 7 && isNumeric(phone))
    {
      FacesMessage fm =
        new FacesMessage("Phone number should be at least 7 digits");
      throw new ValidatorException(fm);
    }
  }

  public static boolean isNumeric(String str)
  {
    try
    {
      double d = Double.parseDouble(str);
    }
    catch (NumberFormatException nfe)
    {
      return false;
    }
    return true;
  }
}
