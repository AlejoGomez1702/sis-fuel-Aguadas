
package middlewares;

/**
 *
 * @author Alejo
 */
public class VehicleMiddleware 
{
    public boolean createVehicle(String description, int fuel)
    {
        if(description.equals("") || fuel < 1)
            return false;
        
        return true;
    }
}
