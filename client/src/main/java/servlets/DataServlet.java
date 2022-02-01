package servlets;

import pojos.Accessory;
import pojos.Game;
import pojos.Item;
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
                Accessory obj = GlobalStore.getAccessory();
                String JSON = mapper.writeValueAsString(obj);
                resp.getWriter().print(JSON);
                resp.setStatus(202);
                break;
            case "console" ://code for console. just copy accessory
                resp.setStatus(501);
                break;
            case "controller" ://code for controller
                resp.setStatus(501);
                break;
            case "game" ://code for game
                resp.setStatus(501);
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
                Accessory payload = mapper.readValue(req.getInputStream(), Accessory.class);
                GlobalStore.setAccessory(payload);
                resp.setStatus(202);
                break;
            case "console" ://code for console
                resp.setStatus(501);
                break;
            case "controller" ://code for controller
                resp.setStatus(501);
                break;
            case "game" ://code for game
                resp.setStatus(501);
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
