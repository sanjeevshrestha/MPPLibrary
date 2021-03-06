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
import mpplibrary.base.Member;
import mpplibrary.base.roles.User;
import mpplibrary.database.Database;
import mpplibrary.database.DatabaseFactory;
import mpplibrary.database.Query;
import mpplibrary.database.QueryException;

/**
 *
 * @author sanjeev
 */
public class MemberDAO {

    private Member member;

    private ArrayList<Member> members;

    public MemberDAO() {
        this.members = new ArrayList<>();
    }

    public MemberDAO(Member m) {
        this.member = m;
        this.members = new ArrayList<>();

    }

    public ArrayList<Member> getMembers() {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("members");
            rs = db.getResultSet();
            while (rs.next()) {
                Member m;

                m = new Member(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"), rs.getDouble("amount_due"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("mobile"),
                        rs.getString("note"),
                        rs.getString("address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                this.members.add(m);
            }

            rs.close();

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.members;

    }

    public ArrayList<Member> searchMembers(String key, String value) {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("*").from("members");

            String[] valueParts = value.split(" ");
            for (String str : valueParts) {
                q.where(key + " LIKE \"%" + str + "%\"", "OR");
            }

            rs = db.getResultSet();
            while (rs.next()) {
                Member m;
                m = new Member(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"), rs.getDouble("amount_due"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("mobile"),
                        rs.getString("note"),
                        rs.getString("address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                this.members.add(m);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.members;

    }

    public boolean loadMember(Member m) {
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);
            q.select("*").from("members").where("id=" + m.getID());

            rs = db.getResultSet();

            while (rs.next()) {

                m.setID(rs.getInt("id"));
                m.setFirstname(rs.getString("firstname"));
                m.setLastname(rs.getString("lastname"));
                m.setActive(rs.getBoolean("active"));
                m.setEmail(rs.getString("email"));
                m.setPhone(rs.getString("phone"));
                m.setMobile(rs.getString("mobile"));
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public boolean saveMember(Member m) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.insert("members");
            q.column("firstname").value(m.getFirstname());
            q.column("lastname").value(m.getLastname());
            q.column("created_by").value(String.valueOf(u.getID()));
            q.column("active").value(String.valueOf(m.isActive()));
            q.column("note").value(String.valueOf(m.getNote()));
            q.column("phone").value(m.getPhone());
            q.column("email").value(m.getEmail());
            q.column("mobile").value(m.getMobile());
            q.column("amount_due").value(String.valueOf(m.getAmountDue()));
            q.column("address").value(m.getAddress());
            q.column("city").value(m.getCity());
            q.column("state").value(m.getState());
            q.column("zip").value(m.getZip());

            db.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public boolean deleteMember(Member m) {
        try {
            Database db = DatabaseFactory.getInstance();
            Query q = db.getQuery(true);

            User u = MPPLibraryFactory.getLoggedInUser();

            q.delete("members");
            q.where("id="+q.quote(String.valueOf(m.getID())));

            db.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }
    
    
    public boolean isValid(Member m)
    {
        if(m.getID()==0) return false;
         boolean isValid = false;
        ResultSet rs = null;
        try {

            Database db = DatabaseFactory.getInstance();

            Query q = db.getQuery(true);
            q.select("count(*) as cnt").from("members");

            q.where("id=" + q.quote(String.valueOf(m.getID())));
            rs = db.getResultSet();
            
            while (rs.next()) {
                
                int rowcount=rs.getInt("cnt");
                isValid=rowcount>0;

            }

            rs.close();
        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return isValid;
    }

}
