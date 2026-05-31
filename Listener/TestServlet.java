import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        // 获取在监听器中初始化的全局数据
        ServletContext application = this.getServletContext();
        Map<String, String> config = (Map<String, String>) application.getAttribute("siteConfig");
        
        out.println("<h3>从监听器初始化的全局数据中读取到的信息：</h3>");
        out.println("网站名称：" + config.get("siteName") + "<br>");
        out.println("当前版本：" + config.get("version") + "<br>");
        out.println("作者：" + config.get("author") + "<br>");
    }
}