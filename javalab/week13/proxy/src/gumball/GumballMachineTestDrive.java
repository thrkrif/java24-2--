package gumball;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

// server
public class GumballMachineTestDrive {
 
	public static void main(String[] args) {
		GumballMachineRemote gumballMachine = null;
		int count;

		if (args.length < 2) {
			System.out.println("GumballMachine <name> <inventory>");
 			System.exit(1);
		}

		try {
			count = Integer.parseInt(args[1]);
			gumballMachine = new GumballMachine(args[0], count);
			GumballMachineRemote stub = (GumballMachineRemote) UnicastRemoteObject.exportObject(gumballMachine, 0);
			//Registry registry = LocateRegistry.getRegistry();
			//registry.rebind(args[0] + "/gumballmachine", stub);
			Naming.rebind(args[0] + "/gumballmachine", stub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
