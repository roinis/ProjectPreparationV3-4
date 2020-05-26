package Domain.System;//roei cohen
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Register {


    private String combineFullName(String full_name){
        String[] nameSplitted = full_name.split(" ");
        String full_name_fixed ="";
        for(int i = 0;i<nameSplitted.length;i++){
            if(i==0){
                full_name_fixed = nameSplitted[i];
            }
            else
                full_name_fixed = full_name_fixed+"_"+nameSplitted[i];
        }
        return  full_name_fixed;
    }

    public boolean registerToSystem(String user_name,String password,String user_id,String full_name){
        Member register_member = (Member)AlphaSystem.getSystem().GetSpecificFromDB(2,user_name);
        if(register_member==null){
            Member new_Member = new Member(user_name,password,user_id,full_name);
            AlphaSystem.getSystem().AddtoDB(2,new_Member);
            return true;
        }
        return false;
    }
}
