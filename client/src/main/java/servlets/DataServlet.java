package servlets;

import pojos.*;
import repos.ItemRepo;
import utils.FileLogger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/*")
public class DataServlet extends HttpServlet {
    private ItemRepo itemRepo;
    

    /**
     * Initializes the repo for the servlet.
     */
    @Override
    public void init() {
        // new repo
        itemRepo = new ItemRepo();
    }

    /**
     * Performs the HTTP GET verb.
     * @param req   request body
     * @param resp  response body
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String nameOfThing = " yup ";
            nameOfThing = readURI(req);
            ObjectMapper mapper = new ObjectMapper();

            switch (nameOfThing){
                case "item"://code for item
                    Item item = itemRepo.readItem(new Item());
                    String JSONitem = mapper.writeValueAsString(item);
                    resp.getWriter().print(JSONitem);
                    resp.setStatus(200);
                    break;
                default://code for default
                    resp.setStatus(501);
                    break;
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }


    /**
     * Performs the HTTP POST verb.
     * @param req   request body
     * @param resp  response body
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String nameOfThing = " yup ";
            nameOfThing = readURI(req);
            ObjectMapper mapper = new ObjectMapper();

            System.out.println(nameOfThing);

            switch (nameOfThing){
                case "item":
                    Item payloadItem = mapper.readValue(req.getInputStream(), Item.class);
                    itemRepo.updateItem(payloadItem);
                    resp.setStatus(200);
                    break;
                default://code for default
                    resp.setStatus(501);
                    break;
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }


    /**
     * Performs the HTTP DELETE verb.
     * @param req   request body
     * @param resp  response body
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // retrieve the context name from the URI
            String contextName = readURI(req);

            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            switch (contextName){
                // if the context refers to an item
                case "item":
                    // create the payload
                    Item payloadItem = mapper.readValue(req.getInputStream(), Item.class);

                    // call delete via the respective repo
                    itemRepo.deleteItem(payloadItem);

                    // make sure the status is OK
                    resp.setStatus(200);

                    break;

                // if the context reference is not implemented
                default:
                    resp.setStatus(501);
                    break;
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }


    /**
     * Performs the HTTP PUT verb.
     * @param req   request body
     * @param resp  response body
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // retrieve the context name from the URI
            String contextName = readURI(req);

            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            switch (contextName){
                // if the context refers to an item
                case "item":
                    // create the payload
                    Item payloadItem = mapper.readValue(req.getInputStream(), Item.class);

                    // call delete via the respective repo
                    itemRepo.updateItem(payloadItem);

                    // make sure the status is OK
                    resp.setStatus(200);
                    
                    break;

                // if the context reference is not implemented
                default:
                    resp.setStatus(501);
                    break;
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }


    /**
     * Helper function that parses the URI for switching between contexts dynamically.
     * @param req request body
     * @return returns the final part of the URI
     */
    private String readURI(HttpServletRequest req){
        String temp = req.getRequestURI();
        String[] uri = temp.split("/");
        return uri[uri.length - 1];
    }
}
