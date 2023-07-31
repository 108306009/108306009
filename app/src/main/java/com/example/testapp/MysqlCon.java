package com.example.testapp;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCon {

    // 資料庫定義
    String mysql_ip = "127.0.0.1";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "fish_tank1";
    //String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String url = "jdbc:mysql://www.db4free.net/fish_tank1";
    String db_user = "fishtank1";
    String db_password = "108306009";

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public String getData() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM `daily`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                String id = rs.getString("daily_id");
                String name = rs.getString("user_name");
                String taskname = rs.getString("daily_name");
                String goal = rs.getString("goal");
                String done = rs.getString("done");
                String color = rs.getString("color");
                data += id + ", " + name + "\n";
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


}