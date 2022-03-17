package sample;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    Alert display =new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField fname,lname,age,nic,city;
    @FXML
    private ChoiceBox typeOfVaccine;
    @FXML
    private void initialize(){
        typeOfVaccine.setValue("Select option");
        typeOfVaccine.setItems(FXCollections.observableArrayList("AstraZeneca","Sinopharm","Pfizer")); //https://youtu.be/MUw7MHIibBc
    }
    @FXML
    private void buttonpress(){
        try{
            int num=Integer.parseInt(age.getText());
        }
        catch(NumberFormatException e){
            System.out.println("Not an integer");
            return;
        }
        if(fname.getText()==null || lname.getText()==null || age.getText()==null || nic.getText()==null || typeOfVaccine.getValue()=="Select option" || city.getText()==null ) {
            System.out.println("Incomplete");
        }
        else {
            String details;
            details = "First name :"+fname.getText() + '\n' +"Last name :" +lname.getText() + '\n' +"Age :"+ age.getText() + '\n' +"NIC no :" +nic.getText() + '\n' +"City :"+city.getText()+'\n'+"Vaccine Type :" +typeOfVaccine.getValue() + "\n\n"+"Current Date and Time : "+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
            display.setTitle("Details...");
            display.setHeaderText("Patient Details");
            display.setContentText(details);
            display.showAndWait();
        }

    }

}
