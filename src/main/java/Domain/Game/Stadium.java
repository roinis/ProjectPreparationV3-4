package Domain.Game;
import  Domain.Events.*;
import Domain.User.*;
import  Domain.System.*;
import  Domain.Jobs.*;
import  Domain.Association.*;

import java.util.Scanner;

public class Stadium {

    private String stadiumName;
    private String city;

    public Stadium(String stadiumName, String city){
        this.stadiumName=stadiumName;
        this.city=city;
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoDB(11,this);
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getCity(){
        return city;
    }


    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

}
