package entity;

import dto.AnimalDTO;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author benjaminp
 * @version 1.0
 */
@Entity
@Table(name = "Animal")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer birthYear;

    @Enumerated(EnumType.STRING)
    private AnimalSound animalSound;
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;


    public Animal() {}
    public Animal(String name, Integer birthYear,AnimalSound animalSound, AnimalType animalType) { this.name = name; this.birthYear = birthYear; this.animalSound = animalSound; this.animalType = animalType;}
    @Contract(pure = true)
    public Animal(AnimalDTO dto) { this.name = dto.name; this.birthYear = dto.age; this.animalSound = dto.animalSound; this.animalType = dto.animalType; }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public AnimalSound getAnimalSound() {
        return animalSound;
    }
    public AnimalType getAnimalType() {
        return animalType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + birthYear +
                ", animalType=" + animalType +
                '}';
    }
}