import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class AppInitListener implements ServletContextListener {

    // 应用启动时执行（Tomcat启动并加载项目时）
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web应用正在启动，开始初始化全局数据...");
        
        ServletContext application = sce.getServletContext();
        
        // 模拟从数据库加载了一些全局的配置数据或字典数据
        Map<String, String> siteConfig = new HashMap<>();
        siteConfig.put("siteName", "JavaWeb学习平台");
        siteConfig.put("version", "v2.0.1");
        siteConfig.put("author", "千问");
        
        // 将全局配置存入 application 域，整个网站都可以共享使用
        application.setAttribute("siteConfig", siteConfig);
        
        System.out.println("全局数据初始化完成！");
    }

    // 应用关闭时执行（Tomcat正常关闭时）
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Web应用正在关闭，正在释放全局资源...");
        // 这里可以执行关闭数据库连接池、保存缓存到硬盘等收尾工作
    }
}