package controllers;

import bd.BaseDatos;
import java.util.ArrayList;
import logic.Fuel;
import middlewares.FuelMiddleware;

/**
 *
 * @author Alejo
 */
public class FuelController 
{
    private FuelMiddleware middleware;
    private BaseDatos bd;

    public FuelController(BaseDatos bd) 
    {
        this.bd = bd;
        this.middleware = new FuelMiddleware();
    }    
    
    public ArrayList<Fuel> index()
    {
        ArrayList<Fuel> fuels;
        fuels = bd.getCrudFuel().getAllFuels();
        
        return fuels;
    }
    
    public boolean create(String name, String value)
    {
        //System.out.println("ennnntrroooo");
        boolean midd = this.middleware.createFuel(name, value);
        if(midd)
        {
System.out.println("PASOOO ELL MIDDLEWARE");
            return this.bd.getCrudFuel().addFuel(name, Double.parseDouble(value));            
        }
        else
            return false;   
        //return false;
    }
    
    public boolean update(Fuel fuel)
    {
        boolean midd = this.middleware.createFuel(fuel.getName(), fuel.getValue()+"");
        if(midd)
        {
System.out.println("PASOOO ELL MIDDLEWARE");
            return this.bd.getCrudFuel().updateFuel(fuel);
        }
        else
            return false;  
        
    }
    
    public boolean delete(int id)
    {
        return this.bd.getCrudFuel().deleteFuel(id);
    }
    
    
}
