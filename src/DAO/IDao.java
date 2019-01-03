package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao层接口
 */
public interface IDao {
    void insert(Object obj) throws SQLException;
    void delete(int key);
    void update(Object obj);
    Object select(int key);
    List selectAll();
}
