package com.cedaniel200.quex.executor.builder;

import com.cedaniel200.quex.executor.Quex;
import com.cedaniel200.quex.mapper.Mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class QuexExecuteBuilder {

    private QuexMapperBuilder quexMapperBuilder;
    private Class exceptionToComplain;
    private String message;

    QuexExecuteBuilder(QuexMapperBuilder quexMapperBuilder) {
        this.quexMapperBuilder = quexMapperBuilder;
    }

    public <E extends Exception> QuexExecuteBuilder ifItFailsComplainWith(Class<E> exceptionToComplain, String message) {
        this.exceptionToComplain = exceptionToComplain;
        this.message = message;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(Class<T> classForMapper) throws Exception {
        Quex<T> quex = new Quex<>(quexMapperBuilder.getQuexQueryBuilder().getQuexSetupBuilder().getDatasource());
        try {
            Mapper<T> mapper = quexMapperBuilder.getMapper();
            return quex.execute(quexMapperBuilder.getQuexQueryBuilder().getQuery(),
                    quexMapperBuilder.getQuexQueryBuilder().getQuexSetupBuilder().getPreparer(),
                    mapper
            );
        } catch (Exception e) {
            throw complain(e);
        }
    }

    private Exception complain(Exception exception) {
        if (exceptionToComplain != null) {
            try {
                Class<?> myClassType = Class.forName(exceptionToComplain.getName());
                Class<?>[] types = new Class[]{String.class, Throwable.class};
                Constructor constructor = myClassType.getConstructor(types);
                constructor.setAccessible(true);
                Object object = constructor.newInstance(message, exception.getCause());
                return (Exception) object;
            } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException | InstantiationException | InvocationTargetException e1) {
                e1.printStackTrace();
                return exception;
            }
        }
        return exception;
    }
}
