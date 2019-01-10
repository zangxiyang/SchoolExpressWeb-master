package DAO;

import Beans.UserBean;
import Beans.UserDetailBean;

public interface IDetailDao {
    /**
     *
     * @param bean
     * @param flag flag 0用户 1配送员
     */
    void insertDetail(UserBean bean, int flag) ;
    UserDetailBean selectDetail(int key , int flag);
    void updateDetail(UserDetailBean bean,int key , int flag);
}
