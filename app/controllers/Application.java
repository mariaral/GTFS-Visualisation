package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import java.util.List;

import play.api.libs.json.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application extends Controller {

    static String a;
    public static Result index() {
        query("jdbc:postgresql://83.212.116.51:5432/athens","maria","maria");
        return ok(index.render(a));
    }

    private static void query(String url, String user, String pwd) {
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            String stm = "SELECT  json_stops()";
            PreparedStatement pst = con.prepareStatement(stm);
            ResultSet rs = pst.executeQuery();
            rs.next();
            a = rs.getObject(1).toString();
            //a = Json.parse(o);
            //System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
