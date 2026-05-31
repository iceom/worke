import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUserListener implements HttpSessionListener {

    // 当浏览器第一次访问服务器，Session被创建时触发
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 1. 获取全局应用域对象 ServletContext
        ServletContext application = se.getSession().getServletContext();
        
        // 2. 获取当前在线人数（第一次获取可能为null，默认为0）
        Integer count = (Integer) application.getAttribute("onlineCount");
        if (count == null) {
            count = 0;
        }
        
        // 3. 人数加1，并存回全局域
        application.setAttribute("onlineCount", count + 1);
        System.out.println("有新用户上线，当前在线人数：" + (count + 1));
    }

    // 当Session销毁（超时或手动注销）时触发
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        
        Integer count = (Integer) application.getAttribute("onlineCount");
        if (count == null || count <= 0) {
            count = 0;
        } else {
            count = count - 1;
        }
        
        application.setAttribute("onlineCount", count);
        System.out.println("有用户下线，当前在线人数：" + count);
    }
}