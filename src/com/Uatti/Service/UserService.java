package com.Uatti.Service;

import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 10:12
 * Created with IntelliJ IDEA.
 */
public interface UserService {
    /**
     * 命令行输出所有用户
     * @return
     * @throws Exception
     */
    List<User> queryAllUser() throws Exception;

    /**
     * 登陆验证——传入用户名和密码验证
     * @param userName
     * @param password
     * @return true/false 登陆成功，返回true
     * @throws Exception
     */
     User checkUserPwd(String userName,String password) throws Exception;

    /**
     * 注册——插入一个用户
     * @param user
     * @return true/false 注册成功/失败，返回true/false
     * @throws Exception
     */
    Boolean insertUser(User user) throws Exception;

    /**
     * 检查用户是否存在
     * @param username
     * @return
     * @throws Exception
     */
    Boolean queryIsExisted(String username) throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    User queryUserInfo(String username) throws Exception;

    int updatePhone(String username,String phone) throws SQLException;

    int updateEmail(String username,String email) throws SQLException;

    int updatePassword(String username,String password) throws SQLException;
}
