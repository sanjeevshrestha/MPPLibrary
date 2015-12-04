/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base.roles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mpplibrary.base.Person;
import mpplibrary.dao.UserDAO;

/**
 *
 * @author 984970
 */
public class User extends Person implements Role {

    private String username;
    private String password;
    private String role;
    private Date createdDate;
    private long createdBy;
    private Date modifiedDate;
    private long modifiedBy;
    private Date lastlogin;
    private UserDAO dataAccess;

    private List<Role> roles;

    public User() {
        roles = new ArrayList<>();
        this.dataAccess = new UserDAO();
    }

    public User(long id) {
        super(id);
        roles = new ArrayList<>();

        this.dataAccess = new UserDAO();
    }

    public User(String username, String password) {
        roles = new ArrayList<>();

        this.username = username;
        this.password = password;
        this.dataAccess = new UserDAO();
    }

    public User(long id, String firstname, String lastname, String email, String username, String password, String role, Date createdDate, long createdBy, Date modifiedDate, long modifiedBy, boolean active, String phone, String address, String city, String state, String zip) {
        super(id, firstname, lastname, email, active, phone, address, city, state, zip);
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.dataAccess = new UserDAO();
        roles = new ArrayList<>();

    }

    public User(long id, String firstname, String lastname, String email, boolean active, String address, String phone, Date lastLoggedIn, String city, String state, String zip) {
        super(id, firstname, lastname, email, active, phone, address, city, state, zip);
        this.lastlogin = lastLoggedIn;
        this.dataAccess = new UserDAO();
        roles = new ArrayList<>();

    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private UserDAO getDataAccess() {

        return this.dataAccess;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean login() {

        return this.getDataAccess().login(this);
    }

    public boolean saveMember() {
        return this.dataAccess.saveUser(this);
    }

    public boolean delete() {
        return this.dataAccess.deleteMember(this);

    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public boolean canManageBook() {
        boolean ret = false;
        for (Role r : this.roles) {
            ret = ret || r.canManageBook();
        }
        return ret;
    }

    @Override
    public boolean canManageLibrary() {
        boolean ret = false;
        for (Role r : this.roles) {
            ret = ret || r.canManageLibrary();
        }
        return ret;
    }

    @Override
    public boolean canManageMember() {
        boolean ret = false;
        for (Role r : this.roles) {
            ret = ret || r.canManageMember();
        }
        return ret;
    }

    @Override
    public boolean canManageAdmin() {
        boolean ret = false;
        for (Role r : this.roles) {
            ret = ret || r.canManageAdmin();
        }
        return ret;
    }

}
