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
public class team implements Serializable, Comparable<team>{
    private String name;
    private String city;
    private int wins;
    private int losses;
    private Player[] roster = new Player[30];
    private int numberofPlayers;

    public team() {
        numberofPlayers = 0;
    }

    public team(String name, String city) {
        this.name = name;
        this.city = city;
        this.wins = 0;
        this.losses = 0;
        this.numberofPlayers = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
    public void addwins(int num){
        wins = wins + num;
    }
    public void addlosses(int num){
        losses = losses + num;
    }
     public int computewiningpercent(){
         if((wins + losses) > 0)
             return 1000*wins/(wins + losses);
         else
             return 0;
     }
     public void addPlayer(Player p){
         roster[numberofPlayers] = p;
         numberofPlayers++;
         bubbleSort(roster, numberofPlayers);
     }
     public Player findPlayer(String sname){
         boolean found = false;
         int i = 0;
         Player p = null;
         while(!found && i < numberofPlayers){
             if(sname.compareTo(roster[i].getName()) == 0){
                 found = true;
                 p = roster[i];
             }
             else
                 i++;
         }
         
             return p;
     }
     public void ListPlayers(){
         for(int j = 0; j < numberofPlayers; j++)
             System.out.println(roster[j].getNumber() + "   " + roster[j].getName() + "   $" + roster[j].getSalary());
     }
     public void deletePlayer(String sname){
          boolean found = false;
         int i = 0;
         Player p = null;
         while(!found && i < numberofPlayers){
             if(sname.compareTo(roster[i].getName()) == 0){
                 found = true;
                 p = roster[i];
             }
             else
                 i++;
         }
         for(int j = i; j <numberofPlayers; j++)
             roster[j] = roster[j+1];
         numberofPlayers--;
         
     }
     public int compareTo(team t){
         return this.computewiningpercent()- t.computewiningpercent();
     }
      public static <E extends Comparable<E>> void bubbleSort(E[] array, int len){
        int lastpos;
        int index;
        E temp;
        for(lastpos = len - 1; lastpos >= 0; lastpos--){
            for(index = 0; index < lastpos; index++){
                if(array[index].compareTo(array[index + 1]) <  0){
                    temp = array[index + 1];
                    array[index + 1] = array[index];
                    array[index] = temp;
                }
            }
        }
    }
    
    
    
}
