package cms.prugift.amitech.vn.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonHelper {
    public static Map<String, String> readTestData(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            Map<String, String> testData = objectMapper.readValue(file, new TypeReference<Map<String, String>>() {
            });
            return testData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void writeTestData(String filePath, Map<String, String> data) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(filePath);
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
