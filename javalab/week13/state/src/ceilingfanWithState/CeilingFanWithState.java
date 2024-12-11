package ceilingfanWithState;

public class CeilingFanWithState {
	State offState;
	State lowState;
	State mediumState;
	State highState;
	State state = null;
	
	public CeilingFanWithState() {
		this.offState = new OffState(this);
		this.lowState = new LowState(this);
		this.mediumState = new MediumState(this);
		this.highState = new HighState(this);
		this.state = this.offState;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getOffState() {
		return this.offState;
	}

	public State getLowState() {
		return this.lowState;
	}

	public State getMediumState() {
		return this.mediumState;
	}

	public State getHighState() {
		return this.highState;
	}

	public void pull() {
		this.state.pull(this);
    }
}
