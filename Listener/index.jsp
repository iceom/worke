<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线人数统计</title>
</head>
<body>
    <h2>当前网站实时在线人数为：
        <!-- 使用EL表达式直接从application域中获取人数 -->
        <span style="color: red; font-size: 24px;">${applicationScope.onlineCount}</span> 人
    </h2>
    <p>（提示：你可以打开多个不同浏览器或隐身窗口访问此页面，观察人数变化）</p >
</body>
</html>