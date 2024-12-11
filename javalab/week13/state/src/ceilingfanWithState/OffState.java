package ceilingfanWithState;

public class OffState implements State {
	CeilingFanWithState fan;
	 
	public OffState(CeilingFanWithState fan) {
		this.fan = fan;
	}

	@Override
	public void pull(CeilingFanWithState fan) {
		System.out.println("Celing fan is off");
		fan.setState(fan.getLowState());
	}

}
