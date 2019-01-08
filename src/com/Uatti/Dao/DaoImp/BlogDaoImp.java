package com.Uatti.Dao.DaoImp;

import com.Uatti.DBUtil.DB;
import com.Uatti.Dao.BlogDao;
import com.Uatti.entity.Blog;
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
 * Date: 2018-11-24
 * Time: 上午 11:09
 * Function:
 */
public class BlogDaoImp implements BlogDao {

    @Override
    public boolean insertBlog(Blog blog) throws SQLException {
        Connection conn = DB.getConnection();
        String sql = "insert into blogs(username,time, title, content, pageview) values(?,?,?,?,?)";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, blog.getUsername());//设置占位符的值
        stmt.setString(2, blog.getTime());
        stmt.setString(3, blog.getTitle());
        System.out.println(blog.getTitle());
        System.out.println(blog.getContent());
        stmt.setString(4,blog.getContent());
        stmt.setLong(5,0);
        int rs = stmt.executeUpdate();
        stmt.close();
        if(rs==1)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateBlog(Blog blog) throws SQLException {
        Connection conn = DB.getConnection();
        String sql = "update blogs set title = ?, content = ? where bid = ?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, blog.getTitle());
        stmt.setString(2, blog.getContent());
        stmt.setInt(3, blog.getbId());
        System.out.println(blog.getbId()+blog.getTitle()+blog.getContent());
        int rs = stmt.executeUpdate();
        stmt.close();
        if(rs==1)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int deleteBlog(Blog blog) throws SQLException {
        Connection conn = DB.getConnection();
        String sql = "DELETE FROM blogs WHERE bid = ?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setInt(1, blog.getbId());
        int statue = stmt.executeUpdate();
        stmt.close();
        return statue;
    }

    @Override
    public List<Blog> getAllMyBlog(String username) throws SQLException {
        String sql = "SELECT * FROM blogs where username = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        List<Blog> blogs = new ArrayList<>();

        while(rs.next()){
            Blog blog = new Blog(
                    rs.getInt("bid"),
                    rs.getString("username"),
                    rs.getString("time"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getLong("pageview")
            );
           blogs.add(blog);
        }
        DB.closeAll(conn,stmt,rs);
        return blogs;
    }

    @Override
    public List<Blog> selectCmtBlog(String username) throws SQLException {
        String sql = "SELECT * FROM blogs where username <> ? order By Rand() Limit 10";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        List<Blog> blogs = new ArrayList<>();

        while(rs.next()){
            Blog blog = new Blog(
                    rs.getInt("bid"),
                    rs.getString("username"),
                    rs.getString("time"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getLong("pageview")
            );
            blogs.add(blog);
        }
        DB.closeAll(conn,stmt,rs);
        return blogs;
    }

    @Override
    public List<Blog> queryHotBlog(String username) throws SQLException {
        String sql = "SELECT * FROM blogs where username <> ? order By pageview desc";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        List<Blog> blogs = new ArrayList<>();

        while(rs.next()){
            Blog blog = new Blog(
                    rs.getInt("bid"),
                    rs.getString("username"),
                    rs.getString("time"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getLong("pageview")
            );
            blogs.add(blog);
        }
        DB.closeAll(conn,stmt,rs);
        return blogs;
    }

    @Override
    public Long sumPageView(String username) throws SQLException {
        String sql = "select * from blogs where username = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet rs = stmt.executeQuery();
        long number = 0;
        List<Blog> blogs = new ArrayList<>();

        while(rs.next()){
            Blog blog = new Blog(
                    rs.getInt("bid"),
                    rs.getString("username"),
                    rs.getString("time"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getLong("pageview")
            );
            number+=blog.getPageView();
        }
        DB.closeAll(conn,stmt,rs);
        return number;
    }

    @Override
    public int updatePageView(Blog blog) throws SQLException {
        String sql = "update blogs set pageview = pageview+1 where bid = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setInt(1,blog.getbId());
        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }

    @Override
    public long queryPageView(Blog blog) throws SQLException {
        String sql = "select pageview from blogs where bid = ?";
        Connection conn = DB.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setInt(1,blog.getbId());
        ResultSet rs = stmt.executeQuery();
        long number = 0;
        while(rs.next()){
            number+=rs.getLong("pageview");
        }
        DB.closeAll(conn,stmt,rs);
        return number;
    }

    @Override
    public int reportBlog(Report report) throws SQLException {
        Connection conn = DB.getConnection();
        String sql = "insert into report(bid,username,title) values(?,?,?)";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setInt(1, report.getBid());
        stmt.setString(2, report.getUsername());
        stmt.setString(3, report.getTitle());
        System.out.println(report.getBid());
        System.out.println(report.getUsername());
        System.out.println(report.getTitle());

        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }


}
