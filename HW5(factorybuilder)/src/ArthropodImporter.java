// 자바프로그래밍2 2분반 32207522 양상훈

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArthropodImporter {

    public static List<ArthropodClassifier> loadCSV(String filename) throws IOException {
        List<ArthropodClassifier> arthropodList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // 첫 줄은 헤더이므로 건너뛴다.

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // ArthropodType을 안전하게 파싱하고, 실패 시 UNKNOWN 타입으로 설정
                ArthropodType type = parseArthropodType(data[0]);

                Arthropod arthropod;

                // 만약 타입이 UNKNOWN이면 기본값으로 생성
                if (type == ArthropodType.UNKNOWN) {
                    arthropod = ArthropodFactory.create(type); // UNKNOWN일 때는 기본 값으로 생성
                } else {
                    // 정상적인 타입일 경우 데이터를 사용해 Arthropod 생성
                    int bodyRegions = parseInt(data[1]);
                    int pairsOfAntennae = parseInt(data[2]);
                    RespirationType respiration = parseRespirationType(data[3]);
                    MetamorphosisType metamorphosis = parseMetamorphosisType(data[4]);
                    int pairsOfWing = parseInt(data[5]);
                    int numberOfLegs = parseInt(data[6]);
                    // 배열의 길이가 7보다 작은경우 ArrayIndexOutOfBoundsException 발생한다.
                    // 이를 해결하기 위해 삼항 연산자 이용한다. 공백으로 처리한다.
                    // 그래야 ARACHNIDA,CRUSTACEA,DIPTERA 구분 가능
                    String distinction = data.length > 7 ? data[7] : "";

                    arthropod = ArthropodFactory.create(
                            bodyRegions,
                            pairsOfAntennae,
                            respiration,
                            metamorphosis,
                            pairsOfWing,
                            numberOfLegs,
                            distinction
                    );
                }

                // ArthropodClassifier에 추가
                arthropodList.add(new ArthropodClassifier(type, arthropod));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            throw e;
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            throw e;
        }

        return arthropodList;
    }

    // ArthropodType을 안전하게 파싱
    private static ArthropodType parseArthropodType(String value) {
        try {
            return ArthropodType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ArthropodType.UNKNOWN;
        }
    }

    // 문자열을 정수로 변환, 실패하면 "unknown" 반환
    private static int parseInt(String value) {
        if (value.equalsIgnoreCase("unknown")) {
            return -1; // 'unknown' 처리
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1; // 숫자 변환 실패 시 -1 반환
        }
    }

    // RespirationType을 문자열에서 변환, 실패하면 UNKNOWN 리턴
    private static RespirationType parseRespirationType(String value) {
        try {
            return RespirationType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return RespirationType.UNKNOWN;
        }
    }

    // MetamorphosisType을 문자열에서 변환, 실패하면 UNKNOWN 리턴
    private static MetamorphosisType parseMetamorphosisType(String value) {
        try {
            return MetamorphosisType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return MetamorphosisType.UNKNOWN;
        }
    }
}