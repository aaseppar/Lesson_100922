package com.example.lesson_100922;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Lesson_100922")


public class Lesson_100922 extends HttpServlet {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/bookshop";
    private static final String user = "root";
    private static final String password = "root1";
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private String message;
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String query = "select  * from authors";
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
          ResultSetMetaData n=rs.getMetaData();
            while (rs.next()) {
                for(int i=1; i<=n.getColumnCount();i++){   //counts
                    out.println(rs.getString(i));
                }
                out.println("<br>");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        out.println("<html></body>");



    }


    public void destroy() {
    }
}