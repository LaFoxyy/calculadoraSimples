package calc.rmi.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mestre extends Remote {
	
	public boolean isOnline() throws RemoteException;
	
	public boolean registraEscravo(EscravoBasico slaveBasico) throws RemoteException;
	
	public boolean registraEscravo(EscravoEspecial slaveEspecial) throws RemoteException;
	
}
