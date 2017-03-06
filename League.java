/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author pwisecup
 */
public class League {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        team[] league = new team[15];
        Scanner kbd = new Scanner(System.in);
        String tname, tcity, resp, pname,  pos;
        int i, wins, losses, pnum, cmd, size = 0;
        double salary;
        System.out.println("Enter the name of the league file");
        String fname = kbd.nextLine();
        System.out.println("Does this file exist 1: yes, 2: No");
        cmd = kbd.nextInt();
        if(cmd == 1){
            FileInputStream ifile = new FileInputStream(fname);
            ObjectInputStream istream = new ObjectInputStream(ifile);
            boolean endoffile = false;
            while(!endoffile){
                try {
                    league[size] = (team) istream.readObject();
                    size++;
                }
                catch(IOException e){
                    endoffile = true;
                }
            }
        }
            boolean done = false;
            while(!done){
                System.out.println("Enter a command from:\n"
                        + "\t1: Enter a new team\n"
                        + "\t2: update a teams record\n"
                        + "\t3: Show Standings\n"
                        + "\t4: Add players to a team\n"
                        + "\t5: List a teams roster\n"
                        + "\t6: Trade players\n"
                        + "\t7: Give a player a raise\n"
                        + "\t8: quit\n");
                cmd = kbd.nextInt();
                
                switch(cmd){
                    case 1:
                        kbd.nextLine();
                        System.out.println("Enter the team name");
                        tname = kbd.nextLine();
                        System.out.println("Enter the team city");
                        tcity = kbd.nextLine();
                        team t = new team(tname, tcity);
                        league[size++]= t;
                        break;
                    case 2:
                        kbd.nextLine();
                        System.out.println("Enter the teams name");
                        tname = kbd.nextLine();
                        boolean found = false;
                        i = 0;
                        while(!found && i < size){
                            if(tname.compareTo(league[i].getName()) == 0)
                                found = true;
                            else
                                i++;
                        }
                        if(found){
                            System.out.println("Enter the new wins");
                            wins = kbd.nextInt();
                            league[i].addwins(wins);
                            System.out.println("Enter the new losses");
                            losses = kbd.nextInt();
                            league[i].addlosses(losses);
                        }
                            
                        else
                            System.out.println("No such team");
                        break;
                    case 3:
                        bubbleSort(league, size);
                        for(i = 0; i < size; i++)
                            System.out.printf(" %20s %5d\n", league[i].getName(), league[i].computewiningpercent());
                        break;
                    case 4:
                        kbd.nextLine();
                        System.out.println("Enter the team to add players");
                        tname = kbd.nextLine();
                        found = false;
                        i = 0;
                        while(!found && i < size){
                            if(tname.compareTo(league[i].getName()) == 0)
                                found = true;
                            else
                                i++;
                        }
                        if(found){
                            System.out.println("How many players are you adding");
                            int nop = kbd.nextInt();
                            kbd.nextLine();
                            for(int j = 0; j<nop; j++){
                                System.out.println("Enter the players name");
                                pname = kbd.nextLine();
                                System.out.println("Enter the players position");
                                pos = kbd.nextLine();
                                System.out.println("Enter the players number");
                                pnum = kbd.nextInt();
                                System.out.println("Enter the players salary");
                                salary = kbd.nextDouble();
                                kbd.nextLine();
                                Player p = new Player(pname, pnum, pos, salary);
                                league[i].addPlayer(p);
                            }
                        }
                            
                        else
                            System.out.println("No such team");
                       
                        break;
                    case 5:
                        kbd.nextLine();
                        System.out.println("Enter the team to list players");
                        tname = kbd.nextLine();
                        found = false;
                        i = 0;
                        while(!found && i < size){
                            if(tname.compareTo(league[i].getName()) == 0)
                                found = true;
                            else
                                i++;
                        }
                        if(found){
                            league[i].ListPlayers();
                        }
                            
                        else
                            System.out.println("No such team");
                       
                        break;
                    
                    case 6:
                        kbd.nextLine();
                        System.out.println("Enter the first team in the trade");
                        String team1 = kbd.nextLine();
                        System.out.println("Enter the second team in the trade");
                        String team2 = kbd.nextLine();
                        found = false;
                        i = 0;
                        while(!found && i < size){
                            if(team1.compareTo(league[i].getName()) == 0)
                                found = true;
                            else
                                i++;
                        }
                        if(found){
                            team t1 = league[i];
                            found = false;
                            i = 0;
                            while(!found && i < size){
                                if(team2.compareTo(league[i].getName()) == 0)
                                    found = true;
                                else
                                    i++;
                            }
                            if(found){
                                team t2 = league[i];
                                System.out.println("Enter the name of the player from team1");
                                pname = kbd.nextLine();
                                Player p1 = t1.findPlayer(pname);
                                if(p1 != null){
                                    
                                    System.out.println("Enter the player from team2");
                                    pname = kbd.nextLine();
                                    Player p2 = t2.findPlayer(pname);
                                    if(p2 != null){
                                        t1.deletePlayer(pname);
                                        t2.deletePlayer(pname);
                                        t1.addPlayer(p2);
                                        t2.addPlayer(p1);
                                    }
                                    else
                                        System.out.println("Player 2 does not exist");
                                }
                                else
                                    System.out.println("Player 1 does not exist");
                            }
                            else
                                System.out.println("No such team");
                        }
                        else
                            System.out.println("No such team");
                        break;
                        
                    case 7:
                        kbd.nextLine();
                        System.out.println("What is the name of the player to give a raise");
                        pname = kbd.nextLine();
                        found = false;
                        i = 0;
                        while(!found && i < size){
                           Player  p1 =league[i].findPlayer(pname);
                           if(p1 != null)
                               found = true;
                           else
                               i++;
                           if(found){
                            System.out.println("What is his new salary?");
                            salary = kbd.nextDouble();
                            kbd.nextLine();
                            p1.setSalary(salary);
                        }
                           
                                
                        }
                        if(!found)
                            System.out.println("No such player");
                        break;
                        
                    case 8:
                        done = true;
                }
            }
            FileOutputStream ofile = new FileOutputStream(fname);
            ObjectOutputStream ostream = new ObjectOutputStream(ofile);
            for(i = 0; i< size; i++) 
                ostream.writeObject(league[i]);
            ostream.close();
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
    

