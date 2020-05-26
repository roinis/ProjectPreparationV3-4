package Service;

import Exceptions.DomainException;
import Exceptions.notFoundException;
import http.Parser;
import http.Response;

import java.util.ArrayList;

public class MainController {

    private static MainController controller;
    private JobsController jobsController;
    private AssociationController associationController;
    private SystemController systemController;
    private UserController userController;

    private MainController() {
        associationController=new AssociationController();
        jobsController=new JobsController();
        systemController=new SystemController();
        userController=new UserController();
    }

    public static MainController getInstance(){
        if(controller==null){
            controller=new MainController();
        }
        return controller;
    }

    public void handle(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        String[] paths=path.split("/");
        try {
            switch (paths[1]) {
            case "Jobs":
                jobsController.handle(paths[2],body,response);
                break;
            case "Association":
                associationController.handle(paths[2],body,response);
                break;
            case "User":
                userController.handle(paths[2],body,response);
                break;
            case "System":
                systemController.handle(paths[2],body,response);
                break;
                default:
                    throw new notFoundException();
            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            throw new notFoundException();
        }

    }
}
