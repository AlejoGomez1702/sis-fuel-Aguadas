package presentation.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Alejo
 */
public class CalendarPresentation 
{
    public String run(Calendar calendar)
    {
        String dateVarchar = "";
        
        Date date;        
        date = calendar.getTime();          
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
        dateVarchar = format1.format(date);
        
        return dateVarchar;
    }
    
}
