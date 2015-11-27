<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles"    uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" 	 uri="http://www.springframework.org/security/tags" %>

<header class="navbar navbar-default">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span>OrderMe</span>
                <!--
                <img alt="Pillar" id="logo" src="<spring:url value="/static/assets/img/pillar_logo.svg"/>">
                -->
            </a>
        </div>

        <nav role="navigation">

            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="/orders">
                        <span class="icon2 icon2-home"></span>
                        <span class="name">Home</span>
                    </a>
                </li>
            </ul>
        </nav>
        <!--
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form:form method="post" action="/logout" style="padding: 24px 10px;">
                    <button class="btn btn-primary m-right-sm">Logout</button>
                </form:form>
            </li>
        </ul>
        -->


    </div>
</header>