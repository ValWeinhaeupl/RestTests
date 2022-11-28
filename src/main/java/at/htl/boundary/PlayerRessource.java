package at.htl.boundary;

import at.htl.control.PlayerRepository;
import at.htl.entity.Player;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("player")
public class PlayerRessource {
    @Inject
    PlayerRepository playerRepository;

    TransactionManager tm;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Player post(Player player){
        playerRepository.save(player);
        return player;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{playerId}")
    public Player getById(@PathParam("playerId") long id){
        return playerRepository.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("firstandlast")
    public List<Player> findByVandNname(
            @QueryParam("vname") String vname,
            @QueryParam("nname") String nname
    ){
       return playerRepository.findByVandNname(vname, nname);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAll(){
        return playerRepository.findAll();
    }
}
