package dashboard.model;

import java.util.HashMap;
import java.util.Map;

public class KeyIndicator {
    private HashMap<String, Integer> kpiValues = new HashMap<>();

    public KeyIndicator(HashMap<String, Integer> kpiValues) {
        for(Map.Entry<String, Integer> entry : kpiValues.entrySet()) {
            this.kpiValues.put(entry.getKey(), entry.getValue());
        }
    }

    public Integer get(String key) {
        return kpiValues.get(key);
    }

}
