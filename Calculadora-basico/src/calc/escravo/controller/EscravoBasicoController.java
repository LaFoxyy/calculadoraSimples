package calc.escravo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import calc.escravo.server.EscravoBasicoServer;

public class EscravoBasicoController {
	
	private ExecutorService executorService;
	private EscravoBasicoServer server;
	
	public EscravoBasicoController(){
        executorService = Executors.newFixedThreadPool(1);
    }

	public void sair() {
		int answer = JOptionPane.showConfirmDialog(null, "Quer mesmo sair?", "Fechando o Conexão com segurança...",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			desligarServidor();
			System.exit(0);
		}
		return;
	}
	

	public String iniciarServidor(String host, String porta) {
		server = new EscravoBasicoServer(host, Integer.parseInt(porta));
		executorService.execute(server);		
		return "Working!";
	}
	
	public EscravoBasicoServer getServer() {
		return server;
	}
	
	public String desligarServidor() {
        executorService.shutdown();
        server = null;
        return "Shuting!";
    }
	
}