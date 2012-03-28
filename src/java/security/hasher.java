/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author unseen
 */
public class hasher 
{
    public String setHash(String password) throws NoSuchAlgorithmException
    {
        StringBuffer sb = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        md.update(password.getBytes());
        
        byte byteData[] = md.digest();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    
    public boolean passChecksum(String password)
    { 
        boolean isEquals = false;
        
        return isEquals;
    
    }
    
}
