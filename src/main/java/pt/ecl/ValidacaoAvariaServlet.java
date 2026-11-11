package pt.ecl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/** Recebe, valida e encaminha o pedido de comunicação de avaria. */
public class ValidacaoAvariaServlet extends HttpServlet {
    private static final Set<String> TIPOS_VALIDOS =
        Set.of("Equipamento", "Software", "Rede");

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp")
            .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String salaRecebida = request.getParameter("sala");
        String tipoRecebido = request.getParameter("tipo");
        String descricaoRecebida = request.getParameter("descricao");

        String sala = salaRecebida == null ? "" : salaRecebida.trim();
        String tipo = tipoRecebido == null ? "" : tipoRecebido.trim();
        String descricao = descricaoRecebida == null
            ? "" : descricaoRecebida.trim();

        String erroSala = sala.isEmpty() ? "Indique a sala." : null;
        String erroTipo = !TIPOS_VALIDOS.contains(tipo)
            ? "Selecione um tipo de avaria válido." : null;
        String erroDescricao = descricao.length() < 10
            ? "A descrição tem de ter pelo menos 10 caracteres." : null;

        if (erroSala != null || erroTipo != null || erroDescricao != null) {
            // Preserva os valores recebidos para o utilizador poder corrigi-los.
            request.setAttribute("sala", salaRecebida == null ? "" : salaRecebida);
            request.setAttribute("tipo", tipoRecebido == null ? "" : tipoRecebido);
            request.setAttribute("descricao",
                descricaoRecebida == null ? "" : descricaoRecebida);
            request.setAttribute("erroSala", erroSala);
            request.setAttribute("erroTipo", erroTipo);
            request.setAttribute("erroDescricao", erroDescricao);
            request.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp")
                .forward(request, response);
            return;
        }

        request.setAttribute("sala", sala);
        request.setAttribute("tipo", tipo);
        request.setAttribute("descricao", descricao);
        request.getRequestDispatcher("/WEB-INF/avaria-confirmacao.jsp")
            .forward(request, response);
    }
}
