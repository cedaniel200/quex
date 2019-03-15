package com.cedaniel200.quex.connection;

public class DatabaseConfiguration {

    private String jdbcDriver;
    private String nameDataBase;
    private String host;
    private String port;
    private String username;
    private String password;
    private String suffix = "";
    private UrlFormat format;

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getNameDataBase() {
        return nameDataBase;
    }

    public void setNameDataBase(String nameDataBase) {
        this.nameDataBase = nameDataBase;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public UrlFormat getFormat() {
        return format;
    }

    public void setFormat(UrlFormat format) {
        this.format = format;
    }

    public String getUrlJDBC(){
        return this.format.getUrl(this);
    }
}
