/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 984970
 */
public class Database {

    private Properties prop;
    
    
    public Database() {
        
        try
        {
                    InputStream infile=new FileInputStream("mpplibrary.properties");

        }
        catch(Exception e)
        {
            
        }
        
    }
    
    
  
}
