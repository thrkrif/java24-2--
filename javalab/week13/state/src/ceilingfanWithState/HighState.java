package ceilingfanWithState;

public class HighState implements State {
	CeilingFanWithState fan;
	 
	public HighState(CeilingFanWithState fan) {
		this.fan = fan;
	}
	
	@Override
	public void pull(CeilingFanWithState fan) {
		System.out.println("Celing fan is on high speed");
		fan.setState(fan.getOffState());
	}

}
