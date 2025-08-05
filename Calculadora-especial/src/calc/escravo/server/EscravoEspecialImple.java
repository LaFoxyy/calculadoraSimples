package calc.escravo.server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import calc.rmi.stub.Mestre;
import calc.rmi.stub.EscravoEspecial;

public class EscravoEspecialImple extends UnicastRemoteObject implements EscravoEspecial{

	private static final long serialVersionUID = 7741187623911762209L;

	private ExecutorService executorService;
	
	public EscravoEspecialImple() throws RemoteException {
		super();
		executorService = Executors.newFixedThreadPool(1);
	}

	public void VerificaMasterOnline(Mestre master) {
		executorService.execute(new calc.escravo.util.VerificaMestreOnline(master));
	}	

	@Override
	public boolean isOnline() throws RemoteException {
		return true;
	}
	
	@Override
	public Double getPotenciacao(double a, double b) throws RemoteException {
		Double resposta;
		resposta = new BigDecimal(a).pow((int) b).doubleValue();
		System.out.println("POTENCIAÇÃO: " + a + " elevado à " + b + " = " + resposta);
		return resposta;
	}


	@Override
	public Double getRaiz(double a) throws RemoteException {
		Double resposta;
		resposta = new BigDecimal(Math.sqrt(a)) .doubleValue();
		System.out.println("RAIZ QUADRADA: Raiz quadrada de " + a + " = " + resposta);
		return resposta;
	}





}
