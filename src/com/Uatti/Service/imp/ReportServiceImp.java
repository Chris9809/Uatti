package com.Uatti.Service.imp;

import com.Uatti.Dao.DaoImp.ReportDaoImp;
import com.Uatti.Dao.ReportDao;
import com.Uatti.Service.ReportService;
import com.Uatti.entity.Report;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-08
 * Time: 上午 8:19
 * Function:
 */
public class ReportServiceImp implements ReportService {
    private ReportDao reportDao = new ReportDaoImp();
    @Override
    public List<Report> queryAllRepoter() throws SQLException {
        reportDao = new ReportDaoImp();
        List<Report> reports = reportDao.queryAllRepoter();
        return reports;
    }
}
