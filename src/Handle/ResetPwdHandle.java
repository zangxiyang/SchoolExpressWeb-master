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

@WebServlet("/resetPwd")
public class ResetPwdHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        IDao dao = UserDaoFactory.getUserDaoInstance();

        String nowPwd , newPwd,reNewPwd;
        String userName  ;
        int flag ;
        HttpSession session = request.getSession();
        int uid = (int)session.getAttribute("uid");
        if (((int)session.getAttribute("roleCode")) == 1){
            flag = 0;
        }else{
            flag = 1 ;
        }

        userName = (String) session.getAttribute("userName");
        nowPwd = request.getParameter("nowPwd");
        newPwd = request.getParameter("newPwd");
        reNewPwd = request.getParameter("reNewPwd");
        if (!newPwd.equals(reNewPwd) || newPwd.length() < 6){
            //失败
            session.setAttribute("resetPwdReturn",1);
            response.sendRedirect("home.jsp?pageCode=resetPwd");
            return;
        }
        UserBean userBean = (UserBean) dao.select(userName,flag);
        String pwdForSQL = userBean.getPassWord();
        if (!nowPwd.equals(pwdForSQL)){
            //失败
            session.setAttribute("resetPwdReturn",1);
            response.sendRedirect("home.jsp?pageCode=resetPwd");
            return ;

        }else{
            //成功
            UserBean userBean1 = new UserBean();
            userBean1.setUid(uid);
            userBean1.setUserName(userBean.getUserName());
            userBean1.setPassWord(newPwd);
            dao.update(userBean1,flag);
            session.setAttribute("resetPwdReturn",0);
            response.sendRedirect("home.jsp?pageCode=resetPwd");
            return;

        }



    }
}
