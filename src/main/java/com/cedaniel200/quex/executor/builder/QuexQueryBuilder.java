package com.cedaniel200.quex.executor.builder;

public class QuexQueryBuilder {

    private QuexSetupBuilder quexSetupBuilder;
    private String query;

    QuexQueryBuilder(QuexSetupBuilder quexSetupBuilder) {
        this.quexSetupBuilder = quexSetupBuilder;
    }

    public QuexMapperBuilder withQuery(String query){
        this.query = query;
        return new QuexMapperBuilder(this);
    }

    QuexSetupBuilder getQuexSetupBuilder() {
        return quexSetupBuilder;
    }

    String getQuery() {
        return query;
    }
}
