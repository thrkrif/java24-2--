package ceilingfanWithState;

public class LowState implements State {
	CeilingFanWithState fan;
	 
	public LowState(CeilingFanWithState fan) {
		this.fan = fan;
	}
	
	@Override
	public void pull(CeilingFanWithState fan) {
		System.out.println("Celing fan is on low speed");
		fan.setState(fan.getMediumState());
	}

}
