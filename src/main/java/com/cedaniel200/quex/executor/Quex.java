package com.cedaniel200.quex.executor;

import com.cedaniel200.quex.connection.Datasource;
import com.cedaniel200.quex.exception.MapperException;
import com.cedaniel200.quex.mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Quex<T> implements QueryExecutor<T> {

    private Datasource datasource;

    public Quex(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public T execute(String query, QueryPreparer queryPreparer, Mapper<T> mapper) throws SQLException, MapperException {
        try (Connection connection = this.datasource.getConnection();
             PreparedStatement declarationToPrepare = connection.prepareStatement(query);
             ResultSet resultSet = queryPreparer.prepare(declarationToPrepare).executeQuery()
        ) {
            return mapper.mapOut(resultSet);
        }
    }

}