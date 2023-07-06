package subway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

@TestConstructor(autowireMode = AutowireMode.ALL)
@DataJpaTest
class LineRepositoryTest {

    private final StationRepository stations;
    private final LineRepository lines;

    public LineRepositoryTest(final StationRepository stations, final LineRepository lines) {
        this.stations = stations;
        this.lines = lines;
    }

    @Test
    void findByName() {
        final Line line = lines.findByName("3호선");

        assertThat(line.getStations()).hasSize(1);
    }

    @Test
    void save() {
        final Line line = new Line("2호선");
        line.addStation(stations.save(new Station("선릉역")));
        lines.save(line);
        lines.flush();
    }
}
