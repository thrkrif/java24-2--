package gumball;

import java.rmi.*;
import java.rmi.registry.*; 

// client
public class GumballMonitorTestDrive {
 
	public static void main(String[] args) {
	    int locationLength = 0;
		String[] location = {"santafe/gumballmachine",
		                     "boulder/gumballmachine",
		                     "seattle/gumballmachine"}; 
		
		if (args.length > 0)
        {
            location = new String[1];
            location[0] = args[0] + "/gumballmachine";
            locationLength = 1;
        }
		else {
		    locationLength = 3;
		}
		GumballMonitor[] monitor = new GumballMonitor[location.length];
		
		for (int i=0;i < location.length; i++) {
			try {
			    Registry registry = LocateRegistry.getRegistry();
           		GumballMachineRemote machine = 
						(GumballMachineRemote) registry.lookup(location[i]);
           		monitor[i] = new GumballMonitor(machine);
				System.out.println(monitor[i]);
        	} catch (Exception e) {
            	e.printStackTrace();
        	}
		}
 
		for(int i=0; i < monitor.length; i++) {
			monitor[i].report();
		}
	}
}
