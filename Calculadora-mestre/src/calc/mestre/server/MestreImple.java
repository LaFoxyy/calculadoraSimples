package calc.mestre.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import calc.rmi.stub.Mestre;
import calc.rmi.stub.EscravoBasico;
import calc.rmi.stub.EscravoEspecial;

public class MestreImple extends UnicastRemoteObject implements Mestre {
	
	private ConcurrentLinkedQueue<EscravoBasico> basicos = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<EscravoEspecial> especiais = new ConcurrentLinkedQueue<>();
    
	private ExecutorService executorBasico;
	private ExecutorService executorEspecial;

	public MestreImple() throws RemoteException {
		super();
		executorBasico = Executors.newCachedThreadPool();
		executorEspecial = Executors.newCachedThreadPool();
	}

	private static final long serialVersionUID = 7081056333661626357L;

	@Override
	public boolean isOnline() throws RemoteException {
		return true;
	}

	@Override
	public boolean registraEscravo(EscravoBasico slaveBasico) throws RemoteException {
		basicos.add(slaveBasico);
		System.out.println("NOVO SLAVE BASICO CONECTADO | slaves basicos disponíveis: " + basicos.size());
		executorBasico.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (slaveBasico.isOnline()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				} catch (RemoteException e) {
					basicos.remove(slaveBasico);
					System.out.println("SLAVE BASICO DESCONECTADO | slaves basicos Disponíveis: " + basicos.size());
				}
				
			}
		});
		return true;
	}

	@Override
	public boolean registraEscravo(EscravoEspecial slaveEspecial) throws RemoteException {
		especiais.add(slaveEspecial);
		System.out.println("NOVO SLAVE ESPECIAL CONECTADO | slaves especiais disponíveis: " + especiais.size());
		executorEspecial.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (slaveEspecial.isOnline()) {
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				} catch (RemoteException e) {
					especiais.remove(slaveEspecial);
					System.out.println("SLAVE ESPECIAL DESCONECTADO | slaves especiais Disponíveis: " + especiais.size());
				}
			}
		});
		return true;
	}
	
	public void  shutdown() {
		executorBasico.shutdown();
		executorEspecial.shutdown();
	}
	
	public ConcurrentLinkedQueue<EscravoBasico> getBasicos() {
		return basicos;
	}
	
	public ConcurrentLinkedQueue<EscravoEspecial> getEspeciais() {
		return especiais;
	}
}

