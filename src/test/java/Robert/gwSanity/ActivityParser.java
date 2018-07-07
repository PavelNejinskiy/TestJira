package Robert.gwSanity;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Robert.gwSanity.SanityMy.*;

import static utils.skyfence.SSHVerification.getActivityLog;

public class ActivityParser  {
    private final String[] fieldNames = {USERNAME_KEY, DATA_OBJ_KEY, RECORD_KEY, ACTION_KEY, STATUS_KEY, LABEL_KEY, SERVICE_TYPE_KEY, FILE_TYPE_KEY};
    private final String[] sessionFields = {GET_SESSION_KEY, SET_SESSION_KEY};
    private Map<String, String> actualMap = new HashMap<>();
    private int countActivity;

    public ActivityParser() {
        String[] logLines = getActivityLog();
        for (String logLine : logLines) {
            parseLine(logLine);
        }
    }

    public int getCountActivity() {
        return countActivity;
    }

    public Map<String, String> getActualMap() {
        return actualMap;
    }

    private void parseLine(String logLine) {
        Matcher matcherField;
        Matcher matcherSession;

        if ((logLine.contains("MgmtInterface INFO") || logLine.contains("RuleEngine WARNING")) && logLine.contains("[Event Info: [EventType: Full]")) {
            countActivity++;
            for (String field : fieldNames) {
                matcherField = Pattern.compile("\\[" + field + ":\\s+([^(\\]]+)").matcher(logLine);
                if (matcherField.find()) {
                    actualMap.put(field, matcherField.group(1).trim());
                }
            }
        }

        if (logLine.contains(GET_SESSION_KEY) || logLine.contains(SET_SESSION_KEY)) {
            for (String field : sessionFields) {
                matcherSession = Pattern.compile(field + "\\s+=\\s+(.+)?\\s+and").matcher(logLine);

                if (matcherSession.find()) {
                    actualMap.put(field, matcherSession.group(1).trim());
                }
            }
        }
    }

}
