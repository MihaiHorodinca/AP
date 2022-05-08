package homework;

public class City {
    public int id;
    public String name;
    public String country;
    public String continent;
    public double latitude;
    public double longitude;

    public City() {}

    public City(String[] values){
        name = values[1];
        country = values[0];
        continent = values[5];
        latitude = Double.parseDouble(values[2]);
        longitude = Double.parseDouble(values[3]);
    }

    public String toString(){
        return name + ", " + country + " at "
                + latitude + " degrees lat and " +
                longitude + " degrees lon.";
    }
}
