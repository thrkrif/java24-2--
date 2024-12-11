package ceilingfanWithState;

public class MediumState implements State {
	CeilingFanWithState fan;
	 
	public MediumState(CeilingFanWithState fan) {
		this.fan = fan;
	}

	@Override
	public void pull(CeilingFanWithState fan) {
		System.out.println("Celing fan is on medium speed");
		fan.setState(fan.getHighState());
	}

}
