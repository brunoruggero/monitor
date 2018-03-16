package br.com.ProjetoSysMonitor.ConectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjetoSysMonitor.Cadastro.CadastroEquipamento;

public class ConectaEquipamentoDAO {
	
	//Criando a conexão com o Banco de Dados
	private Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bd_sysmonitor", "root", "abc123@");
		return con;
	}
	
	//Inserindo um novo registro no Banco de Dados
	public boolean insert(CadastroEquipamento equi) throws Exception{
		String sqlInsert = "insert into equipamento(nomeEquipamento, grupoTrabalho, setor, enderecoIP, maskRede,"
				+ "gatewayPadrao, dnsPrimario, dnsSecundario, dataCadastro) values (?,?,?,?,?,?,?,?,?);";
				
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sqlInsert);
				ps.setString(1, equi.getNomeEquipamento());
				ps.setString(2, equi.getGrupoTrabalho());
				ps.setString(3, equi.getSetor());
				ps.setString(4, equi.getEnderecoIP());
				ps.setString(5, equi.getMaskRede());
				ps.setString(6, equi.getGatewayPadrao());
				ps.setString(7, equi.getdnsPrimario());
				ps.setString(8, equi.getdnsSecundario());
				ps.setDate(9, new java.sql.Date(System.currentTimeMillis()));
				int ret = ps.executeUpdate();
				ps.close();
				con.close();
				return true;
	}
	
	//Atualizando um registro já existente no Banco de Dados
		public boolean update(CadastroEquipamento equi) throws Exception{
			
			String sqlUpdate = "update equipamento set nomeEquipamento = ?, grupoTrabalho = ?, setor = ?, enderecoIP = ?, maskRede = ?,"
					+ "gatewayPadrao = ?, dnsPrimario = ?, dnsSecundario = ? where idEquipamento = ?";
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sqlUpdate);
			ps.setString(1, equi.getNomeEquipamento());
			ps.setString(2, equi.getGrupoTrabalho());
			ps.setString(3, equi.getSetor());
			ps.setString(4, equi.getEnderecoIP());
			ps.setString(5, equi.getMaskRede());
			ps.setString(6, equi.getGatewayPadrao());
			ps.setString(7, equi.getdnsPrimario());
			ps.setString(8, equi.getdnsSecundario());
			ps.setInt(9, equi.getIdEquipamento());
			int ret = ps.executeUpdate();
			ps.close();
			con.close();
			return ret==1;
		}
		
		//Deletando um registro já existente no Banco de Dados
		public boolean delete(int id) throws Exception{
			
			String sqlDelete = "delete from equipamento where idEquipamento = ?";
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sqlDelete);
			ps.setInt(1, id);
			int ret = ps.executeUpdate();
			ps.close();
			con.close();
			return ret==1;
		}
		
		//Selecionando um registro já existente no Banco de Dados
		public CadastroEquipamento getEquipamento (int idEquipamento) throws Exception{
			
			String sqlSelect = "select idEquipamento, nomeEquipamento, grupoTrabalho, setor, enderecoIP, maskRede,"
					+ "gatewayPadrao, dnsPrimario, dnsSecundario from equipamento where idEquipamento =?";
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sqlSelect);
			ps.setInt(1, idEquipamento);
			ResultSet rs = ps.executeQuery();
			rs.next();
			CadastroEquipamento equi = new CadastroEquipamento();
			equi.setIdEquipamento(rs.getInt(1));
			equi.setNomeEquipamento(rs.getString(2));
			equi.setGrupoTrabalho(rs.getString(3));
			equi.setSetor(rs.getString(4));
			equi.setEnderecoIP(rs.getString(5));
			equi.setMaskRede(rs.getString(6));
			equi.setGatewayPadrao(rs.getString(7));
			equi.setdnsPrimario(rs.getString(8));
			equi.setdnsSecundario(rs.getString(9));
			rs.close();
			ps.close();
			con.close();
			return equi;
		}
		
		//Listando um registro do Banco de Dados
		public List<CadastroEquipamento> listAll() throws Exception{
			
			String sqlList = "select idEquipamento, nomeEquipamento, grupoTrabalho, setor, enderecoIP, maskRede,"
					+ "gatewayPadrao, dnsPrimario, dnsSecundario from equipamento";
			
			List<CadastroEquipamento> list = new ArrayList<CadastroEquipamento>();
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sqlList);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				CadastroEquipamento equi = new CadastroEquipamento();
				equi.setIdEquipamento(rs.getInt(1));
				equi.setNomeEquipamento(rs.getString(2));
				equi.setGrupoTrabalho(rs.getString(3));
				equi.setSetor(rs.getString(4));
				equi.setEnderecoIP(rs.getString(5));
				equi.setMaskRede(rs.getString(6));
				equi.setGatewayPadrao(rs.getString(7));
				equi.setdnsPrimario(rs.getString(8));
				equi.setdnsSecundario(rs.getString(9));
				list.add(equi);
			}
			rs.close();
			ps.close();
			con.close();
			return list;		
		}
		
		//Mostrando a quantidade TOTAL de registro no Banco de Dados
		public long getTotal() throws Exception{
			
			String sqlTotal = "select count(*) from equipamento";
			
			long total = 0;
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(sqlTotal);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				total = rs.getLong(1);
			}
			rs.close();
			ps.close();
			con.close();
			return total;
		}


}
