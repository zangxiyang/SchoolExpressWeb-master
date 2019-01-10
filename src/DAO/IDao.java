package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao层接口
 */
public interface IDao {
    //flag -> 0: users 1:courier
    void insert(Object obj,int flag) throws SQLException;
    void delete(int key,int flag);
    void update(Object obj,int flag);
    Object select(String key,int flag);
    List selectAll(int flag);
}
