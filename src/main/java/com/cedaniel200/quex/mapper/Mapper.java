package com.cedaniel200.quex.mapper;

import com.cedaniel200.quex.exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T mapOut(ResultSet resultSet) throws MapperException, SQLException;
}