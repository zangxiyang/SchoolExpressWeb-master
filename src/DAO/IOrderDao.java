package DAO;


import Beans.TaskBean;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDao {
    //0为用户,1为配送员

    List<TaskBean> selectOrders(int key,int flag);
    List<TaskBean> selectNotReceivedOrders();
    List<TaskBean> selectFinishedOrders(int key,int flag);
    List<TaskBean> selectLoadingOrders(int key,int flag);
    void updateOrder(int key,int cid , int flag ,int role) throws SQLException;
    void insertOrder(TaskBean bean) throws SQLException;
}
