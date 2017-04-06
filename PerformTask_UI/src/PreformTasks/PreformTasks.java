package PreformTasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.faces.event.ValueChangeEvent;


public class PreformTasks {

    public PreformTasks() {
        super();
    }
    private Date minDate = new Date();

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMinDate() {
        Calendar calendar = Calendar.getInstance();
        // CLEAR THE TIME PART*
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        minDate = calendar.getTime();
        return minDate;
    }

    public Date getMinDate2() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = formatter.format(currentDate.getTime());
        try {
            minDate = (Date)formatter.parse(dateNow);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return minDate;
    }

    //    public Date getCurrentSystemDate() {
    //        try {
    //            Calendar now = Calendar.getInstance();
    //            java.util.Date date = now.getTime();
    //            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //            String currentDate = formatter.format(date);
    //            return formatter.parse(currentDate);
    //        } catch (Exception e) {
    //            return null;
    //        }
    //    }

    public void taskStartDate(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("taskStartDate  :"+valueChangeEvent.getNewValue());
    }
}
