package TestAPI;

import org.testng.annotations.Test;

import java.io.IOException;

public class TestAPI {

    BodyTestAPI bod = new BodyTestAPI();

    @Test(priority = -2, groups = "positiveApi")
        public void testServerPort(){
        bod.serverPort();
    }

    @Test(priority = -1, groups = "positiveApi")
    public void testContentType() throws IOException {
        bod.contentType();
        // test 1 and 9  tasks
    }

    @Test(priority = 1, groups = "positiveApi")
    public void testCreateNewUserPOST() {
        bod.createNewUserPOST();
    }

    @Test(priority = 2, groups = "positiveApi")
    public void testGetUser() throws IOException {
        bod.getUser();
    }

    @Test(priority = 3, groups = "positiveApi")
    public void testSaveUserUsePUT() {
        bod.saveUserUsePUT();
    }

    @Test(priority = 4, groups = "positiveApi")
    public void testCreateAdministrator() {
        bod.createAdministrator();
    }

    @Test(priority = 5, groups = "positiveApi")
    public void testSaveAdministrator() {
        bod.saveAdministrator();
    }

    @Test(priority = 6, groups = "positiveApi")
    public void testDeleteUser() {
        bod.deleteUser();
    }

    @Test(priority = 7, groups = "positiveApi")
    public void testReturnErrorContentType() throws IOException {
        bod.returnErrorContentType();
    }

    @Test(priority = 7, groups = "positiveApi")
    public void testDefaultCondition() {
        bod.defaultCondition();
    }

    @Test(priority = 8, groups = "positiveApi")
    public void testRole() {
        bod.role();
    }

    @Test(priority = 9, groups = "positiveApi")
    public void testDeleteOrModification() {
        bod.deleteOrModification();
    }




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

 */

}
