package org.group_3;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
     private char b = 'ь';
    // Створюємо додатковий список для збору відповідей.
    static List<String> usedCities = new ArrayList<>();
    // Список міст
    private List<String> citiesList;

    public GameLogic() {
        citiesList = downloadCityList();
    }

    //Рандомна генерація відповіді комп'ютера
    public String generateComputerResponse(String userInput) {
        char lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 1));

        //Перевірка на м'який знак здвие на одну букву
        if (lastLetter == b) {
            lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 2));
        }

        List<String> availableCities = new ArrayList<>();
        for (String city : citiesList) {
            if (city.toLowerCase().charAt(0) == lastLetter && !isCityUsed(city)) {
                availableCities.add(city);
            }
        }

        if (!availableCities.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(availableCities.size());
            String chosenCity = availableCities.get(randomIndex);
            citiesList.remove(chosenCity);
            return chosenCity;
        }

        return "здаюсь";
    }

    //Перевірка на правильність написання назви міста
    public boolean isValidCity(String city) {
        for (String citys : citiesList) {
            if (citys.equalsIgnoreCase(city)) {
                return false;
            }

        }
        return true;
    }

    //Перевірека на на писання назви міста за правильної літери
    public boolean checkingFirstLastSymbol(String userInput) {
        char firstLetter = Character.toLowerCase(userInput.charAt(0));
        String lastAddedCity = usedCities.get(usedCities.size() - 1);
        char lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
        if(lastLetter == b){
             lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 2));
        }
        if (firstLetter != lastLetter) {
            return true;
        }
        return false;
    }

    //Перевірка на використане місто
    public static boolean isCityUsed(String city) {
        for (String citys : usedCities) {
            if (citys.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }

    //Чистка списків для гри знову
    public void clearCollections() {
        citiesList.clear();
        usedCities.clear();

    }

    //Звавантаження міст з сайту
    public static List<String> downloadCityList() {
        List<String> cityList = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet("https://spravka109.net/ua/adres/ukraine/cities");
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                Document document = Jsoup.parse(content);

                Elements cityElements = document.select(".alist");
                for (Element cityElement : cityElements) {
                    String cityName = cityElement.text();


                    if (cityName.length() >= 2) {

                        cityName = cityName.split("\\(")[0].trim();

                        cityName = cityName.replace("Воронеж-45", "Вроцлав").trim();
                        cityName = cityName.replace(". р-н", "").trim();


                        cityList.add(cityName);
                    }
                }
            } else {
                System.out.println("Failed to retrieve web page. Status code: " + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return cityList;
    }
}







