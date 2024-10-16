package shape;

public class ButtonFactory implements AbstractFactory<Button> {

	@Override
	public Button create(String type) {
		switch(type) {
		case "PUSHBUTTON":
			return new PushButton();         
		case "TOGGLEBUTTON":
			return new ToggleButton();	 
		default:
			return null;
		}
	}

}
