package com.Uatti.test;

import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.User;

import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 上午 8:29
 * Created with IntelliJ IDEA.
 */
public class TestLogin {
    public static void main(String[] args) throws Exception {
        // write your code here
        List<User> userList = null;
        UserService userService = new UserServiceImp();
        try {
            userList = userService.queryAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User u:
                userList) {
            System.out.println(u);
        }
        System.out.println("你好！");
    }
}
