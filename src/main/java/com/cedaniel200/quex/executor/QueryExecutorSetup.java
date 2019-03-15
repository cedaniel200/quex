package com.cedaniel200.quex.executor;

import com.cedaniel200.quex.connection.Datasource;

public interface QueryExecutorSetup {
    void setDatasource(Datasource datasource);
}
