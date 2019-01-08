package com.Uatti.Service;

import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;

import java.sql.SQLException;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-04
 * Time: 上午 10:24
 * Function:
 */
public interface BlogService {
    /**
     *
     * @param blog
     * @return
     * @throws Exception
     */
    boolean insertBlog(Blog blog) throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    List<Blog> getAllMyBlog(String username) throws SQLException;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    List<Blog> selectCmtBlog(String username) throws SQLException;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    List<Blog> queryHotBlog(String username) throws SQLException;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    Long sumPageView(String username) throws SQLException;

    /**
     *
     * @param blog
     * @return
     * @throws SQLException
     */
    int updatePageView(Blog blog) throws SQLException;

    /**
     *
     * @param blog
     * @return
     * @throws SQLException
     */
    boolean updateBlog(Blog blog) throws SQLException;

    /**
     *
     * @param blog
     * @return
     * @throws SQLException
     */
    int deleteBlog(Blog blog) throws SQLException;

    long queryPageView(Blog blog) throws SQLException;

    /**
     *
     * @param report
     * @return
     * @throws SQLException
     */
    int reportBlog(Report report) throws SQLException;
}
