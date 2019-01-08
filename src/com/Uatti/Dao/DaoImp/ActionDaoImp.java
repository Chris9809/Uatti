package com.Uatti.Dao.DaoImp;

import com.Uatti.DBUtil.DB;
import com.Uatti.Dao.ActionDao;
import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 10:04
 * Function:
 */
public class ActionDaoImp implements ActionDao {

    @Override
    public int addActionRecord(User actUser,User pasUser, Blog blog,String comment) throws SQLException {
        Connection conn = DB.getConnection();
        String sql = "insert into actions(actuser,pasuser,time,obj,comments) values(?,?,?,?,?)";
        PreparedStatement stmt= conn.prepareStatement(sql);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String time = dateFormat.format(date);
        stmt.setString(1, actUser.getUserName());
        stmt.setString(2, pasUser.getUserName());
        stmt.setString(3, time);
        stmt.setString(4, blog.getTitle());
        stmt.setString(5, comment);

        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }

    @Override
    public List<Action> queryAllMyActions(User user) throws SQLException {
        String sql = "SELECT * FROM actions where pasuser = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,user.getUserName());
        ResultSet rs = stmt.executeQuery();
        List<Action> actions = new ArrayList<>();

        while(rs.next()){
            Action a = new Action(
                    rs.getInt("aid"),
                    rs.getString("actuser"),
                    rs.getString("pasuser"),
                    rs.getString("time"),
                    rs.getString("obj"),
                    rs.getString("comments")
            );
            actions.add(a);
        }
        DB.closeAll(conn,stmt,rs);
        return actions;
    }
}
