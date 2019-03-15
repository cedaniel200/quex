package com.cedaniel200.quex.executor.builder;

import com.cedaniel200.quex.connection.Datasource;
import com.cedaniel200.quex.executor.QueryPreparer;

public class QuexSetupBuilder {

    private Datasource datasource;
    private QueryPreparer preparer;

    private QuexSetupBuilder(Datasource datasource){
        this.datasource = datasource;
    }

    public static QuexSetupBuilder createQueryExecutor(Datasource datasource){
        return new QuexSetupBuilder(datasource);
    }

    public QuexQueryBuilder witQueryPreparer(QueryPreparer preparer){
        this.preparer = preparer;
        return new QuexQueryBuilder(this);
    }

    Datasource getDatasource() {
        return datasource;
    }

    QueryPreparer getPreparer() {
        return preparer;
    }
}
