/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

/**
 *
 * @author sanjeev
 */
public class Test {

    public static void main(String[] args) {
        Database db = DatabaseFactory.getInstance();
        Query q = db.getQuery(true);

        q.select("*").from("users").where("username=" + q.quote("sanjeev"));
        q.join("LEFT", "people", "p.a=u.id");
        q.where("password=\"123\"");
        q.order("title ASC");
        q.group("username");
        System.out.println(q.getQuery());

        q = db.getQuery(true);
        q.insert("users").column("username").value("sanjeev").column("password").value("123");
        System.out.println(q.getQuery());

        q = db.getQuery(true);
        q.update("users").set("username", "sanjeev").set("password", "1212");

        q.where("username=" + q.quote("sanjeev"));

        System.out.println(q.getQuery());
        
        q=db.getQuery(true);
        q.delete("users").where("username="+q.quote("sanjeev"));
        
        System.out.println(q.getQuery());

     //   db.setQuery(q);
    }

}
