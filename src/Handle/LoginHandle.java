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


@WebServlet("/LoginHandle")
public class LoginHandle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String role;
        String userName, passWord;
        userName = request.getParameter("userName");
        passWord = request.getParameter("passWord");
        role = request.getParameter("role");
        UserBean user = new UserBean();
        user.setUserName(userName);
        user.setPassWord(passWord);
        IDao dao = UserDaoFactory.getUserDaoInstance();
        if (role.equals("用户")) {
            UserBean userBean = (UserBean) dao.select(userName, 0);
            if (userBean != null) {
                if (userBean.getPassWord().equals(user.getPassWord())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("isLogin", true);
                    session.setAttribute("roleCode", 1);
                    session.setAttribute("uid",userBean.getUid());
                    session.setAttribute("userName", userName);
                    response.sendRedirect("home.jsp");
                } else {
                    HttpSession session = request.getSession();
                    //登录失败
                    session.setAttribute("loginReturn", false);
                    response.sendRedirect("login.jsp");
                }
            }else {
                HttpSession session = request.getSession();
                //登录失败
                session.setAttribute("loginReturn", false);
                response.sendRedirect("login.jsp");
            }
        } else if (role.equals("配送员")) {
                UserBean userBean = (UserBean)dao.select(userName, 1);
                //TODO -> roleCode 2
            if (userBean != null) {
                if (userBean.getUserName().equals(userName) && userBean.getPassWord().equals(passWord)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("isLogin", true);
                    session.setAttribute("roleCode", 2);
                    session.setAttribute("uid",userBean.getUid());
                    session.setAttribute("userName", userName);
                    response.sendRedirect("home.jsp");
                } else {
                    HttpSession session = request.getSession();
                    //登录失败
                    session.setAttribute("loginReturn", false);
                    response.sendRedirect("login.jsp");
                }
            }

            } else if (role.equals("管理员")){
                //管理员
                UserBean userBean = (UserBean)dao.select(userName, 2);
                //TODO -> roleCode 0
            if (userBean != null) {
                if (userBean.getUserName().equals(userName) && userBean.getPassWord().equals(passWord)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("isLogin", true);
                    session.setAttribute("roleCode", 0);
                    session.setAttribute("uid",userBean.getUid());
                    session.setAttribute("userName", userName);
                    response.sendRedirect("home.jsp");
                } else {
                    HttpSession session = request.getSession();
                    //登录失败
                    session.setAttribute("loginReturn", false);
                    response.sendRedirect("login.jsp");
                }
            }
            }

        }




    }

