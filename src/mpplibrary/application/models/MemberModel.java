/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.application.models;

import java.util.ArrayList;
import mpplibrary.base.Address;
import mpplibrary.base.Member;
import mpplibrary.dao.MemberDAO;

/**
 *
 * @author 984970
 */
public class MemberModel {
    private static MemberModel instance;

    static {
        instance = new MemberModel();
    }

    public static MemberModel getInstance() {
        return instance;

    }

    public ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<>();

        try {
            MemberDAO md = new MemberDAO();
             members = md.getMembers();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return members;

    }

    public ArrayList<Member> searchMembers(String key, String value) {
        ArrayList<Member> members = new ArrayList<>();

        try {
            MemberDAO md = new MemberDAO();
            members = md.searchMembers(key, value);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return members;

        
        
    }
    
    
    public boolean save(long ID, String firstname, String lastname, boolean active, double amt,String email, String phone, String mobile,String note,String address, String city, String state, String zip)
    {
        Member m=new Member(ID,firstname, lastname, active, amt, email,  phone,  mobile, note);
        Address addr=new Address(address, city, state,zip);
        m.setAddress(addr);
       return m.saveMember();
        
    }

    public static void main(String[] args) {
        BookModel bm = new BookModel();
        bm.searchBooks("ISBN", "test");
    }
    
}
