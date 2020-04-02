package Models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

public class clientsData extends RecursiveTreeObject<clientsData> {

    private SimpleStringProperty id, full_name, address, date_registered;

    public clientsData(String id, String full_name, String address, String date_registered) {

        this.id = new SimpleStringProperty(id);
        this.full_name = new SimpleStringProperty(full_name);
        this.address = new SimpleStringProperty(address);
        this.date_registered = new SimpleStringProperty(date_registered);

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFull_name() {
        return full_name.get();
    }

    public SimpleStringProperty full_nameProperty() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name.set(full_name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getDate_registered() {
        return date_registered.get();
    }

    public SimpleStringProperty date_registeredProperty() {
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered.set(date_registered);
    }
}
