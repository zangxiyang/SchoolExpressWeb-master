package DAO;

import Beans.UserBean;
import Beans.UserDetailBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailDAOImpl implements IDetailDao{
    private static final String TAG = "UserDetailDAOImpl";

    @Override
    public void insertDetail(UserBean bean, int flag) {
        ConnectionDAO connectionDAO = null ;
        PreparedStatement ps = null ;
        String sql = null;
        //用戶
        if (flag == 0 ){
            sql = "INSERT INTO end_user_details(uid,userName) VALUES (?,?)";
        }else if (flag == 1){
            sql = "INSERT INTO end_courier_details(uid,userName) VALUES (?,?)";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,bean.getUid());
            ps.setString(2,bean.getUserName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }

    }

    @Override
    public UserDetailBean selectDetail(int key, int flag) {
        UserDetailBean userDetailBean = new UserDetailBean();
        ConnectionDAO connectionDAO = null ;
        PreparedStatement ps = null ;
        ResultSet rs = null;
        String sql = null;
        //用戶
        if (flag == 0 ){
            sql = "SELECT * FROM end_user_details WHERE uid=?";
        }else if (flag == 1){
            sql = "SELECT * FROM end_courier_details WHERE uid=?";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            rs = ps.executeQuery();
            if (flag == 0) {
                if (rs.next()) {
                    userDetailBean.setUid(rs.getInt("uid"));
                    userDetailBean.setUserName(rs.getString("userName"));
                    userDetailBean.setName(rs.getString("name"));
                    userDetailBean.setSex(rs.getString("sex"));
                    userDetailBean.setTelPhone(rs.getString("telPhone"));
                    userDetailBean.setAddress(rs.getString("address"));
                }
            }else{
                if (rs.next()) {
                    userDetailBean.setUid(rs.getInt("uid"));
                    userDetailBean.setUserName(rs.getString("userName"));
                    userDetailBean.setName(rs.getString("name"));
                    userDetailBean.setSex(rs.getString("sex"));
                    userDetailBean.setTelPhone(rs.getString("telPhone"));
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
        return userDetailBean;

    }

    @Override
    public void updateDetail(UserDetailBean bean,int key, int flag) {
        ConnectionDAO connectionDAO = null ;
        PreparedStatement ps = null ;
        String sql = null;
        if (flag == 0){
            sql = "UPDATE end_user_details SET name=?,telPhone=?,sex=?,address=? WHERE uid=?";
        }else if (flag == 1){
            sql = "UPDATE end_courier_details SET name=?,telPhone=?,sex=? WHERE uid=?";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            if (flag == 0) {
                ps.setString(1, bean.getName());
                ps.setString(2, bean.getTelPhone());
                ps.setString(3, bean.getSex());
                ps.setString(4, bean.getAddress());
                ps.setInt(5, key);
            }else if (flag == 1){
                ps.setString(1, bean.getName());
                ps.setString(2, bean.getTelPhone());
                ps.setString(3, bean.getSex());
                ps.setInt(4, key);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
    }
}
