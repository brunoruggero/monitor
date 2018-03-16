package br.com.ProjetoSysMonitor.Monitoramento;

import java.net.InetAddress;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.ProjetoSysMonitor.Cadastro.CadastroEquipamento;
import br.com.ProjetoSysMonitor.Cadastro.EstadoEquipamento;
import br.com.ProjetoSysMonitor.ConectaBanco.ConectaEquipamentoDAO;
import br.com.ProjetoSysMonitor.ConectaBanco.ConectaEstadoEquipamentoDAO;


public class AppContextListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		// Your code here
		System.out.println("Listener has been shutdown");

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// Your code here
		System.out.println("Listener initialized.");

		TimerTask vodTimer = new VodTimerTask();

		Timer timer = new Timer();
		timer.schedule(vodTimer, 1000, (2 * 1000));

	}

	class VodTimerTask extends TimerTask {
		boolean running = false;
		@Override
		public void run() {
			if (running) {
				return;
			}
			try {
				running = true;
				pingEquipamentos();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				running = false;
			}
			System.out.println("TimerTask " + new Date().toString());
		}
	}

	
	public void pingEquipamentos() throws Exception {
		ConectaEquipamentoDAO dao = new ConectaEquipamentoDAO();
		List<CadastroEquipamento> lst = dao.listAll();
		
		ConectaEstadoEquipamentoDAO daoEstado = new ConectaEstadoEquipamentoDAO();
		EstadoEquipamento novo = new EstadoEquipamento();
		
		
		
		for (CadastroEquipamento eqpt : lst) {
			
			InetAddress inet = InetAddress.getByName(eqpt.getEnderecoIP());
			System.out.println("Enviando Ping: " + inet);
			
				if(inet.isReachable(5000)){
					System.out.println(inet + " - OnLine");
					novo.setEstadoEqui("Online");
					
				}else{
					System.out.println(inet + " - OffLine");
					novo.setEstadoEqui("Offline");

				}
			
					novo.setNomeEstado(eqpt.getNomeEquipamento());
					novo.setIpEquipamento(eqpt.getEnderecoIP());
					novo.setDataEstado(novo.getDataEstado());
					daoEstado.insertOrUpdate(novo);
							
			
		
		}
			
			
		}
}
	
//orignal
//
//for (CadastroEquipamento eqpt : lst) {
//			
//			InetAddress inet = InetAddress.getByName(eqpt.getEnderecoIP());
//			System.out.println("Enviando Ping: " + eqpt.getNomeEquipamento() + " Ping: "
//					+ inet.isReachable(5000));
//			novo.setNomeEstado(eqpt.getNomeEquipamento());
//			novo.setIpEquipamento(eqpt.getEnderecoIP());
//			//novo.setEstadoEqui(InetAddress.getByName(arg0));
//
//			novo.setDataEstado(novo.getDataEstado());
//		
//			daoEstado.insert(novo);
//		}
//public void pingEquipamentos() throws Exception {
//	ConectaEquipamentoDAO dao = new ConectaEquipamentoDAO();
//	List<CadastroEquipamento> lst = dao.listAll();
//	
//	ConectaEstadoEquipamentoDAO daoEstado = new ConectaEstadoEquipamentoDAO();
//	EstadoEquipamento novo = new EstadoEquipamento();
//	
//	for (CadastroEquipamento eqpt : lst) {
//		
//		InetAddress inet = InetAddress.getByName(eqpt.getEnderecoIP());
//		System.out.println("Enviando Ping: " + inet);
//		
//			if(inet.isReachable(5000)){
//				System.out.println(inet + " - OnLine");
//				novo.setEstadoEqui("Online");
//				
//			}else{
//				System.out.println(inet + " - OffLine");
//				novo.setEstadoEqui("Offline");
//
//			}
//			
//			
//		novo.setNomeEstado(eqpt.getNomeEquipamento());
//		novo.setIpEquipamento(eqpt.getEnderecoIP());
//		novo.setDataEstado(novo.getDataEstado());
//		daoEstado.insert(novo);
//	}
//		
//		
//	}
