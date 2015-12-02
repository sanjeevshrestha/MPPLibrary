/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

/**
 *
 * @author 984970
 */
public class DatabaseFactory {

    public static Database db;

    public static Database getInstance() {

        if (db == null) {
            db = new Database();
            db.connect();
        }

        return db;

    }

}
