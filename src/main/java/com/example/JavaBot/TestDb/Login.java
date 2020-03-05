package com.example.JavaBot.TestDb;

import com.example.JavaBot.Entity.Questions;

import java.sql.*;

public class Login {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public Login(){
        try{
//            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/botsql", "root", "password");
            st = con.createStatement();
        } catch (Exception e){
            System.out.println("SQL error");
        }
    }

    public String get(String chatId, Long id) {
        try{

            PreparedStatement st = null;
            String query = "Select * From questions where id =" +id;
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            rs.next();
            return rs.getString(2);
        }catch (SQLException e){
            e.printStackTrace();
        } return "НЕ РАБОТЕТ";
    }
    public String setQuestion(String[] q) {
        String query = "INSERT INTO questions (answer, question) VALUES (?, ?)";

        try{
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, q[0]);
            st.setString(2, q[1]);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return "не добавлено";
        } return "добавлено";
    }

    }