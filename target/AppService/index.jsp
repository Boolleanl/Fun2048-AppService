<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入struts2 的标签库--%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>ssh测试</title>
</head>
<body>

<%-- 获取值栈中的user对象的uname的值--%>
用户名： <s:property value="userEntity.name"></s:property>
</body>
</html>