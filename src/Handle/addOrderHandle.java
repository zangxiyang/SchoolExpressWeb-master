package Handle;

import Beans.TaskBean;
import DAO.IDetailDao;
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

@WebServlet("/addOrderHandle")
public class addOrderHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取session对象
        HttpSession session = req.getSession() ;
        int uid = (int) session.getAttribute("uid");
        String name = (String) session.getAttribute("userName");
        TaskBean bean = new TaskBean() ;
        bean.setUid(uid);
        bean.setName(req.getParameter("name"));
        bean.setPhone(req.getParameter("phone"));
        bean.setPickUpCode(req.getParameter("pickupCode"));
        bean.setPickupAddress(req.getParameter("pickupAddress"));
        bean.setToAddress(req.getParameter("toAddress"));
        bean.setTaskStatus(0);//标识未被领取的订单
        IOrderDao dao = UserDaoFactory.getOrderInstance();
        try {
            dao.insertOrder(bean);
            System.out.println("下单成功");
            session.setAttribute("addOrderReturn",0);
            //跳转到发单前界面
            resp.sendRedirect("home.jsp?pageCode=addOrder&return=0");
        } catch (SQLException e) {
            //跳转到发单前界面
            System.out.println("下单失败");
            session.setAttribute("addOrderReturn",1);
            resp.sendRedirect("home.jsp?pageCode=addOrder&return=1");
        }


    }
}
