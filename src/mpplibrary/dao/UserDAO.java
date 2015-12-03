/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpplibrary.base.roles.User;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author sanjeev
 */
public class UserDAO extends DAO {

    public boolean login(User u) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.select("*").from("users").where("username=" + q.quote(u.getUsername()));
            q.where("active="+q.quote("true"));
   
            ResultSet rs = db.getResultSet();
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString("password").equals(u.getPassword())) {
                        u.setRole(rs.getString("role"));
                        return true;
                    } else {
                        return false;
                    }

                }
            }
            rs.close();

            return false;
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
