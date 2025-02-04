package com.nuketree3.example.mvckp.model.connections;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
public abstract class ConnectionToDB {
    static String HOST = "";
    static String USERNAME = "";
    static String PASSWORD = "";

    private Connection connection;

    public ConnectionToDB(String host, String username, String password) {
        HOST = host;
        USERNAME = username;
        PASSWORD = password;
    }

    public Connection getConnection() {
        return connection;
    }
}
