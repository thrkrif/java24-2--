package computer;

public class ComputerBuilderMainTest {

	public ComputerBuilderMainTest() {
        Computer computer = new ComputerBuilder()
        						.name("Desktop Intel")
        						.cpu("i7-13700")
        						.hdd("256GB")
        						.ram("8GB")
        						.graphicsCard("Nvidia RTX 8000")
        						.isBluetoothEnabled(true)
        						.build();
        System.out.println(computer);
         
        computer = new ComputerBuilder()
				.name("Samsung Gallaxy Book 3 Pro")
				.cpu("i7-1360P")
				.hdd("512GB")
				.ram("16GB")
				.graphicsCard("Iris Xe")
				.isBluetoothEnabled(true)
				.build();
        System.out.println(computer);
	}

}
