package com.Uatti.Dao;

import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 10:03
 * Function:
 */
public interface ActionDao {
    int addActionRecord(User actUser,User pasUser, Blog blog,String comment) throws SQLException;
    List<Action> queryAllMyActions(User user) throws SQLException;
}
