package ru.otus.java.basic.homeworks.homework5;

public abstract class Animal {
    public static final String SWIMMING_TYPE = "swimming";
    public static final String RUNNING_TYPE = "running";
    public static final byte FAILED_TIME = -1;

    private final String name;
    private final int maxEndurance;
    private int currentEndurance;
    private int runningSpeed;
    private int swimmingSpeed;
    private int runningEnduranceCost;
    protected int swimmingEnduranceCost;

    public Animal(String name, int maxEndurance, int runningSpeed, int swimmingSpeed) {
        this.name = name;
        this.maxEndurance = maxEndurance;
        this.currentEndurance = maxEndurance;
        this.runningSpeed = runningSpeed;
        this.swimmingSpeed = swimmingSpeed;
        this.runningEnduranceCost = 1;
    }

    public void sleep() {
        this.currentEndurance = this.maxEndurance;
        this.printRestMessage();
    }

    abstract public void info();

    public int run(int distance) {
        return this.calculate(distance, Animal.RUNNING_TYPE);
    }

    public int swim(int distance) {
        return this.calculate(distance, Animal.SWIMMING_TYPE);
    }

    protected int calculate(int distance, String activityType) {
        int cost = this.calculateCost(distance, activityType);

        if (cost > this.currentEndurance) {
            this.printFailureMessage(cost, activityType);
            return Animal.FAILED_TIME;
        }

        int time = this.calculateTime(distance, activityType);
        this.currentEndurance -= cost;
        this.printSuccessMessage(distance, time, activityType);

        return time;
    }

    protected String animalInfo() {
        return String.format(
                "имя: '%s', скорость бега: %d м/с, скорость плавания: %d м/с, выносливость: %d, осталось выносливости: %d.%n",
                name,
                runningSpeed,
                swimmingSpeed,
                maxEndurance,
                currentEndurance
        );
    }

    private int calculateTime(int distance, String activityType) {
        int time;
        switch(activityType) {
            case Animal.RUNNING_TYPE:
                time = distance / runningSpeed;
                break;
            case Animal.SWIMMING_TYPE:
                time = distance / swimmingSpeed;
                break;
            default:
                throw new RuntimeException("Неизвестная активность животного" + activityType);
        }

        return time;
    }

    private int calculateCost(int distance, String activityType) {
        int cost;
        switch(activityType) {
            case Animal.RUNNING_TYPE:
                cost = distance * this.runningEnduranceCost;
                break;
            case Animal.SWIMMING_TYPE:
                cost = distance * this.swimmingEnduranceCost;
                break;
            default:
                throw new RuntimeException("Неизвестная активность животного" + activityType);
        }

        return cost;
    }

    private void printFailureMessage(int cost, String activityType) {
        System.out.printf("Активность: %s. Животное '%s' устало. Не хватает выносливости. Требуется %d выносливости, осталось - %d.%n", activityType, name, cost, currentEndurance);
    }

    private void printSuccessMessage(int distance, int time, String activityType) {
        System.out.printf("Активность: %s. Животное %s освоило дистанцию в %d метров за %d секунд. Осталось выносливости: %d.%n", activityType, name, distance, time, currentEndurance);
    }

    private void printRestMessage() {
        System.out.printf("Животное %s поспало. Выносливость восстановилась до %d.%n", name, currentEndurance);
    }
}
