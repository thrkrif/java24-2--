package gumballstate;
// context
public class GumballMachineTestDrive {

	public GumballMachineTestDrive() {
		GumballMachine gumballMachine = new GumballMachine(2);

		System.out.println("1:" + gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println("2:" + gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();

		System.out.println("3:" + gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
		
		System.out.println("4:" + gumballMachine);

		gumballMachine.refill(5);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println("5:" + gumballMachine);
	}
}
