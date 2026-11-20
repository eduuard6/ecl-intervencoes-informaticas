<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,pt.ecl.Html" %>
<%List<String[]> cursos=(List<String[]>)request.getAttribute("cursos");String base=request.getContextPath();%>
<!DOCTYPE html><html lang="pt"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Pré-inscrição | ECL</title><link rel="stylesheet" href="<%=base%>/estilo.css"></head><body>
<nav><a href="<%=base%>/">Início</a> · <a href="<%=base%>/cursos">Oferta formativa</a></nav><header class="hero"><p class="eyebrow">ESCOLA DO COMÉRCIO DE LISBOA</p><h1>Pré-inscrição</h1><p>Preencha os seus dados para manifestar interesse num curso.</p></header>
<%if(request.getAttribute("erro")!=null){%><p class="erro" role="alert"><%=Html.escapar(request.getAttribute("erro"))%></p><%}%>
<form class="formulario" method="post" action="<%=base%>/pre-inscricao"><label for="nome">Nome completo</label><input id="nome" name="nome" required minlength="3" maxlength="120" value="<%=Html.escapar(request.getAttribute("nome"))%>">
<label for="email">Email</label><input id="email" name="email" type="email" required maxlength="150" value="<%=Html.escapar(request.getAttribute("email"))%>">
<label for="telefone">Telefone (opcional)</label><input id="telefone" name="telefone" type="tel" maxlength="25" value="<%=Html.escapar(request.getAttribute("telefone"))%>">
<label for="curso">Curso pretendido</label><select id="curso" name="curso" required><option value="">Selecione um curso</option><%if(cursos!=null)for(String[] c:cursos){%><option value="<%=Html.escapar(c[0])%>"><%=Html.escapar(c[1])%></option><%}%></select><button type="submit">Enviar pré-inscrição</button></form></body></html>