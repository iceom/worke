import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理用户登录的Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>用户登录</h2>");
        out.println("<form method='post'>");
        out.println("用户名: <input type='text' name='username'><br>");
        out.println("密码: <input type='password' name='password'><br>");
        out.println("<input type='submit' value='登录'>");
        out.println("</form>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 简单的验证逻辑（实际项目中应该查询数据库）
        if ("admin".equals(username) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("admin/dashboard");
        } else {
            response.sendRedirect("login");
        }
    }
}