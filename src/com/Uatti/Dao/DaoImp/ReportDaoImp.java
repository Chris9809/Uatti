package com.Uatti.Dao.DaoImp;

import com.Uatti.DBUtil.DB;
import com.Uatti.Dao.ReportDao;
import com.Uatti.entity.Report;
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
 * Date: 2019-01-08
 * Time: 上午 8:12
 * Function:
 */
public class ReportDaoImp implements ReportDao {
    @Override
    public List<Report> queryAllRepoter() throws SQLException {
        String sql = "SELECT * FROM report";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Report> reports = new ArrayList<>();

        while(rs.next()){
            Report report = new Report(
                    rs.getInt("rid"),
                    rs.getInt("bid"),
                    rs.getString("username"),
                    rs.getString("title")
            );
            reports.add(report);
        }
        DB.closeAll(conn,stmt,rs);
        return reports;
    }
}
