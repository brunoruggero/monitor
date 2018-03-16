package br.com.ProjetoSysMonitor.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ProjetoSysMonitor.Cadastro.CadastroUsuario;
import br.com.ProjetoSysMonitor.ConectaBanco.ConectaUsuarioDAO;

@ManagedBean(name="UsuarioMBean")
@SessionScoped
public class UsuarioMBean {
	
	private List<CadastroUsuario> lista = new ArrayList<CadastroUsuario>();
	private CadastroUsuario selecionado;
	private CadastroUsuario novo = new CadastroUsuario();
	private ConectaUsuarioDAO dao = new ConectaUsuarioDAO();
	
	//Metodo para mostrar resgitro incluso
	public String mostrarIncluir(){
		selecionado = new CadastroUsuario();
		return "listaUsuario";
	}
	
	//Metodo para incluir novo registro
	public String incluir() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		try{
			dao.insert(novo);
			novo = new CadastroUsuario();
			context.addMessage(null, new FacesMessage("Sucesso",  "Usuário Cadastrado!") );
		}catch (Exception ex){
			context.addMessage(null, new FacesMessage("Erro",  "Erro ao inserir no banco " + ex.getMessage()) );
	        return "404";
		}
		return "listaUsuario?faces-redirect=true";
	}
	
	//Metodo para alterar um registro
	public String alterar() throws Exception{
		dao.update(selecionado);
		return "listaUsuario"; //retornara a lista dos usuários cadastrados
	}
	
	//Metodo para listar um registro
	public List<CadastroUsuario> getLista() throws Exception{
		lista = dao.listAll();
		return lista;
	}
	
	//Metodo para excluir um registro
	public String excluir(int id) throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		try{
			dao.delete(id);
			context.addMessage(null, new FacesMessage("Sucesso",  "Usuário Deletado!") );
		}catch (Exception ex){
			context.addMessage(null, new FacesMessage("Erro",  "Erro ao deletar no banco " + ex.getMessage()) );
			return null;
		}
		return "removerUsuario?faces-redirect=true";
	}
	
	//Metodo para TOTAL de registros
	public long totalUsuario() throws Exception{
		return dao.getTotal();
	}
	
	
	
	//Getters and Setters	
	public CadastroUsuario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(CadastroUsuario selecionado) {
		this.selecionado = selecionado;
	}

	public CadastroUsuario getNovo() {
		return novo;
	}

	public void setNovo(CadastroUsuario novo) {
		this.novo = novo;
	}

	public void setLista(List<CadastroUsuario> lista) {
		this.lista = lista;
	}
}
