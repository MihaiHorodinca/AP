package homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

public class DataLoader {
    static String path = "C:\\Users\\Mihai\\IdeaProjects\\PA Lab8\\src\\homework\\cities.csv";

    public static void loadCities(){

        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            CitiesDAO citiesDAO = new CitiesDAO();
            line = br.readLine();

            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                for (int i=0; i<values.length; i++)
                    values[i] = values[i].toLowerCase().trim();

                City city = new City(values);
                if (city.name.equals("n/a"))
                    continue;

                citiesDAO.create(city);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadCities();
    }
}
