/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neodatisfc;

import java.util.Date;

/**
 *
 * @author oracle
 */
public class Game {
    
    private Date when;
    private Sport sport;
    private Team team1;
    private Team team2;
    private String result;

    public Game(Date when, Sport sport, Team team1, Team team2) {
        this.when = when;
        this.sport = sport;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Game{" + "when=" + when + ", sport=" + sport + ", team1=" + team1 + ", team2=" + team2 + ", result=" + result + '}';
    }
    
    
    
}
