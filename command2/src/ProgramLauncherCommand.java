// 자바프로그래밍2 2분반 32207522 양상훈

import java.io.IOException;
import java.io.Serializable;

// Concrete Command
public class ProgramLauncherCommand implements IProgramLauncherCommand, Serializable {

    private String executable;
    private String icon;
    private transient Process process; // Receiver
    private String appName;  // yourcode : Mac 애플리케이션 이름 저장

    public ProgramLauncherCommand(String executable, String icon){
        this.executable = executable;
        this.icon = icon;
        
        // yourcode : Mac 애플리케이션 이름 추출
        this.appName = resolveAppNameForMac(executable);
    }

    // yourcode : Mac용 애플리케이션 이름을 설정하는 메서드
    private String resolveAppNameForMac(String executable) {
        switch (executable.toLowerCase()) {
            case "notepad": return "TextEdit";
            case "cmd /c start msedge": return "Safari";
            case "mspaint": return "Preview";
            default: return null;
        }
    }

    @Override
    public void execute(){
        try {
            // getProperty : 실행되는 곳의 정보를 가져오거나 운영체제의 정보가 필요할 때 이용
            String os = System.getProperty("os.name").toLowerCase(); // 운영체제 이름
            String resolvedExecutable = executable;

            // Mac일 경우 Windows 명령을 Mac 명령으로 변환
            if (os.contains("mac")) {
                resolvedExecutable = resolveExecutableForMac(executable);
            }

            ProcessBuilder pb = new ProcessBuilder(resolvedExecutable.split(" "));
            process = pb.start();
            System.out.println("Executed command: " + resolvedExecutable);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Mac에서 `killall` 명령을 사용해 애플리케이션을 종료
    @Override
    public void undo() {
        String os = System.getProperty("os.name").toLowerCase();
        
        // Mac일 경우 killall 명령을 사용해 애플리케이션 종료
        if (os.contains("mac") && appName != null) {
            try {
                System.out.println("Attempting to close application: " + appName);
                new ProcessBuilder("killall", appName).start();
                System.out.println("Application terminated: " + appName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (process != null && process.isAlive()) { 
            process.destroy(); 
            try {
                process.waitFor(); 
                System.out.println("Process terminated successfully.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Undo action: No process to terminate or already terminated.");
        }
    }

    // yourcode : commands.json이 윈도우에서는 실행이 잘 되지만 mac에서는 작동이 되지 않음
    // 이를 해결하기 위한 메서드
    private String resolveExecutableForMac(String executable) {
        switch (executable.toLowerCase()) {
            case "notepad": return "open -a TextEdit";
            case "cmd /c start msedge": return "open -a Safari";
            case "mspaint": return "open -a Preview";
            default: return executable;
        }
    }

    

    public String getExecutable() { return this.executable; }
    public void setExecutable(String executable) { this.executable = executable; }
    public String getIcon() { return this.icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Process getProcess() { return this.process; }
    public void setProcess(Process process) { this.process = process; }
}
