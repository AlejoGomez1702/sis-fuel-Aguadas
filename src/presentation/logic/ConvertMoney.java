package presentation.logic;

import java.text.DecimalFormat;

/**
 *
 * @author Alejo
 */
public class ConvertMoney 
{
    public String run(double value)
    {
        DecimalFormat format = new DecimalFormat("###,###.##");
        String retorn = format.format(value);
        return retorn;
        
//        String money = "";
//        String varcharValue = (int) value + "";
//        //System.out.println("VARCHAR VALUE => " + varcharValue);
//        int num = varcharValue.length() - 1; //4 ...3=>0
//        int accumulator = 1;
//        
//        for(int i = num; i >= 0; i--) 
//        {            
//            money += varcharValue.charAt(accumulator - 1);
//            if((accumulator+1) % 3 == 0 && (accumulator-1) != num)
//            {
//                money += ".";
//            }
//            //System.out.println("CHAR=> " + i + " " + varcharValue.charAt(i));
//            accumulator ++;                        
//        }
//                
//        return money;        
    }
    
}
