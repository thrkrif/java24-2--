package RemoteProxy;

import java.rmi.registry.*;

// Client
public class MyRemoteClient {
    public static void main (String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            // proxy
            MyRemote stub = (MyRemote) registry.lookup("RemoteHello");
            System.out.println(stub.sayHello());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}