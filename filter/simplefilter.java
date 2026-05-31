import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 简单的过滤器示例
 * 用于演示Filter的基本用法
 */
@WebFilter("/hello")
public class SimpleFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SimpleFilter 初始化");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        System.out.println("SimpleFilter 执行前处理");
        
        // 继续执行后续的过滤器或目标资源
        chain.doFilter(request, response);
        
        System.out.println("SimpleFilter 执行后处理");
    }
    
    @Override
    public void destroy() {
        System.out.println("SimpleFilter 销毁");
    }
}