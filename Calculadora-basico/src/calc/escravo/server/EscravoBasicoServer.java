package calc.escravo.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import calc.rmi.stub.Mestre;

public class EscravoBasicoServer implements Runnable {
	
	
	private EscravoBasicoImple slave;
	private boolean conectado = false;
	
	private String host;
	private int porta;
	private Mestre master;
	
	public EscravoBasicoServer(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}
	
	@Override
	public void run() {
		while (!conectado) {
			try {			
				slave = new EscravoBasicoImple();
				master = (Mestre) Naming.lookup("rmi://" + host + ":" + porta + "/Master");
				conectado = master.registraEscravo(slave);
				slave.VerificaMasterOnline(master);
				System.out.println("Servidor slave Basico ONLINE" );
			
			} catch (RemoteException e) {
				e.printStackTrace();
				System.out.println("Não foi possível se conectar ao Servidor Master");
			} catch (MalformedURLException e) {
				System.out.println("Verificaque a url, o Host ou a portas estão Invalidas!");
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}
	

}
