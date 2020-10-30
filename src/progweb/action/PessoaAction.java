package progweb.action;

import java.util.ArrayList;
import progweb.dao.PesquisaPessoaDAO;
import progweb.dao.PessoaDAO;
import progweb.vo.PessoaVO;
 
public class PessoaAction extends GenericAction<PessoaVO>{
 
         private String mensagemAction;
         private PessoaVO voOut = new PessoaVO();
         private ArrayList<PessoaVO> tabPessoas;
         private ArrayList<PessoaVO> comboPessoas;
        
         @Override
         public void action() {
                   try{
                            PessoaVO voInput = getVOInput();
                            if(isIni()){
                                      setMensagemAction("Preencha os campos e clique na ação desejada");
                            }else if(isInsert()){
                                      PessoaDAO dao = new PessoaDAO(voInput);
                                      dao.inserir();
                                      setMensagemAction("Registro incluído com sucesso");
                            }else if(isDelete()){
                                      PessoaDAO dao = new PessoaDAO(voInput);
                                      dao.excluir();
                                      setMensagemAction("Registro excluído com sucesso");
                            }else if(isUpdate()){
                                      PessoaDAO dao = new PessoaDAO(voInput);
                                      dao.alterar();
                                      setMensagemAction("Registro alterado com sucesso");
                            }
                            if(isSelect()){
                                      PesquisaPessoaDAO daoBusca = new PesquisaPessoaDAO(voInput);
                                      tabPessoas = daoBusca.getResultadoPesquisa();
                                      setMensagemAction("Filtros aplicados com sucesso");
                            }else{
                                      // busca todos, pois recebe um vo vazio como filtro
                                      PesquisaPessoaDAO daoBuscaTodos = new PesquisaPessoaDAO(new PessoaVO());
                                      tabPessoas = daoBuscaTodos.getResultadoPesquisa();
                            }
                            if(isLoad()){
                                      PessoaVO filtroPeloId = new PessoaVO();
                                      filtroPeloId.setId(voInput.getId());
                                      PesquisaPessoaDAO daoBusca = new PesquisaPessoaDAO(filtroPeloId);
                                      voOut = daoBusca.getPessoa();
                                      setMensagemAction("Dados carregados com sucesso");
                            }
                            // busca todos, pois recebe um vo vazio como filtro
                            PesquisaPessoaDAO daoBuscaTodos = new PesquisaPessoaDAO(new PessoaVO());
                            comboPessoas = daoBuscaTodos.getResultadoPesquisa();
                           
                   }catch (Throwable e) {
                            setMensagemAction("ERRO AO EXECUTAR ACTION: "+e.getMessage());
                            e.printStackTrace();
                   }
         }
         @Override
         public PessoaVO getVOInput() throws Throwable {
                   PessoaVO voInput = new PessoaVO();
                   String id = getRequest().getParameter("id");
                   if(id!=null && id.trim().length()>0) {
                            voInput.setId(new Integer(id));
                   }
                   voInput.setNome( getRequest().getParameter("nome") );
                   voInput.setCpf( getRequest().getParameter("cpf") );
                   String idConjuge = getRequest().getParameter("idConjuge");
                   if(idConjuge!=null && idConjuge.trim().length()>0) {
                            voInput.setIdConjuge(new Integer(idConjuge));
                   }
                   return voInput;
         }
         @Override
         public PessoaVO getVOOutput() {
                   return voOut;
         }
         public String getMensagemAction() {
                   return mensagemAction;
         }
         public void setMensagemAction(String mensagem) {
                   this.mensagemAction = mensagem;
         }
         public ArrayList<PessoaVO> getComboPessoas() {
                   return comboPessoas;
         }
         public ArrayList<PessoaVO> getTabPessoas() {
                   return tabPessoas;
         }
}