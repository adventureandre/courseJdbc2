package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st =  null;

        try {
            conn = DB.getConnection();
            st =  conn.prepareStatement(
                            "INSERT INTO seller "
                                    +"(Name,Email,BitthDate,BaseSalary,DepartmentId)"
                    +"VALUES "
                    +"(?,?,?,?,?)");

            st.setString(1,"Andre Luiz");
            st.setString(2,"adventureandre@hotmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1989").getTime()));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: "+rowsAffected);

        }catch (SQLException e){
           e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
