package br.com.ProjetoSysMonitor.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ProjetoSysMonitor.Cadastro.EstadoEquipamento;
import br.com.ProjetoSysMonitor.ConectaBanco.ConectaEstadoEquipamentoDAO;


@ManagedBean(name="EstadoEquipamentoMBean")
@SessionScoped

public class EstadoEquipamentoMBean {
	
	private EstadoEquipamento selecionado;
	private List<EstadoEquipamento> lista = new ArrayList <EstadoEquipamento>();
	private EstadoEquipamento novo = new EstadoEquipamento();
	private ConectaEstadoEquipamentoDAO dao = new ConectaEstadoEquipamentoDAO();
	
	//Metodo para mostrar resgitro incluso
			public String mostrarIncluir(){
				selecionado = new EstadoEquipamento();
				return "dashboard";
			}
	
	//Metodo Listar
		public List<EstadoEquipamento> getLista() throws Exception{
			lista = dao.listAll();
			return lista;
		}
		
		//Metodo Incluir
		public String incluir() throws Exception{
			dao.insert(novo);
			novo = new EstadoEquipamento();
			return null; //Página de retorno
		}

		public EstadoEquipamento getSelecionado() {
			return selecionado;
		}

		public void setSelecionado(EstadoEquipamento selecionado) {
			this.selecionado = selecionado;
		}

		public EstadoEquipamento getNovo() {
			return novo;
		}

		public void setNovo(EstadoEquipamento novo) {
			this.novo = novo;
		}

		public void setLista(List<EstadoEquipamento> lista) {
			this.lista = lista;
		}
		
		
}
