/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

/**
 *
 * @author unseen
 */
public class inputValidator 
{
    public boolean isValid2(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 1; i++)
        {
            if(inputs[i].matches("(?i).*[<>/(){}=\\n].*"))
            {
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    
    public boolean isValid7(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 6; i++)
        {
            if(inputs[i].matches("  <(\"[^\"]*\"|'[^']*'|[^'\">])*> "))
            {
                isValid = false;
            }
        }
        return isValid;
    }
    public boolean isValid10(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 10; i++)
        {
            if(inputs[i].matches("  <(\"[^\"]*\"|'[^']*'|[^'\">])*> "))
            {
                isValid = false;
            }
        }
        return isValid;
    }
    
}
