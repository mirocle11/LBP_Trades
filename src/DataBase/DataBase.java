package DataBase;

import Models.clientsData;
import javafx.collections.ObservableList;

import java.sql.*;
import javax.swing.*;

public class DataBase {

    private static final String URL = "jdbc:mysql://localhost:3307/lbp_trades_db?zeroDateTimeBehavior=convertToNull";
    private static Connection con;

    //declare instance
    private static DataBase instance = null;
    public static DataBase getInstance(){
        if(instance == null){
            instance = new DataBase();
        }
        return instance;
    }

    //connect to db
    private DataBase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, "root", "password");
            System.out.println("Successfully connected to mysql database!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //client side
    public void addClient(String full_name, String address, String date) {

        String sql = "INSERT INTO clients_tbl (FULL_NAME, ADDRESS, DATE_REGISTERED) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null,full_name + " Client saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayClients(ObservableList clientsData) {
        try {
            Statement stmt = con.createStatement();
            String sql= "SELECT * FROM clients_tbl";
            ResultSet rs;
            rs = stmt.executeQuery(sql);

            while(rs.next()) {

                String ID = rs.getString("ID");
                String FULL_NAME = rs.getString("FULL_NAME");
                String ADDRESS = rs.getString("ADDRESS");
                String DATE_REGISTERED = rs.getString("DATE_REGISTERED");

                clientsData.addAll(new clientsData(ID, FULL_NAME, ADDRESS, DATE_REGISTERED));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
