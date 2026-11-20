package pt.ecl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ProtecaoPedidosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession sessao = req.getSession(false);

        if (sessao != null && Boolean.TRUE.equals(sessao.getAttribute("eclAdmin"))) {
            chain.doFilter(request, response);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
