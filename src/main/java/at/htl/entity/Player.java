package at.htl.entity;

import jdk.jfr.Name;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "findByVandNname",
                query = "select p from Player p where p.nname = :NNAME and p.vname = :VNAME"
        ),
        @NamedQuery(
                name = "findAll",
                query = "select p from Player p"
        )
}
)
@Entity
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    private String vname;

    private String nname;

    private String verein;

    public Player() {
    }

    public Player(String vname, String nname, String verein) {
        this.vname = vname;
        this.nname = nname;
        this.verein = verein;
    }

    public long getPlayerId() {
        return playerId;
    }



    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getVerein() {
        return verein;
    }

    public void setVerein(String verein) {
        this.verein = verein;
    }



    @Override
    public String toString() {
        return "Player{" +
                "vname='" + vname + '\'' +
                ", nname='" + nname + '\'' +
                ", verein='" + verein + '\'' +
                '}';
    }
}
