package br.com.ProjetoSysMonitor.Monitoramento;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.net.InetAddress;

public class PingaIP {

//  public static void runSystemCommand(String command) {
//
//		try {
//			Process p = Runtime.getRuntime().exec(command);
//			BufferedReader inputStream = new BufferedReader(
//					new InputStreamReader(p.getInputStream()));
//
//			String s = "";
//			// reading output stream of the command
//			while ((s = inputStream.readLine()) != null) {
//				System.out.println(s);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		
//		String ip = "www.google.com.br";
//		runSystemCommand("ping " + ip);
//
//	
//	}
	
	
	public static void main(String[] args) throws Exception {
	    String ipAddress = "127.0.0.1";
	    InetAddress inet = InetAddress.getByName(ipAddress);

	    System.out.println("Sending Ping Request to " + ipAddress);
	    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");

	    ipAddress = "8.8.8.8";
	    inet = InetAddress.getByName(ipAddress);

	    System.out.println("Sending Ping Request to " + ipAddress);
	    System.out.println(inet.isReachable(5000) ? "Host is reachable" : "Host is NOT reachable");
	}
}