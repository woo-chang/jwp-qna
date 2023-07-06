package subway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

@TestConstructor(autowireMode = AutowireMode.ALL)
@DataJpaTest
class StationRepositoryTest {

    private final StationRepository stations;
    private final LineRepository lines;

    public StationRepositoryTest(final StationRepository stations, final LineRepository lines) {
        this.stations = stations;
        this.lines = lines;
    }

    @Test
    void saveWithLine() {
        final Station expected = new Station("선릉역");
        expected.setLine(lines.save(new Line("2호선")));
        stations.save(expected);
        stations.flush();
    }

    @Test
    void findByNameWithLine() {
        final Station actual = stations.findByName("교대역");

        assertThat(actual).isNotNull();
        assertThat(actual.getLine()).isNotNull();
    }

    @Test
    void updateWithLine() {
        final Station expected = stations.findByName("교대역");
        expected.setLine(lines.save(new Line("2호선")));
        stations.flush(); //변경 감지 대상임을 알리기 위해 flush
    }

    @Test
    void removeLineInStation() {
        final Station expected = stations.findByName("교대역");
        expected.setLine(null);
        stations.flush();
    }

    @Test
    void removeLine() {
        final Line line = lines.findByName("3호선");
        final Station expected = stations.findByName("교대역");
        expected.setLine(null);
        lines.delete(line);
        lines.flush();
    }
}
