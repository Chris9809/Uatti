package com.Uatti.DBUtil;

import java.sql.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 9:18
 * Created with IntelliJ IDEA.
 */
public class DB {
    //驱动名字
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/blog";
    static final String USER = "root";
    static final String PASS = "";

        static {
            try {
                //反射
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    public static Connection getConnection() throws SQLException {
            return DriverManager
                .getConnection(DB_URL, USER,PASS);
    }

    public static void closeAll(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
        if(rs!=null) {
            rs.close();
        }
        if(stmt!=null) {
            stmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }
    public int executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        /* 处理SQL,执行SQL */
        try {
            conn = getConnection(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            ResultSet num = pstmt.executeQuery(); // 执行SQL语句
        } catch (SQLException e) {
            e.printStackTrace(); // 处理SQLException异常
        } finally {
            try {
                DB.closeAll(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
