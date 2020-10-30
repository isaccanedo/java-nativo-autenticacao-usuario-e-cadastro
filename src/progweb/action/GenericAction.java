package progweb.action;

import javax.servlet.http.HttpServletRequest;

//T � o tipo do VO
public abstract class GenericAction<T> {

      private HttpServletRequest request;
      public HttpServletRequest getRequest() {
                return request;
      }
      public void setRequest(HttpServletRequest request) {
                this.request = request;
                // executa a ac��o no momento que recebe a requisi��o
                action();
      }
     
      // m�todo para executar a��o
      // ser� utilizado para todo controle sobre as a��es do formul�rio
      public abstract void action();
     
      // retorna o objeto de sa�da (exibi��o na p�gina)
      public abstract T getVOOutput();
     
      // objeto que representa os dados do formul�rio
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
