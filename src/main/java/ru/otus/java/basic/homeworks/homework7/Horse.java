package ru.otus.java.basic.homeworks.homework7;

public class Horse implements Transport {
    private final String name;
    private final int totalEndurance;
    private int currentEndurance;
    private final int enduranceConsumption;

    public Horse(String name, int totalEndurance, int enduranceConsumption) {
        this.name = name;
        this.totalEndurance = totalEndurance;
        this.currentEndurance = totalEndurance;
        this.enduranceConsumption = enduranceConsumption;
    }

    public void sleep() {
        currentEndurance = totalEndurance;
    }

    @Override
    public String getName() {
        return String.format("лошадь '%s'", name);
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        switch (terrain) {
            case Terrain.SWAMP:
                System.out.printf("%s не может скакать через %s.%n", getName(), terrain.getRussian());
                return false;
            default:
                return canMove(terrain, distance);
        }
    }

    private boolean canMove(Terrain terrain, int distance) {
        int requiredEndurance = calculateRequiredEndurance(distance);
        if (requiredEndurance > currentEndurance) {
            System.out.printf("%s не смогла проскакать %d через %s. Не хватило выносливости.%n", getName(), distance, terrain.getRussian());
            return false;
        }
        currentEndurance -= requiredEndurance;
        System.out.printf("%s успешно проскакала %d через %s. Осталось выносливости: %d.%n", getName(), distance, terrain.getRussian(), currentEndurance);
        return true;
    }

    private int calculateRequiredEndurance(int distance) {
        return distance * enduranceConsumption;
    }
}
