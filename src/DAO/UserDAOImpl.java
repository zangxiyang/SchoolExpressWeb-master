package DAO;

import Beans.UserBean;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 关于用户层的 DAO接口实现层
 */
public class UserDAOImpl implements IDao {
    String TAG = "DAO";
    @Override
    public void insert(Object obj) throws SQLException {
        ConnectionDAO connectionDAO = null ;
        PreparedStatement ps = null ;
        String sql =
                "INSERT INTO end_users(userName,passWord) VALUES (?,?)"
                ;
        try {
            connectionDAO = ConnectionDAO.getInstance();
            System.out.println(connectionDAO.getConnection());
            ps = connectionDAO.getConnection().prepareStatement(sql);
            //进行强制转换
            UserBean userBean = (UserBean) obj;
            String userName = userBean.getUserName();
            String passWord = userBean.getPassWord();
            System.out.println("接口数据"+userName);
            int uid = userBean.getUid();
            ps.setString(1,userName);
            ps.setString(2,passWord);
            ps.executeUpdate();
            if (ps != null){
                ps.clearParameters();
                ps.close();
            }

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG+"驱动注册失败");
        }finally {
            try {
                connectionDAO.close();
                System.out.println(TAG+"数据库关闭");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库时出现未知错误:"+TAG);
            }
        }


    }

    @Override
    public void delete(int key) {
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql =
                "DELETE FROM end_users WHERE uid=?"
                ;

        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            ps.executeUpdate();
            if (ps != null){
                ps.clearParameters();
                ps.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        } finally {

            try {
                connectionDAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库时出现未知错误:" + TAG);
            }
        }
    }

    @Override
    public void update(Object obj) {
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql =
                "UPDATE end_users SET userName=?,passWord=? WHERE uid=?"
                ;
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            UserBean userBean = (UserBean) obj;
            String userName = userBean.getUserName();
            String passWord = userBean.getPassWord();
            int uid = userBean.getUid();
            ps.setInt(3,uid);
            ps.setString(1,userName);
            ps.setString(2,passWord);
            ps.executeUpdate();
            if (ps != null){
                ps.clearParameters();
                ps.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        } finally {

            try {
                connectionDAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库时出现未知错误:" + TAG);
            }
        }
    }

    @Override
    public Object select(int key) {
        UserBean userBean = null ;
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql =
                "SELECT * FROM end_users WHERE uid=?"
                ;
        try {
            connectionDAO = ConnectionDAO.getInstance();
            //预处理参数
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            //返回的执行结果
            rs = ps.executeQuery();
            //获取结果
            while (rs.next()){
                userBean = new UserBean();
                userBean.setUid(rs.getInt("uid"));
                userBean.setUserName(rs.getString("userName"));
                userBean.setPassWord(rs.getString("passWord"));
            }
            if (ps != null){
                ps.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        } finally {

            try {
                connectionDAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库时出现未知错误:" + TAG);
            }
        }


        return userBean;
    }

    @Override
    public List selectAll() {
        List<UserBean> beans = new ArrayList<UserBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql =
                "SELECT * FROM end_users"
                ;
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            //返回查询的结果
            rs = ps.executeQuery();
            while (rs.next()){
                UserBean userBean = new UserBean();
                userBean.setUid(rs.getInt("uid"));
                userBean.setPassWord(rs.getString("passWord"));
                userBean.setUserName(rs.getString("userName"));
                beans.add(userBean);
            }

            if (ps != null){
                ps.close();
            }



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        } finally {

            try {
                connectionDAO.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库时出现未知错误:" + TAG);
            }
        }

        return beans;
    }


}
