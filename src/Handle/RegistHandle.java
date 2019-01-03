package Handle;


import Beans.UserBean;
import DAO.IDao;
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

        String userName ,passWord ;
        userName = request.getParameter("userName");
        System.out.println(userName);
        passWord = request.getParameter("passWord");
        UserBean user = new UserBean() ;
        user.setUserName(userName);
        user.setPassWord(passWord);

        IDao dao = UserDaoFactory.getUserDaoInstance();
        try {
            dao.insert(user);
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
