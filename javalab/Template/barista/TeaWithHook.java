package barista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends CaffeineBeverageWithHook {

	@Override
	protected void brew() {
		System.out.println("Stepping the Tea");
	}

	@Override
	protected void addCondiments() {
		System.out.println("Adding Lemon");
	}

	@Override
	protected boolean customerWantsCondiments() {
		String answer = getUserInput();
		if (answer.toLowerCase().startsWith("y")) {
			return true;
		}
		return false;
	}

	private String getUserInput() {
		String answer = null;
		System.out.print("Would you like lemon with your tea (y/n)? ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (answer == null) {
			return "n";
		}
		return answer;
	}

}
