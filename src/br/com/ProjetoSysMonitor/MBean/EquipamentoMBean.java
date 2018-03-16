package br.com.ProjetoSysMonitor.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ProjetoSysMonitor.Cadastro.CadastroEquipamento;
import br.com.ProjetoSysMonitor.ConectaBanco.ConectaEquipamentoDAO;

@ManagedBean(name="EquipamentoMBean")
@SessionScoped
	
public class EquipamentoMBean {
		
		private List<CadastroEquipamento> lista = new ArrayList<CadastroEquipamento>();
		private CadastroEquipamento selecionado;
		private CadastroEquipamento novo = new CadastroEquipamento();
		private ConectaEquipamentoDAO dao = new ConectaEquipamentoDAO();
		
		//Metodo para mostrar resgitro incluso
		public String mostrarIncluir(){
			selecionado = new CadastroEquipamento();
			return "dashboard";
		}
		
		//Metodo para incluir novo registro
		public String incluir() throws Exception{
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			try {
				dao.insert(novo);
				novo = new CadastroEquipamento();
		        context.addMessage(null, new FacesMessage("Sucesso",  "Equipamento Cadastrado!") );
			}catch (Exception ex){
		        context.addMessage(null, new FacesMessage("Erro",  "Erro ao inserir no banco " + ex.getMessage()) );
		        return "404";
			}
			return "monitoramento?faces-redirect=true";
		}
		
		//Metodo para alterar um registro
		public String alterar() throws Exception{
			dao.update(selecionado);
			return "listaEquipamento"; //retornara a lista dos equipamentos cadastrados
		}
		
		//Metodo para listar um registro
		public List<CadastroEquipamento> getLista() throws Exception{
			lista = dao.listAll();
			return lista;
		}
		
		//Metodo para excluir um registro
		public String excluir(int id) throws Exception{
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			try {
				dao.delete(id);
				context.addMessage(null, new FacesMessage("Sucesso",  "Equipamento Deletado!") );
			}catch (Exception ex){
				context.addMessage(null, new FacesMessage("Erro",  "Erro ao inserir no banco " + ex.getMessage()) );
		        return null;
			}
			return "listaEquipamento?faces-redirect=true";
		}
		
		//Metodo para TOTAL de registros
		public long totalEquipamento() throws Exception{
			return dao.getTotal();
		}
		
		
		
		//Getters and Setters	
		public CadastroEquipamento getSelecionado() {
			return selecionado;
		}

		public void setSelecionado(CadastroEquipamento selecionado) {
			this.selecionado = selecionado;
		}

		public CadastroEquipamento getNovo() {
			return novo;
		}

		public void setNovo(CadastroEquipamento novo) {
			this.novo = novo;
		}

		public void setLista(List<CadastroEquipamento> lista) {
			this.lista = lista;
		}

}
