package solution;

public class DailyHealthDataParseStrategy implements ParserStrategy<DailyHealthData>{

    // CSV 파일의 한 줄(line)을 받아서 ,를 기준으로 구분하고 구분된 값들을 DailyHealthData의 정보 갱신

    @Override
    public DailyHealthData parse(String line) {
        // line을 쉼표로 구분하여 데이터 추출
        // split의 반환값 String, 이후에 타입 캐스팅
        String[] values = line.split(",");
        String date = values[0];
        double bloodSugarLevel = Double.parseDouble(values[1]);
        double insulinDose = Double.parseDouble(values[2]);
        double carbsIntake = Double.parseDouble(values[3]);

        // DailyHealthData의 정보를 갱신
        return new DailyHealthData(date, bloodSugarLevel, insulinDose, carbsIntake);
    }

}
