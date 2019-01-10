package Handle;

import DAO.IOrderDao;
import DAO.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/finishOrder")
public class FinishOrderHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        int uid = (int)session.getAttribute("uid");
        int taskId = Integer.valueOf(req.getParameter("taskId"));
        int roleCode = (int)session.getAttribute("roleCode");
        IOrderDao dao = UserDaoFactory.getOrderInstance();
        try {
            if (roleCode == 1){
                dao.updateOrder(taskId,uid,2,0);
            }else{
                dao.updateOrder(taskId,uid,2,1);
            }

            session.setAttribute("finishOrderReturn",0);
            if (roleCode == 1) {
                resp.sendRedirect("home.jsp?pageCode=myOrder");
            }else if (roleCode == 2){
                resp.sendRedirect("home.jsp?pageCode=myOrder_Courier");
            }
        } catch (SQLException e) {
            session.setAttribute("finishOrderReturn",1);
            if (roleCode == 1) {
                resp.sendRedirect("home.jsp?pageCode=myOrder");
            }else if (roleCode == 2){
                resp.sendRedirect("home.jsp?pageCode=myOrder_Courier");
            }
        }


    }
}
