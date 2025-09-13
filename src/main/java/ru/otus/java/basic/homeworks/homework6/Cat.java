package ru.otus.java.basic.homeworks.homework6;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean isHungry;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isHungry = true;
    }

    public void eat(Bowl bowl) {
        isHungry = !bowl.removeFood(appetite);
        String message = isHungry
                ? String.format("Слишком мало еды в тарелке для кота %s. Кот %s отказался есть.", name, name)
                : String.format("Кот %s съел %dуе еды.", name, appetite);
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", isHungry=" + isHungry +
                '}';
    }
}
