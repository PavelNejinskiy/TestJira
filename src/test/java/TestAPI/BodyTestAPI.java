package TestAPI;

/*
1. Во всех запросах Content-Type: application/json
2. Получение списка пользователя было реализовано по GET на хост
3. Сохранение пользователя было реализовано методом PUT на на хост + '/' + id (где id - id редактируемого человека)
4. Создание нового пользователя было реализовано методом POST на winow.crudURL, в ответ приходил id новой созданной сущности
5. При сохранении администратора надо было делать GET на /refreshAdmins на том же хосте где был хост
6. При создании администратора или помощника надо было передать в поле role имя роли, для студента было не надо
7. Для удаления пользователя необходимо отправить DELETE на хост + '/' + id (где id - id удаляемого человека)
8. Сервер должен запускаться на порту 20007
9. Во всех ответах должен присутстовать заголовок Content-Type application/json
10. Если клиент передает некорректный заголовок Content-Type либо он отсутствует - возвращать 401 ошибку
11. Начальное состояние сервера (при перезапуске):
{ id: '1', name: 'Illya Klymov', phone: '+380504020799', role: 'Administrator' },
{ id: '2', name: 'Ivanov Ivan', phone: '+380670000002', role: 'Student', strikes: 1 },
{ id: '3', name: 'Petrov Petr', phone: '+380670000001', role: 'Support', location: 'Kiev' }
12. Если приходит некорректное поле role (смотри условие задания №4) - возвращать 401 ошибку
13. Если происходит попытка модификации или удаления несуществующего id - сервер должен возвращать 404 ошибку
где host все запросы начинаются с /api/users
 */

import org.apache.http.HttpEntity;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.xmlbeans.impl.piccolo.xml.Entity;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BodyTestAPI {

    ToolsAPI tools = new ToolsAPI();
      String url = "https://192.168.1.79:20007";
    //String url = "https://www.facebook.com/linda.colombrita.37?fref=grp_mmbr_list";
    HttpPost httpPost;

    // test 1 and 9 tasks
    public void contentType() throws IOException {
       // String json = "details={\"id\":\"3\",\"name\":\"Petrov Petr\",\"phone\":\"+380670000001\", \"role\":\"Support\", \"location\":\"Kiev\"}";

        HttpEntity entity = tools.getRespons(url).getEntity();
        System.out.println(entity.getContentType().getName());

        String json = EntityUtils.toString(tools.getRespons(url).getEntity());
        System.out.println(json);

        //Assert.assertEquals(tools.getRespons(url).getHeaders("content-type"), "application/json");
        Assert.assertEquals(entity.getContentType().getName(), "Content-Type");

    }

    public void returnErrorContentType() throws IOException {

        String statusCode = new Integer(tools.getRespons(url).getStatusLine().getStatusCode()).toString();

      Assert.assertEquals(statusCode, "401");
    }

    public void getUser() throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(tools.getHttpGet(url).getEntity().getContent()));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

            //  UsernamePasswordCredentials creds = new UsernamePasswordCredentials("user", "pwd");

    }

    public void saveUserUsePUT() {
    }

    public void createNewUserPOST() {
    }

    public void saveAdministrator() {
    }

    public void createAdministrator() {
    }

    public void deleteUser() {
    }

    public void serverPort() {
    }


    public void defaultCondition() {
    }

    public void role() {
    }

    public void deleteOrModification() {
    }
}
