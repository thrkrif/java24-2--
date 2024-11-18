// 자바프로그래밍 2분반 32207522 양상훈

public class ProgramLauncherCommand {
    private String name; // 프로그램 이름
    private String executable; // 실행할 명령어
    private String icon; // 아이콘의 경로 

    public ProgramLauncherCommand(String name, String executable, String icon) {
        this.name = name;
        this.executable = executable;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getExecutable() {
        return executable;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Executable: %s, Icon: %s", name, executable, icon);
    }
}

