package progweb.action;

import javax.servlet.http.HttpServletRequest;

//T é o tipo do VO
public abstract class GenericAction<T> {

      private HttpServletRequest request;
      public HttpServletRequest getRequest() {
                return request;
      }
      public void setRequest(HttpServletRequest request) {
                this.request = request;
                // executa a acção no momento que recebe a requisição
                action();
      }
     
      // método para executar ação
      // será utilizado para todo controle sobre as ações do formulário
      public abstract void action();
     
      // retorna o objeto de saída (exibição na página)
      public abstract T getVOOutput();
     
      // objeto que representa os dados do formulário
      public abstract T getVOInput() throws Throwable;
     
     
      public String getOp(){
                return getRequest().getParameter("op");
      }
      public boolean isInsert(){
                return "INSERT".equalsIgnoreCase(getOp());
      }
      public boolean isSelect(){
                return "SELECT".equalsIgnoreCase(getOp());
      }
      public boolean isDelete(){
                return "DELETE".equalsIgnoreCase(getOp());
      }
      public boolean isUpdate(){
                return "UPDATE".equalsIgnoreCase(getOp());
      }
      public boolean isLoad(){
                return "LOAD".equalsIgnoreCase(getOp());
      }
      public boolean isIni(){
                return getOp()==null || "".equals(getOp());
      }
}
