<%@ page import="java.time.LocalDate" %>
<%@ page import="com.example.gestionpanneausolaire.objet.Coupures" %>
<%@ page import="com.example.gestionpanneausolaire.objet.Donneepanneau" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: Jeddy
  Date: 15/12/2023
  Time: 08:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LocalDate dateprevision= (LocalDate) request.getAttribute("date");
    LocalTime coupures= (LocalTime) request.getAttribute("coupures");
    Donneepanneau donneepanneau= (Donneepanneau) request.getAttribute("donneepanneau");

%>
<html>
<head>
    <title>prevision</title>
</head>
<body>
<h1>Prevision date:<% out.print(dateprevision);%> </h1>
<p>panneau: <% out.print(donneepanneau.getPanneau().getNomPanneau()); %></p>
<p>moyenne conso= <%out.print(donneepanneau.getConsommationeleve()); %></p>
<p>heure de coupure: <% out.print(coupures); %></p>

</body>
</html>
