package presentation.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Alejo
 */
public class ConvertCalendar 
{
    // 0==> Initial, 1==> Finish.
    public String[] runPagination()
    {
        String[] fechas = new String[2];
        //Sacando las compras y ventas del ultimo mes.     
        LocalDateTime fechaActual = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(fechaActual.getYear(), fechaActual.getMonthValue()-1, 
                                                fechaActual.getDayOfMonth());
        
        //calendar.add(Calendar.DATE, 1);
        Date dateInitial = calendar.getTime();          
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");          
        String initialDate = null;
        String finishDate = null;
        try 
        {
            finishDate = format1.format(dateInitial);
            
            calendar.add(Calendar.DATE, -10);
            Date dateFinish = calendar.getTime();
            initialDate = format1.format(dateFinish);
        }catch (Exception e1) 
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        fechas[0] = initialDate;
        fechas[1] = finishDate;
        //initialDate += " 00:00";
        //finishDate += " 12:59";
        return fechas;
    }
    
    public String[] getSearchDates(Calendar initial, Calendar finish)
    {
        if(initial == null || finish == null)
            return null;
        
        String[] dates = new String[2];
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
        //Inicial
        Date dateInitial = initial.getTime();        
        dates[0] = format1.format(dateInitial);
        
        //final
        Date dateFinish = finish.getTime();
        dates[1] = format1.format(dateFinish);
        
        return dates;
    }
    
    public Date convertStringToDate(String dateString)
    {
        Date date = null;
                
        try 
        {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);  
        } catch (ParseException e) 
        {
            return null;
        }
        
        return date;
    }
    
}
