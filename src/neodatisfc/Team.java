/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neodatisfc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oracle
 */
public class Team {
    
    private String name;
    private List players;

    public Team(String name) {
        this.name = name;
        players = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getPlayers() {
        return players;
    }

    public void setPlayers(List players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" + "name=" + name + ", players=" + players + '}';
    }
    
    

}

    

