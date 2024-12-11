package ceilingfanWithoutState;

public class CeilingFan {
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	int state = OFF;
	
	public CeilingFan() {
		this.state = OFF;
	}

	public int getState() {
		return state;
	}

	public void high() {
		state = HIGH;
	}

	public void medium() {
		state = MEDIUM;
	}
	
	public void low() {
		state = LOW;
	}
	
	public void off() {
		state = OFF;
	}
	
	public void pull() {
        if (state == OFF) {
    		System.out.println("Celing fan is off");
            low();
        } else if (state == LOW) {
    		System.out.println("Celing fan is on low");
            medium();
        } else if (state == MEDIUM) {
    		System.out.println("Celing fan is on medium");
            high();
        } else if (state == HIGH) {
    		System.out.println("Celing fan is on high");
            off();
        }
    }
}
