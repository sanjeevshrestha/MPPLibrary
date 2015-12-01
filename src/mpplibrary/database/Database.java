/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

import java.util.Properties;

/**
 *
 * @author 984970
 */
public class Database {

    private Properties prop;
    private Query query;

    public Database() {

        try {

            this.query = new Query();
            prop = new Properties();
//            InputStream infile = getClass().getResourceAsStream("mpplibrary.properties");//new FileInputStream("mpplibrary.mpplibrary.properties");
//
//            prop.load(infile);
//
//            System.out.println(prop.getProperty("driver"));

            prop.setProperty("driver", "org.sqlite.JDBC");
            prop.setProperty("connectionPath", "jdbc:sqlite:mpplibrary.db");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public Query getQuery(boolean newQuery) {
        if (newQuery) {
            this.query = new Query();
        }
        return this.query;
    }

    public Database setQuery(Query q) {
        this.query = q;
        return this;

    }

    public boolean execute() {
        String query = this.query.getQuery();
        System.out.println(query);

        return true;
    }
    
    public Database connect()
    {
        return this;
    }
    
    public Database initialize()
    {
        return this;
    }

}
