<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
<title>Catalogue</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>

<div>
<f:form modelAttribute="produit" method="post" action="saveFiles">
<table>
<tr>
<td>Titre:</td>
<td><f:input path="reference" name="reference"/></td>
<td><f:errors path="reference" cssClass="errors"/></td>
</tr>
<tr>
<td>Login: </td>
<td><input type="text" name="login" />
</td>
</tr>
<tr>
<td>Password:</td> 
<td><input type="password" name="pwd" />
</td>
</tr>
<tr>
<td>
<input type="submit" value=saveFiles>
</td>
</tr>
</table>
</f:form>
<div>

        
        <form method="POST" action="creer">
        	Name: <input type="text" name="name" />
            CRUD : <input type="checkbox" name="CRUD" />
            Champ: <input type="text" name="champ" />
          
            <input type="submit" value="creer" />
        </form>
        
       
</div>
</div>

</body>
</html>