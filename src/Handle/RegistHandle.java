package Handle;


import Beans.UserBean;
import DAO.IDao;
import DAO.IDetailDao;
import DAO.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2019.01.03
 * Author:Seale
 */
@WebServlet("/RegistHandle")
public class RegistHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String role ;
        String userName ,passWord ;
        userName = request.getParameter("userName");
        System.out.println(userName);
        passWord = request.getParameter("passWord");
        role = request.getParameter("role");
        UserBean user = new UserBean() ;
        user.setUserName(userName);
        user.setPassWord(passWord);
        IDao dao = UserDaoFactory.getUserDaoInstance();
        try {
            if (role.equals("用户")) {
                dao.insert(user, 0);
                //处理用户的信息数据库 :->构建用户的信息数据表初始化 绑定 uid和username
                UserBean bean = (UserBean)dao.select(userName,0);
                IDetailDao detailDao = UserDaoFactory.getDetailInstance();
                detailDao.insertDetail(bean,0);
            }
            if (role.equals("配送员")){
                dao.insert(user,1);
                //处理配送员的信心数据库: -> 构建配送员的信息数据表 绑定uid和userName
                UserBean bean = (UserBean)dao.select(userName,1);
                IDetailDao detailDao = UserDaoFactory.getDetailInstance();
                detailDao.insertDetail(bean,1);
            }
            HttpSession session = request.getSession();
            session.setAttribute("returnCode",1);
            System.out.println("Handle:"+session.getAttribute("returnCode"));
            response.sendRedirect("regist.jsp");
        } catch (SQLException e) {
            HttpSession session = request.getSession();
            session.setAttribute("returnCode",0);
            System.out.println("Handle:"+session.getAttribute("returnCode"));
            response.sendRedirect("regist.jsp");
            System.out.println(e.toString());
        }

    }
}
