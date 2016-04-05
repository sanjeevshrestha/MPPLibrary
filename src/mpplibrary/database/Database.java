/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.database;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.sql.*;

/**
 *
 * @author 984970
 */
public class Database {

    private Properties prop;
    private Query query;
    private Connection c;

    private boolean installed = false;

    public Database() {
        installed = false;

        try {

            this.query = new Query();
            prop = new Properties();
            InputStream infile = Database.class.getResourceAsStream("/mpplibrary/resources/mpplibrary.properties");
            prop.load(infile);

            Path path = Paths.get("./mpplibrary.db");

            if (Files.exists(path)) {
                installed = true;
            }

            this.connect();

            if (!installed) {
                this.initialize();
            }

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

    public long execute() throws QueryException {
        long insertID = 0;

        try {
            String sql = this.query.getQuery();
            Statement stmt = null;
            stmt = this.c.createStatement();
            stmt.executeUpdate(sql);

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while (generatedKeys.next()) {
                insertID = generatedKeys.getLong(1);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return insertID;
    }

    public ResultSet getResultSet() throws QueryException {
        ResultSet rs = null;
        try {
            String sql = this.query.getQuery();
            Statement stmt = null;
            stmt = this.c.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Database connect() {
        try {
            Class.forName(this.prop.getProperty("driver"));
            c = DriverManager.getConnection(this.prop.getProperty("connectionPath"));

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return this;

    }

    public Database initialize() {

        try {
            InputStream infile = Database.class.getResourceAsStream(this.prop.getProperty("installfile"));

            StringBuilder sb = new StringBuilder();
            int content;
            while ((content = infile.read()) != -1) {
                sb.append((char) content);
            }

            String sql = sb.toString();
            Statement stmt = null;
            for (String sqlPart : sql.split(";")) {
                stmt = this.c.createStatement();
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this;
    }

    public void close() throws SQLException {
        this.c.close();

    }

    public static void main(String[] args) {
        Database db = new Database();
        db.connect();
        db.initialize();
    }

}
