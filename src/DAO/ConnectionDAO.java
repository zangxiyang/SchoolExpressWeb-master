package DAO;

import Utils.ConfigManner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 基础Dao层 : -> Connection
 */
public class ConnectionDAO {

    private static ConnectionDAO connectionDAO = null;
    private Connection con = null ;

    /**
     * 构造DAO层时直接创建,如果驱动初始化不成功或者是数据库连接不成功则抛出
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private ConnectionDAO() throws ClassNotFoundException, SQLException {
        //获得Driver
        String driver = ConfigManner.getInstance().getValue("driver");
        //获得Url
        String url = ConfigManner.getInstance().getValue("url");
        //获得用户名
        String userName = ConfigManner.getInstance().getValue("userName");
        //获得密码
        String passWord = ConfigManner.getInstance().getValue("pwd");
        //进行数据库驱动的注册
        Class.forName(driver);
        if (con == null){
            con = DriverManager.getConnection(url,userName,passWord);
        }
    }

    /**
     * 返回连接对象
     * @return
     */
    public Connection getConnection(){
        return con;
    }

    /**
     * 单例模式:返回对象
     * @return
     */
    public static ConnectionDAO getInstance() throws SQLException, ClassNotFoundException {
        connectionDAO = new ConnectionDAO();
        return connectionDAO;

    }

    /**
     * 关闭数据库执行
     * 如果数据库关闭异常 则抛出
     * @throws  SQLException
     */
    public void close() throws SQLException {
            con.close();

    }

}
