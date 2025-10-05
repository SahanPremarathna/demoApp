package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;
    public static String DATA_BASE_NAME = "demologinpage";
    public static String DATA_TABLE_NAME = "studentdata";
    protected static String CREDENTIAL_TABLE_NAME = "useraccounts";

    public Connection getConnection(){
//        String databaseName = "demologinpage";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + DATA_BASE_NAME;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }

        return databaseLink;

    }

    public static String getCredentialTableName(){
        return CREDENTIAL_TABLE_NAME;
    }


}
