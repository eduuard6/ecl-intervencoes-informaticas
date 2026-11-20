package pt.ecl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
public class CursosServlet extends HttpServlet {
 public static class Curso { public int id; public String nome,descricao,nivel; }
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  List<Curso> cursos=new ArrayList<>();
  try(Connection c=BaseDados.ligar();PreparedStatement ps=c.prepareStatement("SELECT id,nome,descricao,nivel FROM cursos WHERE ativo=TRUE ORDER BY nome");ResultSet rs=ps.executeQuery()){
   while(rs.next()){Curso x=new Curso();x.id=rs.getInt("id");x.nome=rs.getString("nome");x.descricao=rs.getString("descricao");x.nivel=rs.getString("nivel");cursos.add(x);}
  }catch(SQLException e){req.setAttribute("erro","Não foi possível carregar a oferta formativa. Confirme a base de dados.");}
  req.setAttribute("cursos",cursos);req.getRequestDispatcher("/WEB-INF/cursos.jsp").forward(req,resp);
 }
}