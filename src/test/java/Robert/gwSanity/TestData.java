package Robert.gwSanity;//package gwSanity;

import java.util.Base64;
import java.util.Calendar;

public interface TestData {

    // SF
    String hostName = "192.168.3.170";
    String userNameConsole = "root";
    String passwordConsole = "Barbapapa1@";
    String PROXY = "." + System.getProperty("sf.gwIp") + ".local";

    //url addresses
    String URL_WEBSERVICE = "http://app2.aobertas.local";
    String URN_TEST_1 = "/login_prefix";
    String URN_TEST_2 = "/login_prefix_match_false";
    String URN_TEST_3 = "/saml_logins";
    String URN_TEST_4 = "/saml_login_xpath";
    String URN_TEST_5 = "/newFields";
    String URN_TEST_6 = "/httpMethod";
    String URN_TEST_7 = "/modifiers";
    String URN_TEST_8 = "/base64Decoder";
    String URN_TEST_9 = "/testpdf";
    //Username
    String timeStamp = Long.toString(Calendar.getInstance().getTime().getTime());

    String USER_NAME_1 = "user" + timeStamp + "@test.com";
    String USER_NAME_2 = "user" + timeStamp + "@test.com";
    String SAML_USER_NAME = "samluser@test.com";

    String TOKEN_1 = "TOKEN1" + timeStamp;
    String TOKEN_2 = "TOKEN2" + timeStamp + 1;
    String TOKEN_3 = "TOKEN3" + timeStamp + 2;

    //Activity Data
    String LABEL = "<TestLabel>";
    String PROTOCOL_TYPE = "Mapping";
    String ACTION_LOGIN = "login";
    String STATUS_SUCCESS = "success";
    String DATA_OBJECT_TEST = "Items";
    String ACTIVITY_RECORD_TEST_5 = "fileName1";
    String ACTIVITY_RECORD_TEST_6 = "qwe%40.txt";
    String ACTIVITY_RECORD_TEST_7 = "ewq@.xml";
    String CUSTOM_DATA_OBJECT_VALUE = "customName";
    String CUSTOM_DATA_OBJECT = "customDataObject";
    String CUSTOM_ACTION = "modify";
    String CUSTOM_ACTION_ID = "bW9kaWZ5";
    String FILE_TYPE = "[FileTypes: application/pdf";
    //new fields
    String TARGET = "QWE";
    String AMOUT = "amountrecord";
    String PROPERIES = "propertiesrecord@";
    String DATA_OBJECT_ID = "propertiesrecord@";
    //Responce Data
    String RESP_TOKEN_FOR_PARSE2 = "IkZpbGVuYW1lIjoiZXdxLnhtbCIsICJmaWxldmVyc2lvbiI";
    String RESP_TOKEN_FOR_PARSE3 = "DQogICAgaGVhZGVyczoNCiAgICAgICAgQ29va2llOiB0b";

    //ForRequest data
    String BODY_TEST1 = "\"user1\": \"" + USER_NAME_1 + "\"";
    String BODY_TEST6 = "parsedId=" + RESP_TOKEN_FOR_PARSE2 + "&customAction=" + CUSTOM_ACTION_ID;
    String BODY_TEST7 = "doublelookup=" + RESP_TOKEN_FOR_PARSE3;
    String BASE64_HEADER = new String(Base64.getEncoder().encode((USER_NAME_1 + ":" + TOKEN_3).getBytes()));
    String NEGATE_PREDICATE = "negatePred";

