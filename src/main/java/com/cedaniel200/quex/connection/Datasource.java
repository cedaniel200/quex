package com.cedaniel200.quex.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface Datasource {

    Connection getConnection() throws SQLException;;

    Connection getConnection(String username, String password)
            throws SQLException;
}
