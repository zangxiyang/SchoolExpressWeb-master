package DAO;

/**
 * Created with IntelliJ IDEA.
 * Description: 工厂类
 * Date:2019.01.03
 * Author:Seale
 */
public class UserDaoFactory {

    public static IDao getUserDaoInstance(){
        return new UserDAOImpl();
    }
    public static IDetailDao getDetailInstance(){return new UserDetailDAOImpl();}
    public static IOrderDao getOrderInstance(){return new OrderDAOImpl();}
}
