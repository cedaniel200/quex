package com.cedaniel200.quex.connection;

public enum TypeDataBase implements UrlFormat {

    NONE(c -> ""),
    POSTGRESQL(c -> String.format("jdbc:postgresql://%s:%s/%s",
            c.getHost(),
            c.getPort(),
            c.getNameDataBase())),
    ORACLE(c ->
        String.format("jdbc:oracle:thin:@%s:%s:%s",
                c.getHost(),
                c.getPort(),
                c.getNameDataBase())
    );

    private UrlFormat urlFormat;

    TypeDataBase(UrlFormat urlFormat) {
        this.urlFormat = urlFormat;
    }

    @Override
    public String getUrl(DatabaseConfiguration configuration) {
        return urlFormat.getUrl(configuration);
    }
}
