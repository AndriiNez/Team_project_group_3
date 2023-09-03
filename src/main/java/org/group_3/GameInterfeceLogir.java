package org.group_3;

import java.util.ArrayList;
import java.util.List;

public interface GameInterfeceLogir {

    String generateComputerResponse(String userInput);

    boolean isValidCity(String city);

    boolean checkingFirstLastSymbol(String userInput);

    boolean isCityUsed(String city);

    void clearCollections();
}
