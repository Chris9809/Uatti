package com.Uatti.test;

import com.Uatti.Dao.UserDao;
import com.Uatti.Dao.DaoImp.UserDaoImp;
import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-24
 * Time: 上午 11:16
 * Function:
 */
public class test {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoImp();
        List<User> users = new ArrayList<>();

        users = userDao.queryAllUser();

        for(User u:users){
            System.out.println(u);
        }
    }
}
