/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mpplibrary.base.Member;
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
                m = new Member(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"));
                this.members.add(m);
            }

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
                m = new Member(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getBoolean("active"));
                this.members.add(m);
            }

        } catch (QueryException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return this.members;

    }
    
}
