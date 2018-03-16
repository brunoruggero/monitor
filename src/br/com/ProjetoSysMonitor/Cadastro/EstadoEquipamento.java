package br.com.ProjetoSysMonitor.Cadastro;

import java.util.Date;

public class EstadoEquipamento {
	
	private int idEstado;
	private String nomeEstado; 
	private String ipEquipamento;
	private String estadoEqui;  
	private Date dataEstado = new Date();
	
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	public String getIpEquipamento() {
		return ipEquipamento;
	}
	public void setIpEquipamento(String ipEquipamento) {
		this.ipEquipamento = ipEquipamento;
	}
	public String getEstadoEqui() {
		return estadoEqui;
	}
	public void setEstadoEqui(String estadoEqui) {
		this.estadoEqui = estadoEqui;
	}
	public Date getDataEstado() {
		return dataEstado;
	}
	public void setDataEstado(Date dataEstado) {
		this.dataEstado = dataEstado;
	}
	
	
	
	
	
}
