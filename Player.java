/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league;
import java.io.Serializable;

/**
 *
 * @author lab701
 */
public class Player implements Serializable, Comparable<Player>{
    
    private String name;
    private int number;
    private String position;
    private double salary;

    public Player(String name, int number, String position, double salary) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public int compareTo(Player x){
        return (int) (this.getSalary() - x.getSalary());
    }
    
}
