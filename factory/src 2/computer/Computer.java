package computer;

public class Computer {
	// required
	private final String name;
	private final String cpu;
	private final String hdd;
	private final String ram;
	private final String graphicsCard;
	
	// optional
	private boolean isBluetoothEnabled;
	
	public Computer(String name, String cpu, String hdd, String ram, String graphicsCard, boolean isBluetoothEnabled) {
		this.name = name;
		this.cpu = cpu;
		this.hdd = hdd;
		this.ram = ram;
		this.graphicsCard = graphicsCard;
		this.isBluetoothEnabled = isBluetoothEnabled;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", cpu=" + cpu + ", hdd=" + hdd + ", ram=" + ram + ", graphicsCard="
				+ graphicsCard + ", isBluetoothEnabled=" + isBluetoothEnabled + "]";
	}
}
