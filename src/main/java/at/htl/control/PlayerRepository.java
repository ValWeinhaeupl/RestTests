package at.htl.control;

import at.htl.entity.Player;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PlayerRepository {
    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    public void save(Player player) {
        em.persist(player);
    }

    public Player findById(long id) {
        return em.find(Player.class, id);
    }

    public List<Player> findByVandNname(String vname, String nname){
        TypedQuery<Player> query = em.createNamedQuery("findByVandNname", Player.class)
                .setParameter("VNAME", vname)
                .setParameter("NNAME", nname);
        return query.getResultList();
    }
}
