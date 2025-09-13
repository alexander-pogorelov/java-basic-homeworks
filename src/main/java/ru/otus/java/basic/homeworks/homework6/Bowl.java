package ru.otus.java.basic.homeworks.homework6;

public class Bowl {
    private final int maxFoodAmount;
    private int currentFoodAmount;

    public Bowl(int maxFoodAmount) {
        this.maxFoodAmount = maxFoodAmount;
        this.currentFoodAmount = maxFoodAmount;
    }

    public void addFood(int foodAmount) {
        int current = this.currentFoodAmount;
        currentFoodAmount += foodAmount;
        if (currentFoodAmount > maxFoodAmount) {
            currentFoodAmount = maxFoodAmount;
        }
        System.out.printf("В тарелку добавили %dуе еды. Стало %dуе еды.%n", currentFoodAmount - current, currentFoodAmount);
    }

    public boolean removeFood(int foodAmount) {
        if (foodAmount > currentFoodAmount) {
            return false;
        }
        currentFoodAmount -= foodAmount;
        return true;
    }

    public void printStatus() {
        System.out.printf("В тарелке осталось %dуе еды.%n", currentFoodAmount);
    }
}
