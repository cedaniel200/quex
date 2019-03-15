package com.cedaniel200.quex.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatasourceDefault implements Datasource {

    private DatabaseConfiguration databaseConfiguration;

    public DatasourceDefault(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return createConnection(databaseConfiguration, null, null);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return createConnection(databaseConfiguration, username, password);
    }

    private Connection createConnection(DatabaseConfiguration databaseConfiguration, String user, String password){
        Connection connection = null;
        try{

            Class.forName(databaseConfiguration.getJdbcDriver());
            connection = DriverManager.getConnection (databaseConfiguration.getUrlJDBC(),
                    databaseConfiguration.getUsername(),
                    databaseConfiguration.getPassword());

        }catch (ClassNotFoundException e){
            System.err.println("Could not load JDBC driver");
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }catch(SQLException ex){
            System.err.println("SQLException information " + ex);
        }
        return connection;
    }
}
