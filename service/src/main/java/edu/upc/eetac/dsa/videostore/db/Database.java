package edu.upc.eetac.dsa.videostore.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * Created by marc on 16/12/15.
 *
 * Actualizado el 3/1/16 por errores de hikari.propierties
 * Código actualizado en: https://gist.github.com/smachado/2c94e30024b602b7deb3
 */
public class Database {
    private static Database instance = null;
    private DataSource ds;

    private Database() {
        PropertyResourceBundle prb = (PropertyResourceBundle) ResourceBundle.getBundle("hikari");
        Enumeration<String> keys = prb.getKeys();
        Properties properties = new Properties();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            properties.setProperty(key, prb.getString(key));
        }

        //HikariConfig config = new HikariConfig(Database.class.getClassLoader().getResource("hikari.properties").getFile());
        HikariConfig config = new HikariConfig(properties);
        ds = new HikariDataSource(config);
    }

    private final static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public final static Connection getConnection() throws SQLException {
        return getInstance().ds.getConnection();
    }
}
