/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neodatisfc;

import java.util.Date;
import java.util.List;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.core.query.nq.SimpleNativeQuery;

/**
 *
 * @author oracle
 */
public class Neodatisfc {

    /**
     * @param args the command line arguments
     */
    public static final String ODB_NAME = "Neodatis-Futebul.pt";
    public static ODB odb = null;
    
    public static void main(String[] args) throws Exception {
        
        odb = ODBFactory.open(ODB_NAME);//conectamos a la base

//        añadir_jugadores_equipos(odb);
//        amosar_deportes(odb);
//        amosar_jugadores(odb);
//        actualiza_por_nome_xogador(odb, "olivier", "oliver");
//        xogadoresDeporte(odb, "volley-ball");
//        devoltar_equipos_con_xogadores_menos_dunha_cantidade(odb, 2000);
//        iguala_nomes_por_deporte(odb, "tennis", "pedro");
//        nativeQueryXogadoresDeporte(odb, "volley-ball"); 
//        borrar_xogadores_por_nome(odb, "minh");
//        listar_xogadores_por_nome_e_deporte(odb, "pedro", "tennis");
//        aumenta_salario_xogadores_equipo(odb, "pedro", "tennis", 1000);
        
        odb.close();//desconectamos la base
    }
    
    public static void añadir_jugadores_equipos(ODB odb) throws Exception {

        //objetos de la clase Sport 
        Sport volleyball = new Sport("volley-ball");
        Sport tennis = new Sport("tennis");

        //creamos 4 jugadores para volleyball
        Player playerV1 = new Player("olivier", new Date(), volleyball, 1000);
        Player playerV2 = new Player("pierre", new Date(), volleyball, 1500);
        Player playerV3 = new Player("elohim", new Date(), volleyball, 2000);
        Player playerV4 = new Player("minh", new Date(), volleyball, 1300);

        //creamos 4 jugadores para tennis
        Player playerT1 = new Player("luis", new Date(), tennis, 1600);
        Player playerT2 = new Player("carlos", new Date(), tennis, 2000);
        Player playerT3 = new Player("luis", new Date(), tennis, 1500);
        Player playerT4 = new Player("jose", new Date(), tennis, 3000);

        //creamos 2 equipos para volleyball
        Team team1 = new Team("Paris");
        Team team2 = new Team("Montepellier");

        //creamos 2 equipos para tennis
        Team team3 = new Team("Bordeux");
        Team team4 = new Team("Lion");

        //introducimos 2 jugadores en el team1
        team1.getPlayers().add(playerV1);
        team1.getPlayers().add(playerV2);
        System.out.println("Paris " + team1);

        //introducimos 2 jugadores en el team2
        team2.getPlayers().add(playerV3);
        team2.getPlayers().add(playerV4);
        System.out.println("Montepellier " + team2);

        //introducimos 2 jugadores en team3
        team3.getPlayers().add(playerT1);
        team3.getPlayers().add(playerT2);
        System.out.println("Bordeux " + team3);

        //introducimos 2 jugadores en team4
        team4.getPlayers().add(playerT3);
        team4.getPlayers().add(playerT4);
        System.out.println("Lion " + team4);

        //creamos los partidos para volleyball
        Game partido1 = new Game(new Date(), volleyball, team1, team2);

        //creamos los partidos para tennis
        Game partido2 = new Game(new Date(), tennis, team3, team4);
        
        try {
            odb.store(partido1);
            odb.store(partido2);            
        } finally {
            
            if (odb != null) {
                odb.close();
            }
        }
        
    }

    //amosar os nomes de todos os deportes
    public static void amosar_deportes(ODB odb) {
        
        Objects<Sport> sports = odb.getObjects(Sport.class);
        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();
            System.out.println(sport.getName());
        }
    }

    //amosar nome do xogador, deporte e salario
    public static void amosar_jugadores(ODB odb) {
        
        Objects<Player> players = odb.getObjects(Player.class);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName() + " - " + player.getFavoriteSport() + " - " + player.getSalario());
        }
    }

    //actualiza o nome dun xogador
    public static void actualiza_por_nome_xogador(ODB odb, String nombreAnterior, String nombreNuevo) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("name", nombreAnterior));
        Objects<Player> player = odb.getObjects(query);
        Player p = null;
        while (player.hasNext()) {
            p = player.next();
            p.setName(nombreNuevo);
            OID store = odb.store(p);
        }
    }

    //amosar os xogadores por deporte
    public static void xogadoresDeporte(ODB odb, String deporte) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("favoriteSport", deporte));
        Objects<Player> players = odb.getObjects(query);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName());
        }
    }

    //amosar xogador e equipo que cobren unha cantidade menor a indicada
    public static void devoltar_equipos_con_xogadores_menos_dunha_cantidade(ODB odb, int cantidade) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.lt("salario", cantidade));
        Objects<Player> players = odb.getObjects(query);
        while(players.hasNext()){
            System.out.println(players.next().toString());
        }

    }

    //iguala os nomes dos xogadores polo deporte especificado
    public static void iguala_nomes_por_deporte(ODB odb, String deporte, String nombreNuevo) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("favoriteSport", deporte));
        Objects<Player> players = odb.getObjects(query);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            player.setName(nombreNuevo);
            odb.store(player);
        }
    }

    //dada as primeiras letras dun deporte amosa os nomes dos xogadores
    public static void nativeQueryXogadoresDeporte(ODB odb, final String deporte) {
        
        IQuery query = new SimpleNativeQuery(){
            public boolean match(Player player){
                return player.getFavoriteSport().getName().toLowerCase().startsWith("v");
            }
        };
        Objects<Player> players = odb.getObjects(query);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName()+" - "+player.getFavoriteSport().getName()+" - "+player.getSalario() +" - "+player.getBirthday());        
        }
    }

    //borra xogadores por nome
    public static void borrar_xogadores_por_nome(ODB odb, String nombre) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("name", nombre));
        Objects<Player> players = odb.getObjects(query);
        Player player = (Player) odb.getObjects(query).getFirst();
        odb.delete(player);
    }

    //listar xogadores por el nombre y el deporte
    public static void listar_xogadores_por_nome_e_deporte(ODB odb, String nombre, String deporte) {
        
        IQuery query = odb.criteriaQuery(Player.class, Where.and()
                .add(Where.equal("favoriteSport.name", deporte))
                .add(Where.equal("name", nombre)));
        Objects<Player> players = odb.getObjects(query);
        Player player = null;
        while (players.hasNext()) {
            player = players.next();
            System.out.println(player.getName());
            
        }
    }
    
    //aumentar o salario de todos os xogadores, indicando o nome e o equipo 
    public static void aumenta_salario_xogadores_equipo(ODB odb, String nombre, String equipo, int aumento){
        
        IQuery query = odb.criteriaQuery(Team.class, Where.equal("name", equipo));
        Objects<Team> teams = odb.getObjects(query);
        Team team = null;
        while(teams.hasNext()){
            team = teams.next();
            List<Player> players = team.getPlayers();
            for(Player p:players){
                if(p.getName().equals(nombre)){
                    p.setSalario(p.getSalario()+aumento);
                    odb.store(p);
                }
            }
        }

    }
}
