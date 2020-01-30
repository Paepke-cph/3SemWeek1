package dto;

import entity.AnimalSound;
import entity.AnimalType;

public class AnimalDTO {
    public String name;
    public Integer age;
    public AnimalSound animalSound;
    public AnimalType animalType;

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", animalSound=" + animalSound +
                ", animalType=" + animalType +
                '}';
    }
}
