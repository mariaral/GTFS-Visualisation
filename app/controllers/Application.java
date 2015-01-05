package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import java.util.List;
import java.util.ArrayList;

import play.api.libs.json.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application extends Controller {

    static List<String> a;

    public static Result index() {
        a =  new ArrayList<String>();
        query("jdbc:postgresql://localhost:5432/athens","maria","maria");
        query("jdbc:postgresql://localhost:5432/paris","maria","maria");
        query("jdbc:postgresql://localhost:5432/portland","maria","maria");
        System.out.println(a.toString());
        return ok(index.render(a));
    }

    private static void query(String url, String user, String pwd) {
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String stm = "SELECT  trips_per_route()";
            PreparedStatement pst = con.prepareStatement(stm);
            ResultSet rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  stops_per_route()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  duration_per_route()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  distance_per_route_deg()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  distance_per_route()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  distance_per_route_abs()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  trips_per_stop()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            stm = "SELECT  routes_per_stop()";
            pst = con.prepareStatement(stm);
            rs = pst.executeQuery();
            rs.next();
            a.add(rs.getObject(1).toString());
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
