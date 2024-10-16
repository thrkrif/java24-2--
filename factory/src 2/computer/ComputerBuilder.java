package computer;

public class ComputerBuilder {
	private String name;
	private String cpu;
	private String hdd;
	private String ram;
	private String graphicsCard;
	private boolean isBluetoothEnabled;

	public ComputerBuilder name(String name) {
		this.name = name;
		return this;
	}
	public ComputerBuilder cpu(String cpu) {
		this.cpu = cpu;
		return this;
	}
	public ComputerBuilder hdd(String hdd) {
		this.hdd = hdd;
		return this;
	}
    public ComputerBuilder ram(String ram) {
    	this.ram = ram;
    	return this;
    }
    public ComputerBuilder graphicsCard(String graphicsCard) {
    	this.graphicsCard = graphicsCard;
    	return this;
    }
    public ComputerBuilder isBluetoothEnabled(boolean isBluetoothEnabled) {
    	this.isBluetoothEnabled = isBluetoothEnabled;
    	return this;
    }
    
    public Computer build() {
    	return new Computer(name, cpu, hdd, ram, graphicsCard, isBluetoothEnabled);
    }
}
