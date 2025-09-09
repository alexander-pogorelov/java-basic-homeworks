package ru.otus.java.basic.homeworks.homework4;

import java.time.Year;

public class Homework4 {
    public static void main(String[] args) {

        for (User user : createUsers()) {
            if (Year.now().getValue() > user.getBirthYear() + 40) {
                user.info();
            }
        }

        Box box = new Box(10, 20, 30, "black");
        box.open();
        box.close();
        box.addItem("Телефон");
        box.open();
        box.addItem("Телефон");
        box.addItem("");
        box.addItem("Карта памяти");
        box.drop();
        box.drop();
        box.info();
        box.addItem("Карта памяти");
        box.open();
        box.changeColor("Белый");
        box.info();
    }

    private static User[] createUsers() {
        return new User[] {
                new User("Иванов", "Иван", "Иванович", 1970, "ivanov_i_i@example.com"),
                new User("Петров", "Петр", "Петрович", 1980, "petrov_p_p@example.com"),
                new User("Васильев", "Василий", "Васильевич", 1990, "vasilyev_v@example.com"),
                new User("Сергеев", "Сергей", "Сергеевич", 1984, "sergeev_s_s@example.com"),
                new User("Александров", "Александр", "Александрович", 1985, "alexandrov_a_a@example.com"),
                new User("Арсеньев", "Арсений", "Арсеньевич", 1986, "arsenyev_a-a@example.com"),
                new User("Владимиров", "Владимир", "Владимирович", 1955, "vladimirov_v_v@example.com"),
                new User("Павлов", "Павел", "Павлович", 1993, "pavlov_p_p@example.com"),
                new User("Михайлов", "Михаил", "Михайлович", 2007, "mikhaylov_m_m@example.com"),
                new User("Константинов", "Константин", "Константинович", 2010, "konstantinov_k_k@example.com"),
        };
    }
}
