package com.Uatti.Dao.DaoImp;

import com.Uatti.DBUtil.DB;
import com.Uatti.Dao.UserDao;
import com.Uatti.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-24
 * Time: 上午 11:09
 * Function:
 */
public class UserDaoImp implements UserDao {
    @Override
    public List<User> queryAllUser() throws SQLException {
        String sql = "SELECT * FROM users";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();

        while(rs.next()){
            User user = new User(
                    rs.getInt("uid"),
                    rs.getString("username"),
                    rs.getString("userpassword"),
                    rs.getString("phone"),
                    rs.getString("email")
            );
            users.add(user);
        }
        DB.closeAll(conn,stmt,rs);
        return users;
    }

    @Override
    public User checkUserPwd(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users where username= ? AND userpassword = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        stmt.setString(1,userName);
        stmt.setString(2,password);

        ResultSet rs = stmt.executeQuery();

        User user = null;
        while(rs.next()){
            user = new User(
                    rs.getInt("uid"),
                    rs.getString("username"),
                    rs.getString("userpassword"),
                    rs.getString("phone"),
                    rs.getString("email")
            );
        }

        DB.closeAll(conn,stmt,rs);
        if(user==null){
            return null;
        } else{return user;}
    }

    @Override
    public int insertUser(User user) throws SQLException {
        return 0;
    }

    @Override
    public int queryIsExisted(String username ) throws SQLException {
        String sql = "SELECT * FROM users where username = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();

        while(rs.next()){
            User user = new User(
                    rs.getInt("uid"),
                    rs.getString("username"),
                    rs.getString("userpassword"),
                    rs.getString("phone"),
                    rs.getString("email")
            );
            users.add(user);
        }
        DB.closeAll(conn,stmt,rs);
        return 0;
    }

    @Override
    public User queryUserInfo(String username) throws SQLException {
        String sql = "SELECT * FROM users where username= ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        User user = null;
        while(rs.next()){
            user = new User(
                    rs.getInt("uid"),
                    rs.getString("username"),
                    rs.getString("userpassword"),
                    rs.getString("phone"),
                    rs.getString("email")
            );
        }
        DB.closeAll(conn,stmt,rs);
        if(user==null){
            return new User("账号已注销","123456","10000000000","10000@qq.com");
        } else{return user;}
    }

    @Override
    public int updatePhone(String username, String phone) throws SQLException {
        String sql = "update users set phone = ? where username = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        stmt.setString(1,phone);
        stmt.setString(2,username);

        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }

    @Override
    public int updateEmail(String username, String email) throws SQLException {
        String sql = "update users set email = ? where username = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        stmt.setString(1,email);
        stmt.setString(2,username);

        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }

    @Override
    public int updatePassword(String username, String password) throws SQLException {
        String sql = "update users set userpassword = ? where username = ?";

        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        stmt.setString(1,password);
        stmt.setString(2,username);

        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }


}
