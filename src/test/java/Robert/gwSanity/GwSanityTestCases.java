package Robert.gwSanity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.skyfence.SSHVerification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Robert.gwSanity.SanityMy.*;

import static Robert.gwSanity.TestData.*;

import static utils.skyfence.SSHVerification.startActivityLogging;

public class GwSanityTestCases {
    public static void compareActivityFieldWithAssertion(Map<String, String> expectedMap, Map<String, String> actual, int countActivity) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(countActivity <= 1, "Extra Activity");
        softAssert.assertTrue(countActivity != 0, "No Activity");
        if (countActivity == 1) {
            for (final String key : expectedMap.keySet()) {
                if (actual.containsKey(key)) {
                    String expectedValue = expectedMap.get(key);
                    String actualValue = actual.get(key);
                    softAssert.assertEquals(actualValue, expectedValue,
                            "Actual: " + actualValue + ", and Expected: " + expectedValue + " : different");
                } else {
                    softAssert.assertNull(key, "No data in Actual result, in field : " + key);
                }
            }
        }
        softAssert.assertAll();
    }

    @BeforeTest
    public void initPage() {
        SSHVerification.startDebugMod();
    }

    @BeforeMethod
    public void deleteLogs() {
        SSHVerification.deleteTempLogs();
        startActivityLogging();
    }

    @Test(description = "#1 url-prefix exact-match=\"true\", username, set session (type regex),activity info (status, action, protocol type)", priority = 1)
    public void loginRegex() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(USERNAME_KEY, USER_NAME_1);
        expectedMap.put(SET_SESSION_KEY, TOKEN_1);
        expectedMap.put(ACTION_KEY, ACTION_LOGIN);
        expectedMap.put(SERVICE_TYPE_KEY, PROTOCOL_TYPE);
        expectedMap.put(STATUS_KEY, STATUS_SUCCESS);

        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_1, new HashMap<String, String>() {{
            put("tk1", TOKEN_1);
        }}, BODY_TEST1, null);

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#2 url-prefix exact-match=\"false\",username, set session (type regex)", priority = 2)
    public void loginParams() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(USERNAME_KEY, USER_NAME_2);
        expectedMap.put(SET_SESSION_KEY, TOKEN_2);


        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_2, new HashMap<String, String>() {{
            put("tk2", TOKEN_2);
        }}, null, new ArrayList<NameValuePair>() {{
            add(new BasicNameValuePair("user2", USER_NAME_2));
        }});
        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#3 url-regex, saml login", priority = 3)
    public void successSAMLLogin() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(USERNAME_KEY, SAML_USER_NAME);


        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_3,
                null, SAML_BODY, null);
        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#4 Status-predicate code, saml login xpath", priority = 4)
    public void failureSAMLLogins() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(USERNAME_KEY, SAML_USER_NAME);


        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_4,
                null, SAML_BODY, null);

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#5 custom-predicate, get session type url-param, custom-data-object" +
            "amount, properties, target, message, data object ID,label, get session type URL_WEBSERVICE-param, parse-record (multiple), activity-record by NAME", priority = 5)
    public void newFields() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(LABEL_KEY, LABEL);
        expectedMap.put(USERNAME_KEY, USER_NAME_1);
        expectedMap.put(GET_SESSION_KEY, TOKEN_1);
        expectedMap.put(RECORD_KEY, "new name:" + ACTIVITY_RECORD_TEST_5);
        expectedMap.put(DATA_OBJ_KEY, DATA_OBJECT_TEST + "." + CUSTOM_DATA_OBJECT_VALUE);


        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_5, null, null, new ArrayList<NameValuePair>() {{
            add(new BasicNameValuePair("tk1", TOKEN_1));
            add(new BasicNameValuePair(CUSTOM_DATA_OBJECT, CUSTOM_DATA_OBJECT_VALUE));
            add(new BasicNameValuePair("recordName", "fileName1"));
            add(new BasicNameValuePair("record2", "YW1vdW50cmVjb3Jk"));
            add(new BasicNameValuePair("record3", "propertiesrecord@"));
            add(new BasicNameValuePair("record4", "dataobjectidrecord"));
            add(new BasicNameValuePair("record5", "messagerecord"));
            add(new BasicNameValuePair("customAction", "modify"));
            add(new BasicNameValuePair("Id", "bW9kaWZ5"));
        }});

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#6 custom-action, activity-record ID group, parse-record-name, get-session by cookie, http-method, set session by cookie", priority = 6)
    public void httpMethod() {
        Map<String, String> expectedMap = new HashMap<>();

        expectedMap.put(USERNAME_KEY, USER_NAME_1);
        expectedMap.put(GET_SESSION_KEY, TOKEN_1);
        expectedMap.put(SET_SESSION_KEY, TOKEN_3);
        expectedMap.put(ACTION_KEY, CUSTOM_ACTION);
        expectedMap.put(RECORD_KEY, ACTIVITY_RECORD_TEST_6);


        RequestApi.sendPutRequest(URL_WEBSERVICE + URN_TEST_6, new HashMap<String, String>() {{
            put("tk3", TOKEN_3);
            put("Cookie", " tk1=" + TOKEN_1);
        }}, BODY_TEST6);

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());

    }

    @Test(description = "#7 custom-predicate negate='true', get-session regex, modifiers: double lookup, decoder, Static activity info fields", priority = 7)
    public void modifiers() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(USERNAME_KEY, USER_NAME_1);
        expectedMap.put(GET_SESSION_KEY, TOKEN_1);
        expectedMap.put(RECORD_KEY, ACTIVITY_RECORD_TEST_7);


        RequestApi.sendPostRequest(URL_WEBSERVICE + URN_TEST_7, new HashMap<String, String>() {{
                    put("tk1", TOKEN_1);
                }}, BODY_TEST7, null
        );

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());

        System.out.println("Second Request\r\n");


        RequestApi.sendGetRequest(URL_WEBSERVICE + URN_TEST_7, new HashMap<String, String>() {{
            put("tk1", TOKEN_1);
            put(NEGATE_PREDICATE, "123");
        }});
        ActivityParser parser2 = new ActivityParser();

        new SoftAssert().assertTrue(parser2.getCountActivity() == 0, "Has Activity");
    }

    @Test(description = "#8 Feature: modifiers: decoder base64, regex-replacer, sesion rebind ", priority = 8)
    public void testBase64Decoder() {
        Map<String, String> expectedMap = new HashMap<>();

        expectedMap.put(USERNAME_KEY, USER_NAME_1);
        expectedMap.put(GET_SESSION_KEY, TOKEN_3);
        expectedMap.put(RECORD_KEY, USER_NAME_1 + ":" + TOKEN_3);


        RequestApi.sendGetRequest(URL_WEBSERVICE + URN_TEST_8,
                new HashMap<String, String>() {{
                    put("tk3", TOKEN_3);
                    put("Authorization", BASE64_HEADER);
                }});

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }

    @Test(description = "#9 Feature: get-session from token 2 ", priority = 9)
    public void secondSessionTest() {
        Map<String, String> expectedMap = new HashMap<>();

        expectedMap.put(USERNAME_KEY, USER_NAME_2);
        expectedMap.put(GET_SESSION_KEY, TOKEN_2);
        expectedMap.put(FILE_TYPE_KEY, FILE_TYPE);


        RequestApi.sendGetRequest(URL_WEBSERVICE + URN_TEST_9,
                new HashMap<String, String>() {{
                    put("tk2", TOKEN_2);
                }});

        ActivityParser parser = new ActivityParser();
        compareActivityFieldWithAssertion(expectedMap, parser.getActualMap(), parser.getCountActivity());
    }
}
