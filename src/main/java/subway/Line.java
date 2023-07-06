package subway;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "line")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //station의 line필드가 FK를 관리하고 있다는 것을 알려준다.
    @OneToMany(mappedBy = "line")
    private List<Station> stations = new ArrayList<>();

    protected Line() {
    }

    public Line(final String name) {
        this.name = name;
    }

    public void addStation(final Station station) {
        station.setLine(this);
        stations.add(station);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }
}
