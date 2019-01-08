package com.Uatti.Service;

import com.Uatti.entity.Report;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-08
 * Time: 上午 8:18
 * Function:
 */
public interface ReportService{

    List<Report> queryAllRepoter() throws SQLException;

}
