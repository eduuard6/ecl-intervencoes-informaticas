package pt.ecl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AutenticacaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String password = req.getParameter("password");
        String configurada = System.getenv("ECL_ADMIN_PASSWORD");

        if (configurada == null || configurada.isBlank()) {
            req.setAttribute("erro", "O administrador ainda não configurou a palavra-passe no servidor.");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        byte[] recebida = (password == null ? "" : password).getBytes(StandardCharsets.UTF_8);
        byte[] correta = configurada.getBytes(StandardCharsets.UTF_8);
        if (MessageDigest.isEqual(recebida, correta)) {
            req.getSession(true).setAttribute("eclAdmin", Boolean.TRUE);
            resp.sendRedirect(req.getContextPath() + "/pedidos");
        } else {
            req.setAttribute("erro", "Palavra-passe incorreta.");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
