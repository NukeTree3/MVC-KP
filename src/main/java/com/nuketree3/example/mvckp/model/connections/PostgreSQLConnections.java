package com.nuketree3.example.mvckp.model.connections;

import java.sql.*;
//5432
//12345
public class PostgreSQLConnections extends ConnectionToDB{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public PostgreSQLConnections(String host, String username, String password) {
        super(host, username, password);
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    }

    public void getAllCloseConnectionResurses(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public void closeConnection() throws SQLException {
        this.statement.close();
        this.connection.close();
    }
}
