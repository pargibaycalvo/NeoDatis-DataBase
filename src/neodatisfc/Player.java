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
public class Player {
    private String name;
    private Date birthday;
    private Sport favoriteSport;
    int salario;

    public Player(String name, Date birthday, Sport favoriteSport, int salario) {
        this.name = name;
        this.birthday = birthday;
        this.favoriteSport = favoriteSport;
        this.salario = salario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sport getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(Sport favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", birthday=" + birthday + ", favoriteSport=" + favoriteSport + ", salario=" + salario + '}';
    }
    
}
