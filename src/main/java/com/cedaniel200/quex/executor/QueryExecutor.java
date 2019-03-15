package com.cedaniel200.quex.executor;

import com.cedaniel200.quex.exception.MapperException;
import com.cedaniel200.quex.mapper.Mapper;

import java.sql.SQLException;

public interface QueryExecutor<T> extends QueryExecutorSetup {
    T execute(String query, QueryPreparer queryPreparer, Mapper<T> mapper) throws SQLException, MapperException;
}
