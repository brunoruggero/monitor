package br.com.ProjetoSysMonitor.ConectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjetoSysMonitor.Cadastro.CadastroUsuario;


public class ConectaUsuarioDAO {
	
	//Criando a conexão com o Banco de Dados
		private Connection getConnection() throws Exception{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bd_sysmonitor", "root", "abc123@");
			return con;
		}
		
		//Inserindo um novo registro no Banco de Dados
		public boolean insert(CadastroUsuario caduser) throws Exception{
			String sqlInsert = "insert into usuario(nomeUsuario, user, pass) values (?,?,?);";
					
					Connection con = getConnection();
					PreparedStatement ps = con.prepareStatement(sqlInsert);
					ps.setString(1, caduser.getNomeUsuario());
					ps.setString(2, caduser.getUsuario());
					ps.setString(3, caduser.getSenha());
					int ret = ps.executeUpdate();
					ps.close();
					con.close();
					return true;
		}
		
		//Atualizando um registro já existente no Banco de Dados
			public boolean update(CadastroUsuario caduser) throws Exception{
				
				String sqlUpdate = "update usuario set nomeUsuario = ?, user = ?, pass = ? where idUsuario = ?";
				
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sqlUpdate);
				ps.setString(1, caduser.getNomeUsuario());
				ps.setString(2, caduser.getUsuario());
				ps.setString(3, caduser.getSenha());
				int ret = ps.executeUpdate();
				ps.close();
				con.close();
				return ret==1;
			}
			
			//Deletando um registro já existente no Banco de Dados
			public boolean delete(int id) throws Exception{
				
				String sqlDelete = "delete from usuario where idUsuario = ?";
				
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sqlDelete);
				ps.setInt(1, id);
				int ret = ps.executeUpdate();
				ps.close();
				con.close();
				return ret == 1;
			}
			
			//Selecionando um registro já existente no Banco de Dados
			public CadastroUsuario getCadUsuario (int idUsuario) throws Exception{
				
				String sqlSelect = "select idUsuario, nomeUsuario, user, pass from usuario where idUsuario =?";
				
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sqlSelect);
				ps.setInt(1, idUsuario);
				ResultSet rs = ps.executeQuery();
				rs.next();
				CadastroUsuario caduser = new CadastroUsuario();
				caduser.setIdUsuario(rs.getInt(1));
				caduser.setNomeUsuario(rs.getString(2));
				caduser.setUsuario(rs.getString(3));
				caduser.setSenha(rs.getString(4));
				rs.close();
				ps.close();
				con.close();
				return caduser;
			}
			
			//Listando um registro do Banco de Dados
			public List<CadastroUsuario> listAll() throws Exception{
				
				String sqlList = "select idUsuario, nomeUsuario, user, pass from usuario";
				
				List<CadastroUsuario> list = new ArrayList<CadastroUsuario>();
				
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sqlList);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					CadastroUsuario caduser = new CadastroUsuario();
					caduser.setIdUsuario(rs.getInt(1));
					caduser.setNomeUsuario(rs.getString(2));
					caduser.setUsuario(rs.getString(3));
					caduser.setSenha(rs.getString(4));
					list.add(caduser);
				}
				rs.close();
				ps.close();
				con.close();
				return list;		
			}
			
			//Mostrando a quantidade TOTAL de registro no Banco de Dados
			public long getTotal() throws Exception{
				
				String sqlTotal = "select count(*) from usuario";
				
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
