/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.MPPLibraryFactory;
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

    private ArrayList<User> users;

    public boolean login(User u) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.select("*").from("users").where("username=" + q.quote(u.getUsername()));
            q.where("active=" + q.quote("true"));

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

    public boolean saveUser(User user) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.insert("users");
            q.column("firstname").value(user.getFirstname());
            q.column("lastname").value(user.getLastname());
            q.column("email").value(user.getEmail());
            q.column("username").value(user.getUsername());
            q.column("password").value(user.getPassword());
            q.column("role").value(user.getRole());
            q.column("created").value(user.getCreatedDate().toString());
            q.column("createdby").value(String.valueOf(u.getID()));
            q.column("modified").value(user.getModifiedDate().toString());
            q.column("modifiedby").value(String.valueOf(u.getID()));
            q.column("active").value(String.valueOf(user.isActive()));
            q.column("phone").value(user.getPhone());
            q.column("address").value(user.getAddress());
            q.column("city").value(user.getCity());
            q.column("state").value(user.getState());
            q.column("zip").value(user.getZip());

            db.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public ArrayList<User> getUsers() {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("users");
            rs = db.getResultSet();
            users = new ArrayList<>();
            while (rs.next()) {
                User u;
                System.out.println(rs.getLong("id"));
                u = new User(rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getBoolean("active"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getDate("lastlogin"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip"));

                this.users.add(u);
            }

            rs.close();

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.users;

    }

}
