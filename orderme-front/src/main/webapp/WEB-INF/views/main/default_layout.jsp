<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles"    uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" 	 uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href='<spring:url value="/static/css/app.css"/>'/>
    <link rel="stylesheet" href='<spring:url value="/static/css/angular-growl.min.css"/>'/>
</head>

<body>

    <tiles:insertAttribute name="header"/>

    <section class="main" style="min-height: 600px;">
        <div class="container">
            <tiles:insertAttribute name="content"/>
        </div>
    </section>

    <tiles:insertAttribute name="footer"/>

    <script src='<spring:url value="/static/js/vendor.js"/>?version=${properties['application.version']}'></script>
    <script src='<spring:url value="/static/js/angular-growl.min.js"/>?version=${properties['application.version']}'></script>
    <script src='<spring:url value="/static/js/app.js"/>?version=${properties['application.version']}'></script>

</body>
</html>

