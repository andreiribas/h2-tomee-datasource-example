<%--
  The MIT License (MIT)
Copyright (c) 2015 Andrei Gonçalves Ribas <andrei.g.ribas@gmail.com>
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
--%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>H2 DB Schemas</title>
</head>
<body>
	<h1>
		<center>H2 DB Schemas:</center>
	</h1>

	<h3>
		<center>DB Schemas</center>
	</h3>
	<table border="1" width="100%">
		<tr>
			<th>Schema name:</th>
			<th>Is Default:</th>
		</tr>
		<c:forEach var="dbSchema"
			items="${dbSchemasRetrievedWithDefaultInitialContext}">
			<tr>
				<td><c:out value="${dbSchema.name}" /></td>
				<td><c:out value="${dbSchema.defaultSchema}" /></td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<br />

	<br />
	<br />

	<div id="exceptionDiv">
		<div id="divExceptionTitle">Exception?</div>
		<div id="divExceptionMessage">
			<c:choose>
				<c:when test="${empty exceptionStackTrace}">
        			No
    			</c:when>
				<c:otherwise>
        			<c:out value="${exceptionStackTrace}" />		
    			</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>