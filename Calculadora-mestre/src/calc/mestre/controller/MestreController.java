package calc.mestre.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import calc.mestre.server.MestreServer;

public class MestreController  {
	
	private MestreServer masterServer;
	private ExecutorService executorService;

	
	public MestreController() {
		executorService = Executors.newCachedThreadPool();
	}
	
	public void iniciarServidor(int portaSocket, int portaRMI){
		masterServer = new MestreServer(portaSocket, portaRMI);
		executorService.execute(masterServer);
	}
	
	public void paraServidor() {
		masterServer.desligar();
		executorService.shutdownNow();
		System.exit(0);
		
	}
	
	public MestreServer getMasterServer() {
		return masterServer;
	}
	
	public void sair() {
		int answer = JOptionPane.showConfirmDialog(null, "Quer mesmo sair?", "Fechando o Conexão com segurança...",
				JOptionPane.YES_NO_OPTION);

		if (answer == JOptionPane.YES_OPTION) {
			paraServidor();
		}
		return;
		
	}
	
	
	
}
