package middlewares;

/**
 *
 * @author Alejo
 */
public class FuelMiddleware 
{
    public boolean createFuel(String name, String value)
    {
        if(name.equals("") || value.equals(""))
            return false;
        
        try 
        {
            Double.parseDouble(value);
        } catch (NumberFormatException e) 
        {
            return false;
        }
        
        return true;
    }
    
}
