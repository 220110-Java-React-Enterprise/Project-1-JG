package servlets;

import exceptions.MalformedTableException;
import pojos.*;
import repos.ItemRepo;
import scriptors.SQLScriptor;
import utils.FileLogger;
import utils.GlobalStore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/*")
public class DataServlet extends HttpServlet {
    private ItemRepo itemRepo;
    
    @Override
    public void init() {
        // new repo
        itemRepo = new ItemRepo();
    }

    //CHANGE this so it gets objects from the database not locally
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfThing = " yup ";
        nameOfThing = readURI(req);
        ObjectMapper mapper = new ObjectMapper();
            //what happened to objectReflectionManager?

        Item item = new Item();
        String sql="nope";
        try {
            sql = SQLScriptor.buildSelectStatement(item);//this should return select item table statemnet
        } catch (MalformedTableException e) {
            FileLogger.getFileLogger().log(e);
        }

        itemRepo.readAllItems(item);
        String whateverMan = mapper.writeValueAsString(itemRepo.readAllItems(item));
        resp.getWriter().print(whateverMan);
        resp.setStatus(202);

    }//end doGet


        //this apparently still works but only with items? or maybe he is just bypassing it entirely???
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfThing = " yup ";
        nameOfThing = readURI(req);
        ObjectMapper mapper = new ObjectMapper();
            //CHANGE this so it gets objects from the database not locally


        switch (nameOfThing){
            case "accessory" ://code for accessory
                Accessory payloadAccessory = mapper.readValue(req.getInputStream(), Accessory.class);
                GlobalStore.setAccessory(payloadAccessory);
                resp.setStatus(202);
                break;
            case "console" ://code for console
                Console payloadConsole = mapper.readValue(req.getInputStream(), Console.class);
                GlobalStore.setConsole(payloadConsole);
                resp.setStatus(202);
                break;
            case "controller" ://code for controller
                Controller payloadController = mapper.readValue(req.getInputStream(), Controller.class);
                GlobalStore.setController(payloadController);
                resp.setStatus(202);
                break;
            case "game" ://code for game
                Game payloadGame = mapper.readValue(req.getInputStream(), Game.class);
                GlobalStore.setGame(payloadGame);
                resp.setStatus(202);
                break;
            case "item":
                Item payloadItem = mapper.readValue(req.getInputStream(), Item.class);
                itemRepo.createItem(payloadItem);
                resp.setStatus(202);
                break;
            default://code for default
                resp.setStatus(501);
                break;
        }
    }


    //TODO implement this
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }


    //TODO implement this
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }


    private String readURI(HttpServletRequest req){
        String temp = req.getRequestURI();
        String[] uri = temp.split("/");
        return uri[uri.length-1];
    }
}
