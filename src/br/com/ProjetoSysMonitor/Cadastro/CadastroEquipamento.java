package br.com.ProjetoSysMonitor.Cadastro;

import java.util.Date;

public class CadastroEquipamento {
	
	private int idEquipamento;
	private String nomeEquipamento;
	private String grupoTrabalho;
	private String setor;
	private String enderecoIP;
	private String maskRede;
	private String gatewayPadrao; 
	private String dnsPrimario;
	private String dnsSecundario;
	private Date dataCadastro = new Date();
	
	public int getIdEquipamento() {
		return idEquipamento;
	}
	public void setIdEquipamento(int idEquipamento) {
		this.idEquipamento = idEquipamento;
	}
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}
	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
	}
	public String getGrupoTrabalho() {
		return grupoTrabalho;
	}
	public void setGrupoTrabalho(String grupoTrabalho) {
		this.grupoTrabalho = grupoTrabalho;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getEnderecoIP() {
		return enderecoIP;
	}
	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}
	public String getMaskRede() {
		return maskRede;
	}
	public void setMaskRede(String maskRede) {
		this.maskRede = maskRede;
	}
	public String getGatewayPadrao() {
		return gatewayPadrao;
	}
	public void setGatewayPadrao(String gatewayPadrao) {
		this.gatewayPadrao = gatewayPadrao;
	}
	public String getdnsPrimario() {
		return dnsPrimario;
	}
	public void setdnsPrimario(String dnsPrimario) {
		this.dnsPrimario = dnsPrimario;
	}
	public String getdnsSecundario() {
		return dnsSecundario;
	}
	public void setdnsSecundario(String dnsSecundario) {
		this.dnsSecundario = dnsSecundario;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
