package calc.escravo.util;

import java.rmi.RemoteException;

import calc.rmi.stub.Mestre;

public class VerificaMestreOnline implements Runnable {

	
	public Mestre master;

	public VerificaMestreOnline(Mestre master) {
		this.master = master;
	}
	
	@Override
	public void run() {
		try {

			while (master.isOnline()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (RemoteException e) {
			System.out.println("Conexão perdida com o Servidor Master");

		}
		
	}

}
