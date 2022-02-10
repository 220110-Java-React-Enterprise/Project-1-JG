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
                    Item item = new Item();
                    String JSONitem = mapper.writeValueAsString(item);
                    resp.getWriter().print(JSONitem);
                    resp.setStatus(202);
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
                    itemRepo.createItem(payloadItem);
                    resp.setStatus(202);
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
    //TODO implement this
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {

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
    //TODO implement this
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {

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
