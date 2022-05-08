package compulsory;

public class Main {
    public static void main(String[] args) {
        ContinentDAO continentDAO = new ContinentDAO();

        continentDAO.create("peru");
        System.out.println(continentDAO.findByName("peru"));
    }
}
