/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author 984970
 */
public class Query {

    public static final int OP_SELECT = 1;
    public static final int OP_INSERT = 2;
    public static final int OP_UPDATE = 3;
    public static final int OP_DELETE = 4;

    private Database db;

    private String select = "";
    private String update = "";
    private String delete = "";
    private String insert = "";
    private String from = "";
    private String limit = "";

    private List<WhereString> where = new ArrayList<>();
    private List<String> join = new ArrayList<>();
    private List<String> group = new ArrayList<>();
    private List<String> order = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    private HashMap<String, String> set = new HashMap<String, String>();

    private int operation = 1;

    public Query(Database db) {
        this.db = db;
    }

    public Query() {

    }

    public Query select(String select) {
        this.select = select;
        this.operation = Query.OP_SELECT;

        return this;
    }

    public Query update(String update) {
        this.update = update;
        this.operation = Query.OP_UPDATE;

        return this;
    }

    public Query delete(String delete) {
        this.delete = delete;
        this.operation = Query.OP_DELETE;

        return this;
    }

    public Query insert(String insert) {
        this.insert = insert;
        this.operation = Query.OP_INSERT;

        return this;
    }

    public Query where(String where, String glue) {
        if (this.where.size() == 0) {
            glue = "";
        }
        this.where.add(new WhereString(where, glue));
        return this;
    }

    public Query where(String where) {
        String glue = "AND";
        if (this.where.size() == 0) {
            glue = "";
        }
        this.where.add(new WhereString(where, glue));
        return this;
    }

    public Query join(String join, String table, String condition) {
        this.join.add(join + " JOIN " + table + " on " + condition);
        return this;
    }

    public Query order(String order) {
        this.order.add(order);
        return this;
    }

    public Query group(String group) {
        this.group.add(group);
        return this;
    }

    public Query from(String from) {
        this.from = from;
        return this;
    }

    public Query column(String column) {
        this.columns.add(column);
        return this;
    }

    public Query value(String value) {
        this.values.add(value);
        return this;
    }

    public Query limit(String limit) {
        this.limit = limit;
        return this;
    }

    public Query set(String key, String value) {
        this.set.put(key, value);
        return this;
    }

    public String getQuery() {
        String sql = "";
        switch (this.operation) {
            case Query.OP_SELECT:
                sql = getSelectQuery();
                break;

            case Query.OP_INSERT:
                sql = getInsertQuery();
                break;

            case Query.OP_UPDATE:
                sql = getUpdateQuery();
                break;

            case Query.OP_DELETE:
                sql = getDeleteQuery();
                break;

        }

        return sql;

    }

    private String getSelectQuery() throws QueryException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(this.select);
        sb.append(" FROM ");
        sb.append(this.from);

        if (this.join.size() > 0) {
            for (String jStr : this.join) {
                sb.append(" ");
                sb.append(jStr);
            }

        }

        if (this.where.size() > 0) {
            sb.append(" WHERE ");
            for (WhereString wStr : this.where) {
                if (!wStr.getGlue().equals("")) {
                    sb.append(" ");
                    sb.append(wStr.getGlue());
                    sb.append(" ");
                }
                sb.append(wStr.getCondition());
            }

        }
        if (this.group.size() > 0) {
            sb.append(" GROUP BY ");
            sb.append(this.group.get(0));
            for (int i = 1; i < this.group.size(); i++) {
                sb.append(this.group.get(i));
            }
        }

        if (this.order.size() > 0) {
            sb.append(" ORDER BY ");
            sb.append(this.order.get(0));
            for (int i = 1; i < this.order.size(); i++) {
                sb.append(this.order.get(i));
            }
        }

        if (this.limit.length() != 0) {
            sb.append("LIMIT (");
            sb.append(this.limit);
            sb.append(")");
        }
        return sb.toString();

    }

    private String getInsertQuery() throws QueryException {
        if (this.columns.size() != this.values.size()) {
            throw new QueryException("Number of Columns and Values should be equal");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append(this.insert);
        sb.append(" (");
        sb.append(this.columns.get(0));

        for (int i = 1; i < this.columns.size(); i++) {
            sb.append(",");
            sb.append(this.columns.get(i));

        }

        sb.append(")");

        sb.append(" VALUES(");
        sb.append(quote(this.values.get(0)));
        for (int i = 1; i < this.values.size(); i++) {
            sb.append(",");
            sb.append(quote(this.values.get(i)));

        }
        sb.append(")");

        return sb.toString();
    }

    private String getUpdateQuery() throws QueryException {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(this.update);
        sb.append(" SET ");

        Iterator<String> keyIterator = this.set.keySet().iterator();
        int size = this.set.size();
        int count = 0;

        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String value = this.set.get(key);
            sb.append(key + "=" + quote(value));
            if (count < (size - 1)) {
                sb.append(", ");
            }
            count++;
        }

        if (this.where.size() > 0) {
            sb.append(" WHERE ");
            for (WhereString wStr : this.where) {
                if (!wStr.getGlue().equals("")) {
                    sb.append(" ");
                    sb.append(wStr.getGlue());
                    sb.append(" ");
                }
                sb.append(wStr.getCondition());
            }

        }

        return sb.toString();

    }

    private String getDeleteQuery() throws QueryException {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ");
        sb.append(this.delete);

        if (this.where.size() > 0) {
            sb.append(" WHERE ");
            for (WhereString wStr : this.where) {
                if (!wStr.getGlue().equals("")) {
                    sb.append(" ");
                    sb.append(wStr.getGlue());
                    sb.append(" ");
                }
                sb.append(wStr.getCondition());
            }

        }

        return sb.toString();
    }

    public String quote(String str) {
        return "\"" + str + "\"";
    }

}

class WhereString {

    private String condition;
    private String glue;

    WhereString(String cond, String glue) {
        this.condition = cond;
        this.glue = glue;
    }

    public String getCondition() {
        return condition;
    }

    public String getGlue() {
        return glue;
    }

}
