package Service;

import Domain.Association.AssociationMember;
import Domain.System.*;
import Domain.User.Member;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import http.Parser;
import http.Response;

import java.util.ArrayList;

public class SystemController {
    private SystemResponses systemResponses;

    public SystemController() {
        systemResponses=new SystemResponses();
    }

    public void routing(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        try {
            String input1, input2, input3, input4;
            switch (path) {
                case "Login":
                    input1 = Parser.getElement("user_name", body);
                    input2 = Parser.getElement("password", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    Member member=Login(input1, input2);
                    systemResponses.LoginResponse(member,response);
                    break;
                case "Register":
                    input1 = Parser.getElement("user_name", body);
                    input2 = Parser.getElement("password", body);
                    input3 = Parser.getElement("user_id", body);
                    input4 = Parser.getElement("full_name", body);
                    if (input1 == null || input2 == null || input3 == null || input4 == null)
                        throw new DomainException("invalid input");
                    Register(input1, input2, input3, input4);
                    break;
                case "Logout":
                    input1 = Parser.getElement("user_name", body);
                    if (input1 == null)
                        throw new DomainException("invalid input");
                    Logout(input1);
                    break;
                default:
                    throw new notFoundException();

            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            if(e.getClass().equals(NumberFormatException.class))
                throw (NumberFormatException)e;
            throw new notFoundException();
        }

    }

    private Member Login(String user_name, String password) throws DomainException {
        return AlphaSystem.getSystem().Login(user_name,password);
    }

    private boolean Register(String user_name,String password,String user_id,String full_name) throws DomainException {
        return AlphaSystem.getSystem().Register(user_name, password, user_id,full_name);
    }

    private void Logout(String Username) throws DomainException {
        Member member = (Member)AlphaSystem.getSystem().GetSpecificFromMemory(2,Username);
        if(member==null){
            throw new DomainException("No such username exists");
        }
        AlphaSystem.getSystem().Logout(member);
    }

    //-----------------------------------------------------------------------------------------


}
