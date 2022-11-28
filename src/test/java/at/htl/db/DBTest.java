package at.htl.db;

import at.htl.control.PlayerRepository;
import at.htl.entity.Player;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.db.api.Assertions.assertThat;

import javax.inject.Inject;

@QuarkusTest
public class DBTest {

    @Inject
    PlayerRepository playerRepository;

    @Inject
    AgroalDataSource ds;

    Table table = new Table(ds, "player");

    @Test
    public void test_player(){
        Player test = new Player("Lukas", "Bichl", "Bruckmühl");
        playerRepository.save(test);

        /*assertThat(table).column("nname")
                .value().isEqualTo("weinh")
                .value().isEqualTo("weinh")
                .value().isEqualTo("weinh");*/

        assertThat(table).row(3)
                .value().isEqualTo(4);

    }
}
