package ceilingfan;

public class CeilingFan {
	public static final int OFF = 0;
	public static final int LOW = 1;
	public static final int MEDIUM = 2;
	public static final int HIGH = 3;
	int state = OFF;
	
	public CeilingFan() {
		this.state = OFF;
	}

	public void off() {
		state = OFF;
	}
	
	public void low() {
		state = LOW;
	}
	
	public void medium() {
		state = MEDIUM;
	}
	
	public void high() {
		state = HIGH;
	}

	public void pull() {
        switch (state) {
        case OFF:
    		System.out.println("Celing fan is off");
            low();
            break;
        case LOW:
    		System.out.println("Celing fan is on low speed");
            medium();
            break;
        case MEDIUM:
    		System.out.println("Celing fan is on medium speed");
            high();
            break;
        case HIGH:
    		System.out.println("Celing fan is on high speed");
            off();
            break;
        default:
        	break;
        }
    }
}
