package home;

public class CommandMainTest {

	public CommandMainTest() {
        // Invoker
                SimpleRemoteControl rc = new SimpleRemoteControl();

        // Receiver & Command
                Light lamp = new Light();
                Command lampOnCommand = new LightOnCommand(lamp);
                Command lampOffCommand = new LightOffCommand(lamp);

                rc.setCommand("lampOn", lampOnCommand);
                rc.setCommand("lampOff", lampOffCommand);
                
                Fan fan = new Fan();
                Command fanStartCommand = new FanStartCommand(fan);
                Command fanStopCommand = new FanStopCommand(fan);
                rc.setCommand("fanStart", fanStartCommand);
                rc.setCommand("fanStop", fanStopCommand);

                Television tv = new Television();
                Command tvOnCommand = new TelevisionOnCommand(tv);
                Command tvOffCommand = new TelevisionOffCommand(tv);
                rc.setCommand("tvOn", tvOnCommand);
                rc.setCommand("tvOff", tvOffCommand);

                rc.execute("lampOn");
                rc.execute("lampOff");
                rc.execute("fanStart");
                rc.execute("fanStop");
                rc.execute("tvOn");
                rc.undo("tvOn");   
	}

}
