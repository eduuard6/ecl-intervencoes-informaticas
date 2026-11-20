package pt.ecl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PedidosServlet extends HttpServlet {
    private final Repositorio repositorio = new Repositorio();
    private final List<String> estados = List.of("Por analisar", "Em intervenção", "A aguardar", "Resolvido");

    private String campo(HttpServletRequest req, String nome) {
        String valor = req.getParameter(nome); return valor == null ? "" : valor.trim();
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try { req.setAttribute("pedidos", repositorio.listar()); }
        catch (SQLException e) {
            req.setAttribute("pedidos", List.of());
            req.setAttribute("erro", "Não foi possível consultar os pedidos. Verifique a base de dados.");
        }
        req.getRequestDispatcher("/WEB-INF/pedidos.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String acao = campo(req, "acao"), erro = "";
        try {
            if (acao.equals("criar")) {
                String nome=campo(req,"nome"), sala=campo(req,"sala"), equipamento=campo(req,"equipamento"), descricao=campo(req,"descricao");
                if (nome.isBlank() || sala.isBlank() || equipamento.isBlank() || descricao.length()<20)
                    erro="Preencha os campos. Descrição: mínimo 20 caracteres.";
                else repositorio.adicionar(nome,sala,equipamento,descricao);
            } else if (acao.equals("atualizar")) {
                String estado=campo(req,"estado"), observacao=campo(req,"observacao");
                if (!estados.contains(estado)) erro="Estado inválido.";
                else if (estado.equals("Resolvido") && observacao.length()<10) erro="Indique a solução com pelo menos 10 caracteres.";
                else {
                    try {
                        int id=Integer.parseInt(campo(req,"id"));
                        if (!repositorio.atualizar(id,estado,observacao)) erro="Pedido inexistente.";
                    } catch (NumberFormatException e) { erro="Número do pedido inválido."; }
                }
            } else erro="Operação inválida.";
        } catch (SQLException e) { erro="Não foi possível guardar a operação. Verifique a base de dados."; }

        if (!erro.isEmpty()) { req.setAttribute("erro",erro); doGet(req,resp); return; }
        req.getSession().setAttribute("mensagem","Operação realizada com sucesso.");
        resp.sendRedirect(req.getContextPath()+"/pedidos");
    }
}
