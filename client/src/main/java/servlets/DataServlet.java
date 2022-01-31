package servlets;

import utils.DataObject;
import utils.GlobalStore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataObject obj = GlobalStore.getDataObject();
        ObjectMapper mapper = new ObjectMapper();
        String JSON = mapper.writeValueAsString(obj);
        resp.getWriter().print(JSON);
        resp.setStatus(200);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        DataObject payload = mapper.readValue(req.getInputStream(), DataObject.class);
        GlobalStore.setDataObject(payload);
        resp.setStatus(202);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
