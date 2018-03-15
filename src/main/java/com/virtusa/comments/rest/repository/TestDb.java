package com.virtusa.comments.rest.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDb {
	public static void main(String a[]) throws Exception {
		  Connection connect = null;
		  Statement statement = null;
		  PreparedStatement preparedStatement = null;
		  ResultSet resultSet = null;
		  Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager
		          .getConnection("jdbc:mysql://" + "127.0.0.1" + "/comments?"
		              + "user=" + "root" + "&password=" + "admin@123");

	}
}
