package Domain.System;
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;

import java.time.LocalDate;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Season s=new Season(1990,null,null);
        Random random = new Random();
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        int x=0;
    }
}