    String SAML_BODY = "SAMLResponse=PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbDJwOlJlc3BvbnNlIHhtbG5zOnNhbWwycD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly8xOTIuMTY4LjMuMjQ5L3Nzby9zYW1sIiBJRD0iaWQyNDkxNjY0ODQyOTE2NjAyMjcwNDI1IiBJc3N1ZUluc3RhbnQ9IjIwMTgtMDQtMDVUMTU6MTM6MDYuODgzWiIgVmVyc2lvbj0iMi4wIj48c2FtbDI6SXNzdWVyIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIiBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQtZm9ybWF0OmVudGl0eSI%2BaHR0cDovL3d3dy5va3RhLmNvbS9leGt2ZzAwZThoTXBaUjRoeDJwNjwvc2FtbDI6SXNzdWVyPjxkczpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMiPjxkczpTaWduZWRJbmZvPjxkczpDYW5vbmljYWxpemF0aW9uTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8%2BPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMDQveG1sZHNpZy1tb3JlI3JzYS1zaGEyNTYiLz48ZHM6UmVmZXJlbmNlIFVSST0iI2lkMjQ5MTY2NDg0MjkxMjM0MDEwMjI3MDQyNSI%2BPGRzOlRyYW5zZm9ybXM%2BPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIi8%2BPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjwvZHM6VHJhbnNmb3Jtcz48ZHM6RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxlbmMjc2hhMjU2Ii8%2BPGRzOkRpZ2VzdFZhbHVlPjhNSjcxSWpCOW9nZTNla3RBWGZmUThkSmhlZWpGV1FWZTM1djAwTUlwanc9PC9kczpEaWdlc3RWYWx1ZT48L2RzOlJlZmVyZW5jZT48L2RzOlNpZ25lZEluZm8%2BPGRzOlNpZ25hdHVyZVZhbHVlPlZjSGUzSGRKWm5qN2xVN01GTmFISk5PM0hOTENKWFBXb0k4aTFNb1BmNW8wZ1RZU0g5QVYvaCtjcW5PU1gvTXpPUDg1TXBvS2g1cVBYVVU4Q01OeEhxZTZyYkFpV01ISmF4Mm5VOHIzMVhuV2FleXBpRU9RTU1kN21wWFkxYkt0eEJSeDhjOG9PTk9CZ0k4enJiUjdDVGY2L25weVQwYklNRDMxTStpYWtSUUQ5cU8wTkR6cEJpOTRiWFRVbW90SVc3aFNzYU5iS1NTa3J6UWw4b2dGQUdtWnhQNWQ0NFhtTHEwZWVtdUZvMkJqNkxJSjRiZ1BYUk9uUHhxOGxNWTgweVJuNzFVa3J0RnZqM083UmVuWHhBSUxTMFBjVytra1dPaHdpNVRHeGFtN1AwL1FOT1lZNWtCMUdBUW5rYXZ1ME1XRzdmSW1SLy9XWUIvSFZqMG1OQT09PC9kczpTaWduYXR1cmVWYWx1ZT48ZHM6S2V5SW5mbz48ZHM6WDUwOURhdGE%2BPGRzOlg1MDlDZXJ0aWZpY2F0ZT5NSUlEcURDQ0FwQ2dBd0lCQWdJR0FXSmN6RVBITUEwR0NTcUdTSWIzRFFFQkN3VUFNSUdVTVFzd0NRWURWUVFHRXdKVlV6RVRNQkVHDQpBMVVFQ0F3S1EyRnNhV1p2Y201cFlURVdNQlFHQTFVRUJ3d05VMkZ1SUVaeVlXNWphWE5qYnpFTk1Bc0dBMVVFQ2d3RVQydDBZVEVVDQpNQklHQTFVRUN3d0xVMU5QVUhKdmRtbGtaWEl4RlRBVEJnTlZCQU1NREc1aGRtVnlMV2x0YW1WMGVURWNNQm9HQ1NxR1NJYjNEUUVKDQpBUllOYVc1bWIwQnZhM1JoTG1OdmJUQWVGdzB4T0RBek1qVXhNRFUwTVROYUZ3MHlPREF6TWpVeE1EVTFNVEphTUlHVU1Rc3dDUVlEDQpWUVFHRXdKVlV6RVRNQkVHQTFVRUNBd0tRMkZzYVdadmNtNXBZVEVXTUJRR0ExVUVCd3dOVTJGdUlFWnlZVzVqYVhOamJ6RU5NQXNHDQpBMVVFQ2d3RVQydDBZVEVVTUJJR0ExVUVDd3dMVTFOUFVISnZkbWxrWlhJeEZUQVRCZ05WQkFNTURHNWhkbVZ5TFdsdGFtVjBlVEVjDQpNQm9HQ1NxR1NJYjNEUUVKQVJZTmFXNW1iMEJ2YTNSaExtTnZiVENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DDQpnZ0VCQUozeTUwZGlwRk92TjUvVXlQR2FFQWFHOVJsMERvV1UvMDRvVEh5M3ZBQWhyQlZnUDRROXNXSG5rTC94ZFAyb2pLNTdhaWNCDQoyQTRNWkoyaWJqRElpVkgvSzBDS2J5NkNtUWs2ZHQ4bzQ0L3pnWnZ3STdMd3E0WnFqYis5aCtKZWRHd01JTFVKM0tIYlo5d2xJVjg4DQprZDVTbVU3Tm5tTGRWNHlKb3ZrSVlUNGUrNUF1RlRxZVlaM2NSTUpUTGU1STBZQUk5cXB2SjZpTDZ1dmwxaE9vMkQwZjhqalFQNWtkDQpQa1hBYmVpVHRzcVlNS2t4clRZa3NyVTlQWDRqUXdyaVJ2ZFpteHBEVEtlRkdoS0VzNFNaeTNVMVkvQUdydjJLYW1nNFFqWmRxQnFaDQpqcGJSUVFXSWhEdXFTbFBpb1RWRG5UbkNMUmk3Zkw4Y3FJeUdWbVhCbWdFQ0F3RUFBVEFOQmdrcWhraUc5dzBCQVFzRkFBT0NBUUVBDQpsT2dGNkVFVDBhMXZ3UVFxdXBHN2lVYlppaE1oeHZlY244SEdOQ1JEcXhpelh6bllQaXQzWHJ5L1FWb3N0VEVsT3hxSTVEN0xIYU5sDQpsQ1ZhY3pra3NOb0xSS0VNZ1d1NmpsUlpQZ1FVdEk0aGFxVWprRGRIYVV6bUZWczJVWE5TZ2Yybkc5cGg3RUV4K0lVYzFpSVVveWhVDQpxdFhaUGJ1d2w0TStCU1FLWDhsdlQyYjZGYWRGL28wUHA4ZS9vUjdNcE5WV1NKU0JIYnBxZE1UY0VkQXJRekJibXBJRmRVRHBueDVJDQpmaGNsVDhvMjJTaGpKQzI4QXJFd2YvQnhNejJRakxsY0hycExUNG5oOHJxYjVJSEpwT3dNbDBtMVFqam82Sjg1TEJkdm9JZCsxVGd1DQpMdGwzYllqbDF3R1hNWE9oZTIrK0g3SXQ3UjhWTnFZbllFOUowQT09PC9kczpYNTA5Q2VydGlmaWNhdGU%2BPC9kczpYNTA5RGF0YT48L2RzOktleUluZm8%2BPC9kczpTaWduYXR1cmU%2BPHNhbWwycDpTdGF0dXMgeG1sbnM6c2FtbDJwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPjxzYW1sMnA6U3RhdHVzQ29kZSBWYWx1ZT0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnN0YXR1czpTdWNjZXNzIi8%2BPC9zYW1sMnA6U3RhdHVzPjxzYW1sMjpBc3NlcnRpb24geG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iIElEPSJpZDI0OTE2NjQ4NDI5NDAwOTM1MDE0OTU5MzgiIElzc3VlSW5zdGFudD0iMjAxOC0wNC0wNVQxNToxMzowNi44ODNaIiBWZXJzaW9uPSIyLjAiPjxzYW1sMjpJc3N1ZXIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDplbnRpdHkiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj5odHRwOi8vd3d3Lm9rdGEuY29tL2V4a3ZnMDBlOGhNcFpSNGh4MnA2PC9zYW1sMjpJc3N1ZXI%2BPGRzOlNpZ25hdHVyZSB4bWxuczpkcz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI%2BPGRzOlNpZ25lZEluZm8%2BPGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48ZHM6U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxkc2lnLW1vcmUjcnNhLXNoYTI1NiIvPjxkczpSZWZlcmVuY2UgVVJJPSIjaWQyNDkxNjY0ODQyOTQwMDkzNTAxNDk1OTM4Ij48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8%2BPC9kczpUcmFuc2Zvcm1zPjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz48ZHM6RGlnZXN0VmFsdWU%2BeXFnUFNGSXJQKzlNc3lsbURDYU91aDI0MTMzV1kxdmVrTDlFSGNWVVlpST08L2RzOkRpZ2VzdFZhbHVlPjwvZHM6UmVmZXJlbmNlPjwvZHM6U2lnbmVkSW5mbz48ZHM6U2lnbmF0dXJlVmFsdWU%2BbWMwL3JTUDZ1WXpaRnFjbjJiaXl0Sm5ZWGp3akpwZmh4eDNlazZUTzZEeE02TUx2UWpBbktkbkpJMU5ma1F0aFNyVzBpOVdtb3Z3bndUVXlWSlFXNFBJSkNtbXhDMkVUMFcraWxNbkpYV08xUUhrQ2hpdE9OOEZGOStNejNsUHVUMjhOTEYybnlDU0RUMi9ncktlYjRkWjgxOVowNGFwM0crK3hpSm9DYVZTN1NFdjl1V1RwOWNvdE5leWNQaUVhaTBNbGxOVXNBOTIyWi93cUZqUHNydFRoUkRNU2JyUTdxUEdVTGh4aS9xNnl4SDVBVjA0ZlB4bWJLZng0Nld3dlhMTWh0OEdKUU5ZOE12MVFrNFVmU3V5MU1kQytmeHVEVGJUY1oyUVZIS1J2Y29HbFA2ejI0cHBFK2w0Ym9NM3dBcVpYdmpLaXI3UFBoUlVJNmY5UlhRPT08L2RzOlNpZ25hdHVyZVZhbHVlPjxkczpLZXlJbmZvPjxkczpYNTA5RGF0YT48ZHM6WDUwOUNlcnRpZmljYXRlPk1JSURxRENDQXBDZ0F3SUJBZ0lHQVdKY3pFUEhNQTBHQ1NxR1NJYjNEUUVCQ3dVQU1JR1VNUXN3Q1FZRFZRUUdFd0pWVXpFVE1CRUcNCkExVUVDQXdLUTJGc2FXWnZjbTVwWVRFV01CUUdBMVVFQnd3TlUyRnVJRVp5WVc1amFYTmpiekVOTUFzR0ExVUVDZ3dFVDJ0MFlURVUNCk1CSUdBMVVFQ3d3TFUxTlBVSEp2ZG1sa1pYSXhGVEFUQmdOVkJBTU1ERzVoZG1WeUxXbHRhbVYwZVRFY01Cb0dDU3FHU0liM0RRRUoNCkFSWU5hVzVtYjBCdmEzUmhMbU52YlRBZUZ3MHhPREF6TWpVeE1EVTBNVE5hRncweU9EQXpNalV4TURVMU1USmFNSUdVTVFzd0NRWUQNClZRUUdFd0pWVXpFVE1CRUdBMVVFQ0F3S1EyRnNhV1p2Y201cFlURVdNQlFHQTFVRUJ3d05VMkZ1SUVaeVlXNWphWE5qYnpFTk1Bc0cNCkExVUVDZ3dFVDJ0MFlURVVNQklHQTFVRUN3d0xVMU5QVUhKdmRtbGtaWEl4RlRBVEJnTlZCQU1NREc1aGRtVnlMV2x0YW1WMGVURWMNCk1Cb0dDU3FHU0liM0RRRUpBUllOYVc1bWIwQnZhM1JoTG1OdmJUQ0NBU0l3RFFZSktvWklodmNOQVFFQkJRQURnZ0VQQURDQ0FRb0MNCmdnRUJBSjN5NTBkaXBGT3ZONS9VeVBHYUVBYUc5UmwwRG9XVS8wNG9USHkzdkFBaHJCVmdQNFE5c1dIbmtML3hkUDJvaks1N2FpY0INCjJBNE1aSjJpYmpESWlWSC9LMENLYnk2Q21RazZkdDhvNDQvemdadndJN0x3cTRacWpiKzloK0plZEd3TUlMVUozS0hiWjl3bElWODgNCmtkNVNtVTdObm1MZFY0eUpvdmtJWVQ0ZSs1QXVGVHFlWVozY1JNSlRMZTVJMFlBSTlxcHZKNmlMNnV2bDFoT28yRDBmOGpqUVA1a2QNClBrWEFiZWlUdHNxWU1La3hyVFlrc3JVOVBYNGpRd3JpUnZkWm14cERUS2VGR2hLRXM0U1p5M1UxWS9BR3J2MkthbWc0UWpaZHFCcVoNCmpwYlJRUVdJaER1cVNsUGlvVFZEblRuQ0xSaTdmTDhjcUl5R1ZtWEJtZ0VDQXdFQUFUQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUENCmxPZ0Y2RUVUMGExdndRUXF1cEc3aVViWmloTWh4dmVjbjhIR05DUkRxeGl6WHpuWVBpdDNYcnkvUVZvc3RURWxPeHFJNUQ3TEhhTmwNCmxDVmFjemtrc05vTFJLRU1nV3U2amxSWlBnUVV0STRoYXFVamtEZEhhVXptRlZzMlVYTlNnZjJuRzlwaDdFRXgrSVVjMWlJVW95aFUNCnF0WFpQYnV3bDRNK0JTUUtYOGx2VDJiNkZhZEYvbzBQcDhlL29SN01wTlZXU0pTQkhicHFkTVRjRWRBclF6QmJtcElGZFVEcG54NUkNCmZoY2xUOG8yMlNoakpDMjhBckV3Zi9CeE16MlFqTGxjSHJwTFQ0bmg4cnFiNUlISnBPd01sMG0xUWpqbzZKODVMQmR2b0lkKzFUZ3UNCkx0bDNiWWpsMXdHWE1YT2hlMisrSDdJdDdSOFZOcVluWUU5SjBBPT08L2RzOlg1MDlDZXJ0aWZpY2F0ZT48L2RzOlg1MDlEYXRhPjwvZHM6S2V5SW5mbz48L2RzOlNpZ25hdHVyZT48c2FtbDI6U3ViamVjdCB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI%2BPHNhbWwyOk5hbWVJRCBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjEuMTpuYW1laWQtZm9ybWF0OnVuc3BlY2lmaWVkIj5zYW1sdXNlckB0ZXN0LmNvbTwvc2FtbDI6TmFtZUlEPjxzYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uIE1ldGhvZD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmNtOmJlYXJlciI%2BPHNhbWwyOlN1YmplY3RDb25maXJtYXRpb25EYXRhIE5vdE9uT3JBZnRlcj0iMjAxOC0wNC0wNVQxNToxODowNi44ODNaIiBSZWNpcGllbnQ9Imh0dHBzOi8vMTkyLjE2OC4zLjI0OS9zc28vc2FtbCIvPjwvc2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbj48L3NhbWwyOlN1YmplY3Q%2BPHNhbWwyOkNvbmRpdGlvbnMgTm90QmVmb3JlPSIyMDE4LTA0LTA1VDE1OjA4OjA2Ljg4M1oiIE5vdE9uT3JBZnRlcj0iMjAxOC0wNC0wNVQxNToxODowNi44ODNaIiB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI%2BPHNhbWwyOkF1ZGllbmNlUmVzdHJpY3Rpb24%2BPHNhbWwyOkF1ZGllbmNlPmh0dHBzOi8vMTkyLjE2OC4zLjI0OS9zc28vc2FtbDwvc2FtbDI6QXVkaWVuY2U%2BPC9zYW1sMjpBdWRpZW5jZVJlc3RyaWN0aW9uPjwvc2FtbDI6Q29uZGl0aW9ucz48c2FtbDI6QXV0aG5TdGF0ZW1lbnQgQXV0aG5JbnN0YW50PSIyMDE4LTA0LTA1VDE0OjU1OjE4LjE0M1oiIFNlc3Npb25JbmRleD0iaWQxNTIyOTQxMTg2ODgzLjE5OTc1NjA0NzkiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6QXV0aG5Db250ZXh0PjxzYW1sMjpBdXRobkNvbnRleHRDbGFzc1JlZj51cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YWM6Y2xhc3NlczpQYXNzd29yZFByb3RlY3RlZFRyYW5zcG9ydDwvc2FtbDI6QXV0aG5Db250ZXh0Q2xhc3NSZWY%2BPC9zYW1sMjpBdXRobkNvbnRleHQ%2BPC9zYW1sMjpBdXRoblN0YXRlbWVudD48L3NhbWwyOkFzc2VydGlvbj48L3NhbWwycDpSZXNwb25zZT4%3D";
}
