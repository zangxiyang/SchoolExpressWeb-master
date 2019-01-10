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


//接收订单逻辑

@WebServlet("/getOrder")
public class GetOrderHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("接收订单逻辑被初始化");
        int taskId = Integer.valueOf(request.getParameter("taskId"));
        //取得一个任务DAO实例
        IOrderDao orderDao = UserDaoFactory.getOrderInstance() ;
        //取得当前会话的session对象
        HttpSession session = request.getSession();
        int uid = (int)session.getAttribute("uid");
        try {
            orderDao.updateOrder(taskId,uid,1,1);
            session.setAttribute("getOrderReturn",0);
            response.sendRedirect("home.jsp?pageCode=getOrder");
        } catch (SQLException e) {
            //正确返回0 错误返回 1
            session.setAttribute("getOrderReturn" ,1);
            response.sendRedirect("home.jsp?pageCode=getOrder");
        }

    }


}
