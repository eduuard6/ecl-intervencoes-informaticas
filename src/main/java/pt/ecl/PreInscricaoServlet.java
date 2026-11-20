package pt.ecl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
public class PreInscricaoServlet extends HttpServlet {
 private String campo(HttpServletRequest r,String n){String v=r.getParameter(n);return v==null?"":v.trim();}
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  List<String[]> cursos=new ArrayList<>();
  try(Connection c=BaseDados.ligar();PreparedStatement ps=c.prepareStatement("SELECT id,nome FROM cursos WHERE ativo=TRUE ORDER BY nome");ResultSet rs=ps.executeQuery()){
   while(rs.next())cursos.add(new String[]{String.valueOf(rs.getInt("id")),rs.getString("nome")});
  }catch(SQLException e){req.setAttribute("erro","Não foi possível carregar os cursos. Confirme a base de dados.");}
  req.setAttribute("cursos",cursos);req.getRequestDispatcher("/WEB-INF/pre-inscricao.jsp").forward(req,resp);
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");String nome=campo(req,"nome"),email=campo(req,"email"),telefone=campo(req,"telefone"),cursoTexto=campo(req,"curso"),erro=null;int cursoId=0;
  if(nome.length()<3||nome.length()>120)erro="Indique o nome completo (3 a 120 caracteres).";
  else if(!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")||email.length()>150)erro="Indique um email válido.";
  else if(!telefone.isBlank()&&!telefone.matches("[0-9 +()\\-]{6,25}"))erro="O telefone não é válido.";
  else{try{cursoId=Integer.parseInt(cursoTexto);}catch(NumberFormatException e){erro="Selecione um curso válido.";}}
  if(erro==null){String sql="INSERT INTO pre_inscricoes (nome,email,telefone,curso_id) SELECT ?,?,?,id FROM cursos WHERE id=? AND ativo=TRUE";
   try(Connection c=BaseDados.ligar();PreparedStatement ps=c.prepareStatement(sql)){ps.setString(1,nome);ps.setString(2,email);ps.setString(3,telefone);ps.setInt(4,cursoId);if(ps.executeUpdate()!=1)erro="O curso selecionado não está disponível.";}
   catch(SQLException e){erro="Não foi possível guardar a pré-inscrição. Tente novamente.";}
  }
  if(erro!=null){req.setAttribute("erro",erro);req.setAttribute("nome",nome);req.setAttribute("email",email);req.setAttribute("telefone",telefone);doGet(req,resp);return;}
  req.setAttribute("nome",nome);req.getRequestDispatcher("/WEB-INF/pre-inscricao-confirmacao.jsp").forward(req,resp);
 }
}