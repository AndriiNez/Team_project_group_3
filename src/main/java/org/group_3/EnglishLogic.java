package org.group_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnglishLogic implements GameInterfeceLogir {
    private final char q = 'q';
    private final char w = 'w';
    private final char x = 'x';


    private List<String> citiesList;
    public List<String> usedCities = new ArrayList<>();

    public EnglishLogic() {
        this.citiesList = ListCity.downloadCityListEnglish();
    }

    @Override
    public String generateComputerResponse(String userInput) {
        char lastLetter = Character.toLowerCase(userInput.charAt(userInput.length() - 1));

        //Перевірка на м'який знак здвие на одну букву
        if (lastLetter == q || lastLetter == w || lastLetter == x) {
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

        return "i give up";
    }


    @Override
    public boolean isValidCity(String city) {
        for (String citys : citiesList) {
            if (citys.equalsIgnoreCase(city)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkingFirstLastSymbol(String userInput) {
        char firstLetter = Character.toLowerCase(userInput.charAt(0));
        String lastAddedCity = usedCities.get(usedCities.size() - 1);
        char lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
        if (lastLetter == q || lastLetter == w || lastLetter == x) {
            lastLetter = Character.toLowerCase(lastAddedCity.charAt(lastAddedCity.length() - 2));
        }
        return firstLetter != lastLetter;
    }


    @Override
    public boolean isCityUsed(String city) {
        for (String citys : usedCities) {
            if (citys.equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearCollections() {
        citiesList.clear();
        usedCities.clear();
    }
}
