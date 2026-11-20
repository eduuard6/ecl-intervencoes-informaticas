package pt.ecl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class ValidacaoAvariaServlet extends HttpServlet {
    private static final Set<String> TIPOS_VALIDOS = Set.of("Equipamento", "Software", "Rede");

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp").forward(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sala = valor(request.getParameter("sala"));
        String tipo = valor(request.getParameter("tipo"));
        String descricao = valor(request.getParameter("descricao"));
        String erroSala = sala.isBlank() ? "Indique a sala." : null;
        String erroTipo = !TIPOS_VALIDOS.contains(tipo) ? "Selecione um tipo de avaria válido." : null;
        String erroDescricao = descricao.length() < 10 ? "A descrição tem de ter pelo menos 10 caracteres." : null;

        if (erroSala != null || erroTipo != null || erroDescricao != null) {
            request.setAttribute("sala", sala); request.setAttribute("tipo", tipo);
            request.setAttribute("descricao", descricao); request.setAttribute("erroSala", erroSala);
            request.setAttribute("erroTipo", erroTipo); request.setAttribute("erroDescricao", erroDescricao);
            request.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp").forward(request, response);
            return;
        }

        try {
            new Repositorio().adicionarAvaria(sala, tipo, descricao);
            request.setAttribute("sala", sala); request.setAttribute("tipo", tipo);
            request.setAttribute("descricao", descricao);
            request.getRequestDispatcher("/WEB-INF/avaria-confirmacao.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("erroBD", "Não foi possível guardar o pedido. Confirme a ligação à base de dados.");
            request.setAttribute("sala", sala); request.setAttribute("tipo", tipo);
            request.setAttribute("descricao", descricao);
            request.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp").forward(request, response);
        }
    }

    private String valor(String s) { return s == null ? "" : s.trim(); }
}
