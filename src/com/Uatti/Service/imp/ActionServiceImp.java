package com.Uatti.Service.imp;

import com.Uatti.Dao.ActionDao;
import com.Uatti.Dao.DaoImp.ActionDaoImp;
import com.Uatti.Service.ActionService;
import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 10:14
 * Function:
 */
public class ActionServiceImp implements ActionService {
    private ActionDao actionDao;
    @Override
    public int addActionRecord(User actUser, User pasUser, Blog blog,String comment) throws SQLException {
        actionDao = new ActionDaoImp();
        int statue = actionDao.addActionRecord(actUser,pasUser,blog,comment);
        return statue;
    }

    @Override
    public List<Action> queryAllMyActions(User user) throws SQLException {
        actionDao = new ActionDaoImp();
        List<Action> actions = actionDao.queryAllMyActions(user);
        return actions;
    }
}
