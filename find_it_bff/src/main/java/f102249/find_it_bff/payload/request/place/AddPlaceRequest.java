package f102249.find_it_bff.payload.request.place;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddPlaceRequest {

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

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getAccessibleFeatures() {
        return accessibleFeatures;
    }

    public void setAccessibleFeatures(List<String> accessibleFeatures) {
        this.accessibleFeatures = accessibleFeatures;
    }
}
