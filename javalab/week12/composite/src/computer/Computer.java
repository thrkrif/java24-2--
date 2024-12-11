package computer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Computer extends ComputerDevice {
	private List<ComputerDevice> components = new ArrayList<>();

	public List<ComputerDevice> getComponents() {
		return components;
	}

	public void addComponent(ComputerDevice component) {
		this.components.add(component);
	}

	public void removeComponent(ComputerDevice component) {
		this.components.remove(component);
	}

	public int getPrice() {
		return components.stream().collect(Collectors.summingInt(e -> e.getPrice()));
	}

	public int getPower() {
		return components.stream().collect(Collectors.summingInt(e -> e.getPower()));
	}
}
