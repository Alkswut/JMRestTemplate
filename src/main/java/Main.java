import org.jm.config.Config;
import org.jm.dto.userDto;
import org.jm.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        userDto connect = context.getBean("userDto", userDto.class);

        System.out.println(connect.getAllUsers());
        User user = new User(3L, "James", "Brown", (byte) 30);
        connect.saveUser(user);
        user.changeFLName("Thomas", "Shelby");
        connect.updateUser(user);
        connect.deleteUser(3L);
    }
}

//Получение всех пользователей - …/api/users ( GET )
    //session id
//Добавление пользователя - …/api/users ( POST )
    //id = 3, name = James, lastName = Brown, age = на ваш выбор
//Изменение пользователя - …/api/users ( PUT )
    //id = 3. Необходимо поменять name на Thomas, а lastName на Shelby
//Удаление пользователя - …/api/users /{id} ( DELETE )
    //id = 3