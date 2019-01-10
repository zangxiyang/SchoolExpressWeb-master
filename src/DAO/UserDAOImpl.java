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

    final String TABLE_USERS = "end_users";
    final String TABLE_COURIERS = "end_couriers";
    final String TABLE_ADMINS = "end_admins";

    String TAG = "DAO";
    @Override
    public void insert(Object obj,int flag) throws SQLException {
        ConnectionDAO connectionDAO = null ;
        PreparedStatement ps = null ;
        String sql = null;
        if (flag == 0) {
            sql = "INSERT INTO end_users(userName,passWord) VALUES (?,?)";
        }else if (flag == 1){
            sql ="INSERT INTO end_couriers(userName,passWord) VALUES (?,?)";
        }

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
    public void delete(int key,int flag) {
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql = null;
         if (flag == 0) {
             sql = "DELETE FROM end_users WHERE uid=?";
         }else if (flag == 1){
             sql = "DELETE FROM end_couriers WHERE uid=?";
         }
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
    public void update(Object obj,int flag) {
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql = null ;
        if (flag == 0) {
            sql = "UPDATE end_users SET userName=?,passWord=? WHERE uid=?";
        }else if (flag ==1 ){
            sql = "UPDATE end_couriers SET userName=?,passWord=? WHERE uid=?";
        }
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
    public Object select(String key,int flag) {
        UserBean userBean = null ;
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = null ;

        if (flag == 0 ){
            sql = "SELECT * FROM end_users WHERE userName=?";
        }else if (flag == 1){
            sql = "SELECT * FROM end_couriers WHERE userName=?";
        }else if (flag == 2){
            sql = "SELECT * FROM "+TABLE_ADMINS+" WHERE userName=?";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            //预处理参数
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setString(1,key);
            //返回的执行结果
            rs = ps.executeQuery();
            //获取结果
            if (flag == 0 || flag == 1) {
                if (rs.next()) {
                        userBean = new UserBean();
                        userBean.setUid(rs.getInt("uid"));
                        userBean.setUserName(rs.getString("userName"));
                        userBean.setPassWord(rs.getString("passWord"));
                }

            }else if (flag == 2){
                //管理员
                if (rs.next()) {
                        userBean = new UserBean();
                        userBean.setUid(rs.getInt("aid"));
                        userBean.setUserName(rs.getString("userName"));
                        userBean.setPassWord(rs.getString("passWord"));
                }
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
    public List selectAll(int flag) {
        List<UserBean> beans = new ArrayList<UserBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = null;
        if (flag == 0){
            sql = "SELECT * FROM end_users";
        }else if (flag == 1 ){
            sql = "SELECT * FROM end_couriers";
        }

        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            //返回查询的结果
            rs = ps.executeQuery();
            if (rs.next()) {
                while (rs.next()) {
                    UserBean userBean = new UserBean();
                    userBean.setUid(rs.getInt("uid"));
                    userBean.setPassWord(rs.getString("passWord"));
                    userBean.setUserName(rs.getString("userName"));
                    beans.add(userBean);
                }
            }else {
                beans = null ;
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
