package servlets;

import pojos.*;
import utils.DataObject;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfThing = " yup ";
        nameOfThing = readURI(req);
        ObjectMapper mapper = new ObjectMapper();

        switch (nameOfThing){
            case "accessory" ://code for accessory
                //Accessory payload = mapper.readValue(req.getInputStream(), Accessory.class);
                Accessory acc = GlobalStore.getAccessory();
                String JSONaccessory = mapper.writeValueAsString(acc);
                resp.getWriter().print(JSONaccessory);
                resp.setStatus(202);
                break;
            case "console" ://code for console. just copy accessory
                Console con = GlobalStore.getConsole();
                String JSONconsole = mapper.writeValueAsString(con);
                resp.getWriter().print(JSONconsole);
                resp.setStatus(202);
                break;
            case "controller" ://code for controller
                Controller cont = GlobalStore.getController();
                String JSONcontroller = mapper.writeValueAsString(cont);
                resp.getWriter().print(JSONcontroller);

                resp.setStatus(202);
                break;
            case "game" ://code for game
                Game game = GlobalStore.getGame();
                String JSONgame = mapper.writeValueAsString(game);
                resp.getWriter().print(JSONgame);
                resp.setStatus(202);
                break;
            default://code for default
                resp.setStatus(501);
                break;
        }
        /*
        DataObject obj = GlobalStore.getDataObject();
        String JSON = mapper.writeValueAsString(obj);
        resp.getWriter().print(JSON);
        resp.setStatus(200);
    */
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfThing = " yup ";
        nameOfThing = readURI(req);
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(nameOfThing);

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
            default://code for default
                resp.setStatus(501);
                break;
        }

        //DataObject payload = mapper.readValue(req.getInputStream(), DataObject.class);
        //GlobalStore.setDataObject(payload);

    }
    private String readURI(HttpServletRequest req){
        String temp = req.getRequestURI();
        String[] uri = temp.split("/");
        return uri[uri.length-1];
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
