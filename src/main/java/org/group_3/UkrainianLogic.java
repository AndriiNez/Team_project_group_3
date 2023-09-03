package org.group_3;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UkrainianLogic implements GameInterfeceLogir {
    private final char b = 'ь';
    private final char c = 'и';
    private final char d = 'й';

    private final char e = 'ґ';

    private final char f = 'ї';

    private final char g = 'ц';


    // Створюємо додатковий список для збору відповідей.

    // Список міст
    private List<String> citiesList;
    public List<String> usedCities = new ArrayList<>();

    public UkrainianLogic() {
        citiesList = ListCity.downloadCityListUkrainian();
    }

    //Рандомна генерація відповіді комп'ютера
    @Override
    public String generateComputerResponse(String userInput) {
        char lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 1));

        //Перевірка на м'який знак здвие на одну букву
        if (lastLetter == b || lastLetter == c || lastLetter == d || lastLetter == e || lastLetter == f || lastLetter == g) {
            lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 2));
        }
        if (userInput.toLowerCase().endsWith("ий")) {
            lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 3));
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
    @Override
    public boolean isValidCity(String city) {
        for (String citys : citiesList) {
            if (citys.equalsIgnoreCase(city)) {
                return false;
            }
        }
        return true;
    }

    //Перевірека на на писання назви міста за правильної літери
    @Override
    public boolean checkingFirstLastSymbol(String userInput) {
        char firstLetter = Character.toLowerCase(userInput.charAt(0));
        String lastAddedCity = usedCities.get(usedCities.size() - 1);
        char lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
        if (lastLetter == b || lastLetter == c || lastLetter == d || lastLetter == e || lastLetter == f || lastLetter == g) {
            lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 2));
        }
        if (lastAddedCity.toLowerCase().endsWith("ий")) {
            lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 3));
        }
        return firstLetter != lastLetter;
    }

    //Перевірка на використане місто
    @Override
    public boolean isCityUsed(String city) {
        for (String citys : usedCities) {
            if (citys.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }

    //Чистка списків для гри знову
    @Override
    public void clearCollections() {
        citiesList.clear();
        usedCities.clear();

    }
}







