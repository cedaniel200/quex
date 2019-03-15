package com.cedaniel200.quex.executor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryPreparer {
    PreparedStatement prepare(PreparedStatement preparedStatement, Object... params) throws SQLException;
}
