package f102249.find_it_bff.models;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class SearchEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placeType;

    private String city;

    private Integer userAge;

    public SearchEvent() {
    }

    public SearchEvent(String placeType, String city, Integer userAge) {
        this.placeType = placeType;
        this.city = city;
        this.userAge = userAge;
    }
}
