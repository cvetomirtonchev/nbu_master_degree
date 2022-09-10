package f102249.find_it_bff.payload.request.event;

public class SearchEventRequest {
    private String placeType;

    private String city;

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
