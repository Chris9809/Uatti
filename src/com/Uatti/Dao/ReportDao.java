package com.Uatti.Dao;

import com.Uatti.entity.Report;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-08
 * Time: 上午 8:11
 * Function:
 */
public interface ReportDao {
    /***
     *
     * @return
     * @throws SQLException
     */
    List<Report> queryAllRepoter() throws SQLException;
}
