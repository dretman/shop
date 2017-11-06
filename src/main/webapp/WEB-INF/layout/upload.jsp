<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Page Content -->

<div class="container">
    <sf:form method="post" commandName="application" enctype="multipart/form-data">
        <div class="form-group">
            <div>
                <sf:label path="applicationName" cssErrorClass="error">Application Name</sf:label>
                <sf:input path="applicationName" cssClass="form-control"/>
            </div>

            <div>
                <small class="form-text text-muted">
                    <sf:errors path="applicationName" cssClass="error"/>
                </small>
            </div>

        </div>
        <div class="form-group">
            <div>
                <sf:label path="description" cssErrorClass="error">Description</sf:label>
                <sf:input path="description" cssClass="form-control"/>
            </div>
            <small class="form-text text-muted">Describe your application</small>
            <div>
                <small class="form-text text-muted">
                    <sf:errors path="description" cssClass="error"/>
                </small>
            </div>
        </div>
        <div class="form-group">
                <%--<div class="col-sm-2">--%>
            <label>Category</label>
                <%--</div>--%>
                <%--<div class="col-lg-4">--%>
            <sf:select path="category" cssClass="form-control">
                <c:forEach var="category" items="${categoryList}">
                    <sf:option value="${category.id}">${category.name}</sf:option>
                </c:forEach>
            </sf:select>
                <%--</div>--%>
        </div>
        <div class="form-group">
            <label>Archive</label>
            <input type="file" class="form-control-file" aria-describedby="fileHelp" name="archive"
                   accept="application/zip">
            <small class="form-text text-muted">Upload your program archive
            </small>
            <div class="archiveValidationError">
                <c:out value="${archiveValidationErrorMessage}"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Upload</button>

    </sf:form>

</div>

<script>
    $(function () {
       $("#gallery").hide();
    });
</script>