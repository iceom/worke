```java
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证过滤器
 * 检查用户是否已登录，未登录则重定向到登录页面
 */
@WebFilter("/admin/*")
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // 检查用户是否已登录
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        // 获取请求的URI
        String requestURI = httpRequest.getRequestURI();
        
        // 如果用户未登录且请求的不是登录页面，则重定向到登录页面
        if (!isLoggedIn && !requestURI.endsWith("login")) {
            httpResponse.sendRedirect("login");
            return;
        }
        
        // 用户已登录或请求的是登录页面，继续执行
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // 清理资源
    }
}
```

## 5. LoginServlet.java - 登录Ser

