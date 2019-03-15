package com.cedaniel200.quex.executor.builder;

import com.cedaniel200.quex.mapper.Mapper;

public class QuexMapperBuilder {

    private QuexQueryBuilder quexQueryBuilder;
    private Mapper mapper;

    QuexMapperBuilder(QuexQueryBuilder quexQueryBuilder) {
        this.quexQueryBuilder = quexQueryBuilder;
    }

    public QuexExecuteBuilder withMapper(Mapper mapper){
        this.mapper = mapper;
        return new QuexExecuteBuilder(this);
    }

    QuexQueryBuilder getQuexQueryBuilder() {
        return quexQueryBuilder;
    }

    Mapper getMapper() {
        return mapper;
    }
}
