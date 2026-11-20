package pt.ecl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class TerminarSessaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession sessao = req.getSession(false);
        if (sessao != null) sessao.invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
