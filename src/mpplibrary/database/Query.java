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
public class Query {

    private Database db;

    private String select;
    private String update;

    private String delete;
    private String where;
    private String join;
    private String insert;

    private String order;
    private String group;
    private String from;

    private String set;

    public Query(Database db) {
        this.db = db;
    }

    public Query() {

    }

    public Query select(String select) {
        this.select = select;
        return this;
    }

    public Query update(String update) {
        this.update = update;
        return this;
    }

    public Query delete(String delete) {
        this.delete = delete;
        return this;
    }

    public Query insert(String insert) {
        this.insert = insert;
        return this;
    }

    public Query where(String where) {
        this.where = where;
        return this;
    }

    public Query join(String join) {
        this.join = join;
        return this;
    }

    public Query order(String order) {
        this.order = order;
        return this;
    }

    public Query group(String group) {
        this.group = group;
        return this;
    }

    public Query from(String from) {
        this.from = from;
        return this;
    }
    
    public String getQuery()
    {
        return "select * from users";
    }

}
