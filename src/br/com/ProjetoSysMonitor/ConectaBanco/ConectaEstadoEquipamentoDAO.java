package br.com.ProjetoSysMonitor.ConectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjetoSysMonitor.Cadastro.EstadoEquipamento;

public class ConectaEstadoEquipamentoDAO {
	
		//Criando a conexão com o Banco de Dados
		private Connection getConnection() throws Exception{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bd_sysmonitor", "root", "abc123@");
			return con;
		}
		
		//inserindo no banco
		public boolean insert (EstadoEquipamento estado) throws Exception{
			String sqlInsert = "insert into estadoEquipamento(nomeEstado, ipEquipamento, estadoEqui, dataEstado) values (?,?,?,?);";
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlInsert);
					ps.setString(1, estado.getNomeEstado());
					ps.setString(2, estado.getIpEquipamento());
					ps.setString(3, estado.getEstadoEqui());
//					ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
					ps.setTimestamp(4, new Timestamp(estado.getDataEstado().getTime()));
					int ret = ps.executeUpdate();
					ps.close();
					con.close();
					return true;
		}
		
		//inserindo no banco
		public boolean update (EstadoEquipamento estado) throws Exception{
			String sqlUpdate = "update estadoEquipamento set nomeEstado = ?, ipEquipamento = ?, estadoEqui = ?, dataEstado= ?"
					+ " where idEstado = ?";
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlUpdate);
					ps.setString(1, estado.getNomeEstado());
					ps.setString(2, estado.getIpEquipamento());
					ps.setString(3, estado.getEstadoEqui());
					ps.setTimestamp(4, new Timestamp(estado.getDataEstado().getTime()));
					ps.setInt(5, estado.getIdEstado());
					int ret = ps.executeUpdate();
					ps.close();
					con.close();
					return true;
		}
		
		
		//inserindo no banco
				public boolean insertOrUpdate (EstadoEquipamento estado) throws Exception{
					EstadoEquipamento old = getEstadoByEquipamento(estado.getIpEquipamento());
					if (old != null) {
						estado.setIdEstado(old.getIdEstado());
						update(estado);
					} else {
						insert(estado);
					}
					return true;
				}
		
		//Listando um registro do Banco de Dados
				public List<EstadoEquipamento> listAll() throws Exception{
					
					String sqlList = "select idEstado, nomeEstado, ipEquipamento, estadoEqui, dataEstado from estadoEquipamento";
					
					List<EstadoEquipamento> list = new ArrayList<EstadoEquipamento>();
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlList);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						EstadoEquipamento estado = new EstadoEquipamento();
						estado.setIdEstado(rs.getInt(1));
						estado.setNomeEstado(rs.getString(2));
						estado.setIpEquipamento(rs.getString(3));
						estado.setEstadoEqui(rs.getString(4));
						estado.setDataEstado(rs.getTimestamp(5));
						
						list.add(estado);
					}
					rs.close();
					ps.close();
					con.close();
					return list;		
				}
				
		//Selecionando um registro já existente no Banco de Dados
				public EstadoEquipamento getEstadoEquipamento (int idEstado) throws Exception{
				
					String sqlSelect = "select idEstado, nomeEstado, ipEquipamento, estadoEqui, dataEstado from estadoEquipamento where idEstado = ?";
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlSelect);
					ps.setInt(1, idEstado);
					ResultSet rs = ps.executeQuery();
					rs.next();
					EstadoEquipamento estado = new EstadoEquipamento();
					estado.setIdEstado(rs.getInt(1));
					estado.setNomeEstado(rs.getString(2));
					estado.setIpEquipamento(rs.getString(3));
					estado.setEstadoEqui(rs.getString(4));
					estado.setDataEstado(rs.getTimestamp(5));
					rs.close();
					ps.close();
					con.close();
					return estado;
					
					
				}
				

				public EstadoEquipamento getEstadoByEquipamento (String ipEqpt) throws Exception{
					
					String sqlSelect = "select idEstado, nomeEstado, ipEquipamento, estadoEqui, dataEstado "
							+ "from estadoEquipamento where ipEquipamento = ?";
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlSelect);
					ps.setString(1, ipEqpt);
					ResultSet rs = ps.executeQuery();
					EstadoEquipamento estado = null;
					if (rs.next()) {
						estado = new EstadoEquipamento();
						estado.setIdEstado(rs.getInt(1));
						estado.setNomeEstado(rs.getString(2));
						estado.setIpEquipamento(rs.getString(3));
						estado.setEstadoEqui(rs.getString(4));
						estado.setDataEstado(rs.getTimestamp(5));
					}
					rs.close();
					ps.close();
					con.close();
					return estado;
					
				}
}
