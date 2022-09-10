package f102249.find_it_bff.payload.response;

public class PlaceResponse {
    private String placeId;

    private Double lat;

    private Double lng;

    private String name;

    private Double rating;

    private String address;

    private String city;

    public PlaceResponse(String placeId, Double lat, Double lng, String name, Double rating, String address, String city) {
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.city = city;
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
}
