package Handle;

import Beans.UserDetailBean;
import DAO.IDetailDao;
import DAO.UserDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/setInfo")
public class SetInfoHandle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 0 -> user 1 -> courier
        int flag = Integer.valueOf(req.getParameter("flag"));
        if (flag == 0){
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String phone = req.getParameter("phone");
            String toAddress = req.getParameter("toAddress");
            HttpSession session = req.getSession();
            int uid = (int) session.getAttribute("uid");
            UserDetailBean detailBean = new UserDetailBean();
            detailBean.setUid(uid);
            detailBean.setName(name);
            detailBean.setSex(sex);
            detailBean.setTelPhone(phone);
            detailBean.setAddress(toAddress);
            IDetailDao dao = UserDaoFactory.getDetailInstance();
            dao.updateDetail(detailBean,uid,0);
            session.setAttribute("setInfoReturn",0);
            resp.sendRedirect("home.jsp?pageCode=setInfo");
        }else if (flag == 1){
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String phone = req.getParameter("phone");
            HttpSession session = req.getSession();
            int uid = (int) session.getAttribute("uid");
            UserDetailBean detailBean = new UserDetailBean();
            detailBean.setUid(uid);
            detailBean.setName(name);
            detailBean.setSex(sex);
            detailBean.setTelPhone(phone);
            IDetailDao dao = UserDaoFactory.getDetailInstance();
            dao.updateDetail(detailBean,uid,1);
            session.setAttribute("setInfoReturn",0);
            resp.sendRedirect("home.jsp?pageCode=setInfo_Courier");
        }


    }
}
