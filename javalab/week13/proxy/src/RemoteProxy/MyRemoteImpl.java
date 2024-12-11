package RemoteProxy;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

// server
public class MyRemoteImpl implements MyRemote {
    public String sayHello() {
        return "Server says, 'Hey'";
    }    

    public static void main(String[] args) {
        try {
            //Registry
            MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(new MyRemoteImpl(), 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("RemoteHello", stub);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}