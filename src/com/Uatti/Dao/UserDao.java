package com.Uatti.Dao;

import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;
import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-24
 * Time: 上午 11:08
 * Function:
 */
public interface UserDao {
    /**
     *
     * @return
     * @throws SQLException
     */
    List<User> queryAllUser() throws SQLException;

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    User checkUserPwd(String userName, String password)throws SQLException;

    /**
     *
     * @param user
     * @return
     */
    int insertUser(User user)throws SQLException;

    /**
     *
     * @param username
     * @return
     */
    int queryIsExisted(String username )throws SQLException;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    User queryUserInfo(String username) throws  SQLException;

    int updatePhone(String username,String phone) throws SQLException;

    int updateEmail(String username,String email) throws SQLException;

    int updatePassword(String username,String password) throws SQLException;


}
