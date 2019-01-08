package com.Uatti.Service.imp;

import com.Uatti.Dao.BlogDao;
import com.Uatti.Dao.DaoImp.BlogDaoImp;
import com.Uatti.Service.BlogService;
import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-04
 * Time: 上午 10:22
 * Function:
 */
public class BlogServiceImp implements BlogService {

    private BlogDao blogDao;

    @Override
    public boolean insertBlog(Blog blog) throws Exception {
        blogDao = new BlogDaoImp();
        return blogDao.insertBlog(blog);
    }

    @Override
    public List<Blog> getAllMyBlog(String username) throws SQLException {
        blogDao  = new BlogDaoImp();
        List<Blog> blogs = new ArrayList<>();
        blogs = blogDao.getAllMyBlog(username);
        return blogs;
    }

    @Override
    public List<Blog> selectCmtBlog(String username) throws SQLException {
        blogDao  = new BlogDaoImp();
        List<Blog> blogs = new ArrayList<>();
        blogs = blogDao.selectCmtBlog(username);
        return blogs;
    }

    @Override
    public List<Blog> queryHotBlog(String username) throws SQLException {
        blogDao  = new BlogDaoImp();
        List<Blog> blogs = new ArrayList<>();
        blogs = blogDao.queryHotBlog(username);
        return blogs;
    }

    @Override
    public Long sumPageView(String username) throws SQLException {
        blogDao  = new BlogDaoImp();
        long number = blogDao.sumPageView(username);
        return number;
    }

    @Override
    public int updatePageView(Blog blog) throws SQLException {
        blogDao  = new BlogDaoImp();
        int statue  = blogDao.updatePageView(blog);
        return statue;
    }



    @Override
    public boolean updateBlog(Blog blog) throws SQLException {
        blogDao = new BlogDaoImp();
        boolean statue = blogDao.updateBlog(blog);
        return statue;
    }

    @Override
    public int deleteBlog(Blog blog) throws SQLException {
        blogDao = new BlogDaoImp();
        int statue = blogDao.deleteBlog(blog);
        return statue;
    }

    @Override
    public long queryPageView(Blog blog) throws SQLException {
        blogDao = new BlogDaoImp();
        Long statue = blogDao.queryPageView(blog);
        return statue;
    }

    @Override
    public int reportBlog(Report report) throws SQLException {
        blogDao = new BlogDaoImp();
        int statue = blogDao.reportBlog(report);
        return statue;
    }

}
