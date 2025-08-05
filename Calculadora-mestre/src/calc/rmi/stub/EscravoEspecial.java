package calc.rmi.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EscravoEspecial  extends Remote {
	
	public boolean isOnline() throws RemoteException;
        
	public Double getRaiz(double a) throws RemoteException;

	public Double getPotenciacao(double a, double b) throws RemoteException;

}
