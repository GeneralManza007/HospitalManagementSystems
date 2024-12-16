import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import java.util.*;
//import java.io.*;



public class HospitalManagementSystem extends Application {

    public static void main(String [] args){

        launch(args);
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Care Sync Portal");

        Button PaitentButton = new Button("Paitent");
        PaitentButton.setOnAction(event -> {

            Paitent.paitent();
            Platform.exit();

        });

        Button ReceptionistButton = new Button("Receptionist");
        ReceptionistButton.setOnAction(event -> {

            Receptionist.receptionist();
            Platform.exit();

        });

        Button DoctorButton = new Button("Doctor");
        DoctorButton.setOnAction(event -> {

            Doctor.doctor();
            Platform.exit();

        });
        

        Button adminButton = new Button("Admin");
        adminButton.setOnAction(event -> {

            Admin.AdminUser();
            Platform.exit();
        
        } );

        BorderPane layout = new BorderPane();

        layout.setCenter(createButtonPane(PaitentButton, ReceptionistButton, DoctorButton, adminButton));

        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();

        Duration duration = Duration.seconds(20);
        KeyFrame keyFrame = new KeyFrame(duration, event -> Platform.exit());
        Timeline timeline = new Timeline(keyFrame);
        timeline.play(); 


      
    }

    private BorderPane createButtonPane(Button Paitent, Button Receptionist, Button Doctor, Button Admin) {

        BorderPane buttonPane = new BorderPane();

        Label welcomeLabel = new Label("Welcome to the care Sync Portal");
        Label InstructLabel = new Label("Please choose which page to visit according to your clearance.");

        VBox buttonsVBox = new VBox(10, Paitent,Receptionist,Doctor,Admin);
        buttonsVBox.setAlignment(Pos.CENTER);

        VBox labelsVbox = new VBox(10, welcomeLabel, InstructLabel);
        labelsVbox.setAlignment(Pos.CENTER);


        buttonPane.setTop(labelsVbox);
        buttonPane.setCenter(buttonsVBox);
        return buttonPane;

    }




    /*public static void HospitalSystem(){
        Scanner scanner = new Scanner(System.in);

        boolean StartSystem = true;

        while(StartSystem) {   

                System.out.println();
                System.out.println("Please choose which page to visit according to your clearance.");
                System.out.println();
                System.out.println("1. Paitent");
                System.out.println("2. Receptionist");
                System.out.println("3. Doctor");
                System.out.println("4. Admin");
                System.out.println();

                System.out.println("Select: ");
                int Choice = scanner.nextInt();
                scanner.nextLine();
 
                switch(Choice) {



                }

                
        }

    }*/
    
}
