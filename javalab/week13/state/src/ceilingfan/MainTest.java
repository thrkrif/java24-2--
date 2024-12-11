package ceilingfan;

import java.util.Scanner;

public class MainTest {
	static Scanner input = new Scanner(System.in);

	public static boolean getUserExitKey() {
    	System.out.print("Press q-key to exit or enter-key to continue to pull the CeilingFan: ");
		String s = input.nextLine();
        if (s.contentEquals("q")) {
            return true;
        }
        else {
            return false;
        }
    }
	
	public MainTest() {
		System.out.println("CeilingFan..");
		CeilingFan ceilingFan = new CeilingFan();
		while (!getUserExitKey()) {
			ceilingFan.pull();
		}
		System.out.println("done..");
	}
}
