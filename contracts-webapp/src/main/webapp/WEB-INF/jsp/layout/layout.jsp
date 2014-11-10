<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><decorator:title default="Contracts"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="${pageContext.servletContext.contextPath}/static/images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="114x114" href="${pageContext.servletContext.contextPath}/static/images/apple-touch-icon-retina.png">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/stylesheets/main.css" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/stylesheets/application.css" />

    <script src="${pageContext.servletContext.contextPath}/static/javascripts/jquery-1.11.1.js"></script>
    <script src="${pageContext.servletContext.contextPath}/static/javascripts/application.js"></script>
    <decorator:head />
</head>
<body>
<div id="grailsLogo" role="banner">
    <a style="margin: 0" href="http://grails.org">
        <img height="100" src="https://tecnoesis.files.wordpress.com/2009/11/spring-logo1.jpg" alt="Grails"/>
    </a>
</div>
<decorator:body />
<div class="footer" role="contentinfo"></div>
</body>
</html>
