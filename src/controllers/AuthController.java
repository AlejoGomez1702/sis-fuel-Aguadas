package controllers;

import presentation.logic.AuthPresentation;

/**
 * Controlador del inicio de sesión del sistema.
 * @author Alejo
 */
public class AuthController 
{
    //Nombre y contraseña del usuario que tiene acceso al sistema.
    private final String username = "alcaldia";
    private final String password = "aguadas";
    
    private AuthPresentation presentation;

    public AuthController(AuthPresentation presentation) 
    {
        this.presentation = presentation;
    }       
    
    public int verifyIncome(String username, String password)
    {
        int flag = 0;
        
        if(!username.equals(this.username+""))
            return 1;
        if(!password.equals(this.password+""))
            return 2;
        
        return flag;        
    }    
    
}
