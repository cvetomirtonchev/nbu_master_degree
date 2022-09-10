package f102249.find_it_bff.models.place;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placeId;

    private Double lat;

    private Double lng;

    @NotBlank
    private String name;

    private Double rating;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    public Place() {
    }

    public Place(String placeId, Double lat, Double lng, String name, Double rating, String address, String city) {
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
