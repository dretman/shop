<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div>
    <t:insertAttribute name="header"/>
</div>

<div>
    <t:insertAttribute name="navigator"/>
</div>

<div>
    <t:insertAttribute name="gallery"/>
</div>

<div>
    <t:insertAttribute name="body"/>
</div>

<div>
    <t:insertAttribute name="footer"/>
</div>