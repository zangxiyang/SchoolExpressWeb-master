package DAO;

import Beans.TaskBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements IOrderDao {
    private static final String TAG = "OrderDAOImpl";
    final String TABLE_USERS = "end_users";
    final String TABLE_COURIERS = "end_couriers";

    /**
     *
     * @param key uid \ cid
     * @param flag 0:用户 1:配送员
     * @return Arraylist for data
     */
    @Override
    public List<TaskBean> selectOrders(int key, int flag) {
        List<TaskBean> beans = new ArrayList<TaskBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = null ;

        //用户
        if (flag == 0){
            sql = "SELECT * FROM end_taskList WHERE uid=? ORDER BY taskId DESC ";
        }
        //配送员
        else if (flag == 1){
            sql = "SELECT * FROM end_taskList WHERE cid=? ORDER BY taskId DESC ";
        }

        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            rs = ps.executeQuery();
            //获取返回的结果
            while (rs.next()){
                    TaskBean taskBean = new TaskBean();
                    taskBean.setTaskId(rs.getInt("taskId"));
                    taskBean.setUid(rs.getInt("uid"));
                    taskBean.setCid(rs.getInt("cid"));
                    taskBean.setName(rs.getString("name"));
                    taskBean.setPhone(rs.getString("phone"));
                    taskBean.setPickupAddress(rs.getString("pickupAddress"));
                    taskBean.setPickUpCode(rs.getString("pickupCode"));
                    taskBean.setToAddress(rs.getString("toAddress"));
                    taskBean.setTaskStatus(rs.getInt("taskStatus"));
                    beans.add(taskBean);
                }
            ps.clearParameters();
            ps.close();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
        return beans;
    }
    //订单状态 : -> 0 未被领取 , 1:正在进行 , 2:已完成
    @Override
    public List<TaskBean> selectNotReceivedOrders() {
        List<TaskBean> beans = new ArrayList<TaskBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = "SELECT * FROM end_taskList WHERE taskStatus=? ORDER BY taskId DESC " ;

        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,0);
            rs = ps.executeQuery();
            //获取返回的结果
            while (rs.next()){
                TaskBean taskBean = new TaskBean();
                taskBean.setTaskId(rs.getInt("taskId"));
                taskBean.setTaskId(rs.getInt("taskId"));
                taskBean.setUid(rs.getInt("uid"));
                taskBean.setCid(rs.getInt("cid"));
                taskBean.setName(rs.getString("name"));
                taskBean.setPhone(rs.getString("phone"));
                taskBean.setPickupAddress(rs.getString("pickupAddress"));
                taskBean.setPickUpCode(rs.getString("pickupCode"));
                taskBean.setToAddress(rs.getString("toAddress"));
                taskBean.setTaskStatus(rs.getInt("taskStatus"));
                beans.add(taskBean);
            }
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
        return beans;
    }

    @Override
    public List<TaskBean> selectFinishedOrders(int key, int flag) {
        List<TaskBean> beans = new ArrayList<TaskBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = null;
        if (flag == 0) {
            sql = "SELECT * FROM end_taskList WHERE taskStatus=2 AND uid=? ORDER BY taskId DESC ";
        }else if (flag == 1){
            sql = "SELECT * FROM end_taskList WHERE taskStatus=2 AND cid=? ORDER BY taskId DESC ";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            rs = ps.executeQuery();
            //获取返回的结果
            while (rs.next()){
                TaskBean taskBean = new TaskBean();
                taskBean.setTaskId(rs.getInt("taskId"));
                taskBean.setCid(rs.getInt("cid"));
                taskBean.setUid(rs.getInt("uid"));
                taskBean.setName(rs.getString("name"));
                taskBean.setPhone(rs.getString("phone"));
                taskBean.setPickupAddress(rs.getString("pickupAddress"));
                taskBean.setPickUpCode(rs.getString("pickupCode"));
                taskBean.setTaskStatus(rs.getInt("taskStatus"));
                taskBean.setToAddress(rs.getString("toAddress"));
                beans.add(taskBean);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
        return beans;
    }




    @Override
    public List<TaskBean> selectLoadingOrders(int key, int flag) {
        List<TaskBean> beans = new ArrayList<TaskBean>();
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        String sql = null;
        if (flag == 0) {
            sql = "SELECT * FROM end_taskList WHERE taskStatus=1 OR taskStatus=0 AND uid=? ORDER BY taskId DESC ";
        }else if (flag == 1){
            sql = "SELECT * FROM end_taskList WHERE taskStatus=1  AND cid=? ORDER BY taskId DESC ";
        }
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,key);
            rs = ps.executeQuery();
            //获取返回的结果
            while (rs.next()){
                TaskBean taskBean = new TaskBean();
                taskBean.setTaskId(rs.getInt("taskId"));
                taskBean.setUid(rs.getInt("uid"));
                taskBean.setCid(rs.getInt("cid"));
                taskBean.setName(rs.getString("name"));
                taskBean.setPhone(rs.getString("phone"));
                taskBean.setToAddress(rs.getString("toAddress"));
                taskBean.setPickupAddress(rs.getString("pickupAddress"));
                taskBean.setPickUpCode(rs.getString("pickupCode"));
                taskBean.setTaskStatus(rs.getInt("taskStatus"));
                beans.add(taskBean);
            }
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(TAG + "数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
        return beans;
    }
    //订单状态 : -> 0 未被领取 , 1:正在进行 , 2:已完成

    /**
     * 根据taskId来修改任务的状态
     * @param key
     * @param flag  代表任务状态设置为 0为未领取 1为正在进行中 2为已完成
     */
    @Override
    public void updateOrder(int key,int cid , int flag ,int role) throws SQLException{
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql = null;
        if (role == 1) {
            switch (flag) {
                case 0:
                    sql = "UPDATE end_taskList SET taskStatus=0,cid=? WHERE taskId=?";
                    break;
                case 1:
                    sql = "UPDATE end_taskList SET taskStatus=1,cid=? WHERE taskId=?";
                    break;
                case 2:
                    sql = "UPDATE end_taskList SET taskStatus=2,cid=? WHERE taskId=?";
                    break;
            }
        }else if (role == 0){
            switch (flag) {
                case 0:
                    sql = "UPDATE end_taskList SET taskStatus=0,uid=? WHERE taskId=?";
                    break;
                case 1:
                    sql = "UPDATE end_taskList SET taskStatus=1,uid=? WHERE taskId=?";
                    break;
                case 2:
                    sql = "UPDATE end_taskList SET taskStatus=2,uid=? WHERE taskId=?";
                    break;
            }
        }

        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,cid);
            ps.setInt(2,key);
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
    }

    @Override
    public void insertOrder(TaskBean bean) throws SQLException{
        ConnectionDAO connectionDAO = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO end_taskList(uid,name,phone,pickupCode,pickupAddress,toAddress,taskStatus) VALUES (?,?,?,?,?,?,?)";
        try {
            connectionDAO = ConnectionDAO.getInstance();
            ps = connectionDAO.getConnection().prepareStatement(sql);
            ps.setInt(1,bean.getUid());
            ps.setString(2,bean.getName());
            ps.setString(3,bean.getPhone());
            ps.setString(4,bean.getPickUpCode());
            ps.setString(5,bean.getPickupAddress());
            ps.setString(6,bean.getToAddress());
            ps.setInt(7,bean.getTaskStatus());
            ps.executeUpdate();
            ps.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(TAG + "驱动注册失败");
        }
    }

}
