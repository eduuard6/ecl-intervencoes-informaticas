<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pt.ecl.Html" %>
<!DOCTYPE html>
<html lang="pt">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Pedido validado</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilo.css">
</head>
<body>
  <h1>Confirmação do pedido</h1>
  <p><strong>Sala:</strong> <%= Html.escapar(request.getAttribute("sala")) %></p>
  <p><strong>Tipo de avaria:</strong> <%= Html.escapar(request.getAttribute("tipo")) %></p>
  <p><strong>Descrição:</strong></p>
  <p class="descricao"><%= Html.escapar(request.getAttribute("descricao")) %></p>
  <p class="sucesso">Pedido validado com sucesso</p>
  <p><a href="<%= request.getContextPath() %>/">Voltar à página inicial</a></p>
</body>
</html>
