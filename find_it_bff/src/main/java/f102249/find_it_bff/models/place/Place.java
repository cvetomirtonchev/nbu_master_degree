package f102249.find_it_bff.models.place;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @NotBlank
    private String placeType;

    private List<String> accessibleFeatures;

    public Place() {
    }

    public Place(String placeId, Double lat, Double lng, String name, Double rating, String address, String city, String placeType, List<String> accessibleFeatures) {
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.city = city;
        this.placeType = placeType;
        this.accessibleFeatures = accessibleFeatures;
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

    public String getPlaceType() {
        return placeType;
    }

    public List<String> getAccessibleFeatures() {
        return accessibleFeatures;
    }
}
