package com.Uatti.Service.imp;

import com.Uatti.DBUtil.DB;
import com.Uatti.Dao.UserDao;
import com.Uatti.Dao.DaoImp.UserDaoImp;
import com.Uatti.Service.UserService;
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
 * Date: 2018-11-03
 * Time: 上午 10:14
 * Created with IntelliJ IDEA.
 */
public class UserServiceImp implements UserService {

    private UserDao userDao;
    private static final String TAG_FALSE = "数据库连接错误! 错误语句：";
    private static final String TAG_SUCCESS = "数据库连接成功!";

    @Override
    public List<User> queryAllUser() throws Exception {
        userDao = new UserDaoImp();
        List<User> users = new ArrayList<>();
        users = userDao.queryAllUser();
        return users;
    }
    @Override
    public User checkUserPwd(String userName, String password) throws Exception{
        userDao = new UserDaoImp();
        User user = userDao.checkUserPwd(userName,password);
        return user;
    }

    @Override
    public Boolean insertUser(User user) throws Exception {

        //注册用户默认id是自增的，这里0知识为了填充
        String sql = "INSERT INTO users (uid,username,userpassword,phone,email) VALUES ("+
                "'"+0+"',"+
                "'"+ user.getUserName()+"',"+
                "'"+user.getUserPassword()+"',"+
                "'"+user.getPhone()+"',"+
                "'"+user.getEmail()+"'"+
                ")";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        int rs = stmt.executeUpdate();
        stmt.close();

        if(rs==1)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean queryIsExisted(String username) throws Exception {
        String sql = "SELECT * FROM users where username = '"+username+"'";
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

        if(users.isEmpty()) {
            //查不到存在返回false
            return false;
        } else{
            return true;
        }
    }

    @Override
    public User queryUserInfo(String username) throws Exception {
        userDao = new UserDaoImp();
        User user = userDao.queryUserInfo(username);
        return user;
    }

    @Override
    public int updatePhone(String username, String phone) throws SQLException {
        userDao = new UserDaoImp();
        int statue = userDao.updatePhone(username,phone);
        return statue;
    }

    @Override
    public int updateEmail(String username, String email) throws SQLException {
        userDao = new UserDaoImp();
        int statue = userDao.updateEmail(username,email);
        return statue;
    }

    @Override
    public int updatePassword(String username, String password) throws SQLException {
        userDao = new UserDaoImp();
        int statue = userDao.updatePassword(username,password);
        return statue;
    }


}
