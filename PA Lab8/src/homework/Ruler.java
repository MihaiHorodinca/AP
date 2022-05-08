package homework;

public class Ruler {

    private Ruler(){}

    public static double distanceBetween(City city1, City city2){
        double lat1 = Math.toRadians(city1.latitude);
        double lat2 = Math.toRadians(city2.latitude);
        double lon1 = Math.toRadians(city1.longitude);
        double lon2 = Math.toRadians(city2.longitude);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth
        double r = 6371;

        return(c * r);
    }
}
