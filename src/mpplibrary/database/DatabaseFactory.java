/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

import java.sql.SQLException;

/**
 *
 * @author 984970
 */
public class DatabaseFactory {

    public static Database db;
    static{
        db=new Database();
    }

    public static Database getInstance() {

        return db;

    }
    
    public static void closeConnection()
    {
        try {
            db.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

}
