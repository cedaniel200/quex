package com.cedaniel200.quex.executor;

import com.cedaniel200.quex.connection.Datasource;
import com.cedaniel200.quex.exception.MapperException;
import com.cedaniel200.quex.mapper.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.cedaniel200.quex.executor.builder.QuexSetupBuilder.createQueryExecutor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class QuexTest {

    @Mock
    private Datasource datasource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private QueryPreparer preparer;
    private Mapper<Person> mapper;
    private String query;
    private Quex<Person> quex;

    @Before
    public void seupt() throws SQLException {
        initMocks(this);
        comportmentSetup();
        query = "Select id, name from person where id = ?";
        preparer = (ps, p) -> {
            ps.setLong(1, 1);
            return ps;
        };
        mapper = r -> {
            Person person = new Person();
            person.setId(r.getInt(1));
            person.setName(r.getString(2));
            return person;
        };
        quex = new Quex<>(datasource);
    }

    private void comportmentSetup() throws SQLException {
        when(datasource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Cesar Daniel");
    }

    @Test
    public void executeTest() throws SQLException, MapperException {
        Person person = quex.execute(query, preparer, mapper);

        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getName(), "Cesar Daniel");
    }

    @Test
    public void setDatasourceTest() throws SQLException, MapperException {
        quex = new Quex<>(null);
        quex.setDatasource(datasource);

        Person person = quex.execute(query, preparer, mapper);

        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getName(), "Cesar Daniel");
    }

    @Test
    public void executeWithBuilder() throws Exception {
        Person person = createQueryExecutor(datasource)
                .witQueryPreparer(preparer)
                .withQuery(query)
                .withMapper(mapper)
                .execute(Person.class);

        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getName(), "Cesar Daniel");
    }

    @Test(expected = MyException.class)
    public void executeWithBuilderFailingAndComplainingWithMyException() throws Exception {
        when(datasource.getConnection()).thenThrow(new SQLException("connection failures"));
        Person person = createQueryExecutor(datasource)
                .witQueryPreparer(preparer)
                .withQuery(query)
                .withMapper(mapper)
                .ifItFailsComplainWith(MyException.class, "My Exception")
                .execute(Person.class);
    }
}

class MyException extends Exception {
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}

class Person {

    private long id;
    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}