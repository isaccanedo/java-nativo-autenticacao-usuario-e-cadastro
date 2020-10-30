package progweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import progweb.dao.LoginDAO;
 
public class ServletLogin extends HttpServlet {
       private static final long serialVersionUID = 1L;
 
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doPost(request, response); // para executar o doPost para GET ou POST
       }
 
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
             try{
                    // verifica se deseja terminar a sessão
                    if("true".equals(request.getParameter("sair"))){
                           request.getSession().invalidate();// termina a sessão
                           request.setAttribute("mensagem", "Sessão Terminada");
                           goToLogin(request, response);
                           return;// para não continuar a execução do método
                    }
                   
                    String login = request.getParameter("login");
                    String senha = request.getParameter("senha");
                   
                    if(login==null || login.length()==0 || senha==null || senha.length()==0){
                           request.setAttribute("mensagem", "Login e Senha Obrigatórios");
                           goToLogin(request, response);
                    }else{
                           LoginDAO dao = new LoginDAO(login, senha);
                           if(dao.verificaLogin()){
                                  request.setAttribute("mensagem", "Login Efetuado com Sucesso");
                                  request.getSession().setAttribute("autenticado", true);
                                  RequestDispatcher d = request.getRequestDispatcher("cadastro.jsp");
                                  d.forward(request, response);
                           }else{
                                  request.setAttribute("mensagem", "Login ou Senha Inválidos");
                                  goToLogin(request, response);
                           }
                    }
                   
             }catch (Exception e) {
                    request.setAttribute("mensagem", "Erro ao Autenticar: "+e.getMessage());
                    e.printStackTrace();
                    goToLogin(request, response);
             }
            
       }
 
       private void goToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
             RequestDispatcher d = request.getRequestDispatcher("index.jsp");
             d.forward(request, response);
       }
      
}