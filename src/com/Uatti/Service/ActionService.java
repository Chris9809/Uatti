package com.Uatti.Service;

import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 10:13
 * Function:
 */
public interface ActionService {
    /**
     *
     * @param actUser
     * @param pasUser
     * @param blog
     * @param obj
     * @param comment
     * @return
     * @throws SQLException
     */
    int addActionRecord(User actUser, User pasUser, Blog blog, String comment) throws SQLException;

    /**
     *
     * @param user
     * @return
     * @throws SQLException
     */
    List<Action> queryAllMyActions(User user) throws SQLException;
}
