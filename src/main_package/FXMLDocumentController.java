/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import functions.Customer;
import functions.Functions;
import functions.Tour;
import functions.TourCollection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author bhm
 */
public class FXMLDocumentController implements Initializable {
    TourCollection best,currentSolution ;
    @FXML
    private Label label1;
    @FXML
    private Label label2,labelbest,labelcurrent,labeltemp,labelV;
    @FXML
    private TableView<Customer> table;
    @FXML
    private Pane panel;
    @FXML
    private TextField inputBrows;
    ArrayList<Customer> list=new ArrayList<Customer>();
    @FXML
    private void ReadFile() {
        if(inputBrows.getText().equals("")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No file selected !");
            alert.showAndWait();
        }else{
            Functions f=new Functions();
            try {
               this.list=f.read_file(inputBrows.getText());
               Functions.listCustomer=Functions.read_file(inputBrows.getText());
            } catch (IOException ex) {
               Logger.getLogger(TP_MH_FX.class.getName()).log(Level.SEVERE, null, ex);
            }
            //CUST NO.   XCOORD.    YCOORD.    DEMAND   READY TIME   DUE DATE   SERVICE TIME
            arrayListTotableView(list,101,7);
            label1.setText("VEHICLE NUMBER : 25");
            label2.setText("CAPACITY : 200");
        }
    }
    @FXML
    private void arrayListTotableView(ArrayList<Customer> list,int row,int col){
        TableColumn<Customer,Integer> c1 = new TableColumn("CUST_NO");
        c1.setCellValueFactory(new PropertyValueFactory<>("CUST_NO"));
        TableColumn<Customer,Integer> c2 = new TableColumn("XCOORD");
        c2.setCellValueFactory(new PropertyValueFactory<>("XCOORD"));
        TableColumn<Customer,Integer> c3 = new TableColumn("YCOORD");
        c3.setCellValueFactory(new PropertyValueFactory<>("YCOORD"));
        TableColumn<Customer,Integer> c4 = new TableColumn("DEMAND");
        c4.setCellValueFactory(new PropertyValueFactory<>("DEMAND"));
        TableColumn<Customer,Integer> c5 = new TableColumn("READY_TIME");
        c5.setCellValueFactory(new PropertyValueFactory<>("READY_TIME"));
        TableColumn<Customer,Integer> c6 = new TableColumn("DUE_DATE");
        c6.setCellValueFactory(new PropertyValueFactory<>("DUE_DATE"));
        TableColumn<Customer,Integer> c7 = new TableColumn("SERVICE_TIME");
        c7.setCellValueFactory(new PropertyValueFactory<>("SERVICE_TIME"));
        
        table=new TableView();
        ObservableList<Customer> Customers=FXCollections.observableArrayList(list);
        table.setItems(Customers);
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7);
        panel.getChildren().clear();
        panel.getChildren().addAll(table);
        table.setPrefSize(panel.getPrefWidth(),panel.getPrefHeight());
        c1.setPrefWidth(panel.getPrefWidth()/7);
        c2.setPrefWidth(panel.getPrefWidth()/7);
        c3.setPrefWidth(panel.getPrefWidth()/7);
        c4.setPrefWidth(panel.getPrefWidth()/7);
        c5.setPrefWidth(panel.getPrefWidth()/7);
        c6.setPrefWidth(panel.getPrefWidth()/7);
        c7.setPrefWidth(panel.getPrefWidth()/7);
    }
    
    @FXML
    private void showEuclidianDist(){
       if(this.list.size()<1){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("read file !");
            alert.showAndWait();
        }else{
         panel.getChildren().clear();
         //afficher les cercle
         Functions f=new Functions();
         double[][] dist=f.euclideanDistanceMartrix(f.ArrayListToMatrix(list, 101, 7));
         for(int i=0;i<101;i++){
          for(int j=0;j<101;j++){
            System.out.print(dist[i][j]+"    ");
          }
          System.out.println("\n");
        }
        }
    }
    @FXML
    private void fileShooser(ActionEvent event){
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Open Resource File");
       fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
       Window stage = null;
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
       File selectedFile=fileChooser.showOpenDialog(stage);
       if (selectedFile != null) {
        inputBrows.setText(selectedFile.getAbsolutePath());
        ReadFile();
       }
    }
    @FXML
    private void printLine() throws InterruptedException{
       panel.getChildren().clear();
        //afficher les cercle
        for(int i=0;i<list.size();i++){
            double x=list.get(i).getXCOORD()*1004/100;
            double y=list.get(i).getYCOORD()*596/100;
            if(i==0){
                Rectangle r =new Rectangle(x, y, 20, 20);
                r.setStroke(Paint.valueOf("#fc003a"));
                 panel.getChildren().add(r);
            }else{
                panel.getChildren().add(new Circle(x,y,2,Paint.valueOf("#5B6BC0")));
            }
            
        }
        //afficher les numero des client
        for(int i=0;i<list.size();i++){
            double x=list.get(i).getXCOORD()*1004/100;
            double y=list.get(i).getYCOORD()*596/100;
            if(i==0){
               panel.getChildren().add(new Text(x-2,y-2,"Depot"));
            }else{
                Text t=new Text(x-3.5,y-3.5,"C "+i);
                t.setStyle("-fx-font-size:10px;-fx-color:green;");
                panel.getChildren().add(t);
            }
            
        }
    }
    public void SA() throws InterruptedException, IOException{
      // String inFile="C:\\Users\\bhm\\Documents\\NetBeansProjects\\TP_MH\\src\\instances VRP\\c101.txt";
       //Functions.listCustomer=Functions.read_file(inFile);
        currentSolution = new TourCollection();
        currentSolution.intialSolution();
        System.out.println("Total vehicul of initial solution: " + currentSolution.getSize());
        System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());
        currentSolution.showSolution();
        
        double temp = 1000;
        double coolingRate = 0.003;
        int itr=0;
        this.best = new TourCollection(currentSolution.getCollection());
          Platform.runLater(new Runnable() {
            public void run() {
                panel.getChildren().clear();
                for(int i=0;i<best.getSize();i++){
                    best.getTour(i).add(best.getTour(i).getTour().get(0));
                    try {
                        representation(best.getTour(i).getTour(),i);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        while (temp > 1) {
            // Create new neighbour tour
            TourCollection newSolution = new TourCollection(currentSolution.getCollection());
            // Get random positions in the tourCollection
            int TourPos1 = Functions.randomInt(0 , newSolution.getSize());
            int TourPos2 = Functions.randomInt(0 , newSolution.getSize());
            // Get random positions in the tour
            int Pos1 = Functions.randomInt(1 , newSolution.getTour(TourPos1).tourSize());
            int Pos2 = Functions.randomInt(1 , newSolution.getTour(TourPos2).tourSize());
            
            //to make sure that tourPos1 and tourPos2 are different
            while(Pos1 == Pos2 && TourPos1==TourPos2) {Pos2 = Functions.randomInt(1 , newSolution.getTour(TourPos2).tourSize());}
            
            // Get the cities at selected positions in the tour
            Customer customerSwap1 = new Customer(newSolution.getTour(TourPos1).getCustomer(Pos1));
            Customer customerSwap2 = new Customer(newSolution.getTour(TourPos2).getCustomer(Pos2));
            
            // Swap them
            if(Functions.permutation(newSolution, TourPos1, TourPos2, Pos1, Pos2)){
                if(TourPos1!=TourPos2){
                newSolution.getTour(TourPos2).deleteCustomer(Pos2);
                int bestPos2=Functions.bestPosition(newSolution.getTour(TourPos2),customerSwap1);
                newSolution.getTour(TourPos2).add(bestPos2,customerSwap1);
                
                newSolution.getTour(TourPos1).deleteCustomer(Pos1);
                int bestPos1=Functions.bestPosition(newSolution.getTour(TourPos1),customerSwap2);
                newSolution.getTour(TourPos1).add(bestPos1, customerSwap2);
                }else{
                   newSolution.getTour(TourPos2).setCustomer(Pos2, customerSwap1);
                   newSolution.getTour(TourPos1).setCustomer(Pos1, customerSwap2);
                }
            }
            
            // Get energy of solutions
            int currentDistance   = currentSolution.getTotalDistance();
            int neighbourDistance = newSolution.getTotalDistance();

            // Decide if we should accept the neighbour
            double rand = Functions.randomDouble();
            if (Functions.acceptanceProbability(currentDistance, neighbourDistance, temp) > 0.5  ) {
                currentSolution = new TourCollection(newSolution.getCollection());
                
            }
            // Keep track of the best solution found
            if (currentSolution.getTotalDistance() < best.getTotalDistance() && currentSolution.respectCapacité()) {
                best = new TourCollection(currentSolution.getCollection());
                Platform.runLater(new Runnable() {
                 public void run() {
                  panel.getChildren().clear();
                  for(int i=0;i<best.getSize();i++){
                  best.getTour(i).add(best.getTour(i).getTour().get(0));
                      try {
                          representation(best.getTour(i).getTour(),i);
                      } catch (InterruptedException ex) {
                          Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                 }
                }
               });
            }
             System.out.println("currentDistance : "+currentDistance+" ----> neighbourDistance: "+neighbourDistance);
             System.out.println("random = "+rand+"->>> probabilité :"+Functions.acceptanceProbability(currentDistance, neighbourDistance, temp));
            
            if(itr>70){
                temp *= 1 - coolingRate;
                itr=0;
            }
            itr++;
            Double t=temp;
            Platform.runLater(new Runnable() {
            public void run() {
                 labelbest.setText(Integer.toString(best.getTotalDistance()));
                 labelcurrent.setText(Integer.toString(currentDistance));
                 labeltemp.setText(Double.toString(t));
                 labelV.setText("Tours Number :  "+Integer.toString(best.getSize()));
                }
               });
        }
        System.out.println("Total vehicul of best solution: " + best.getSize());
        System.out.println("Total distance of best solution: " + best.getTotalDistance());
        best.showSolution();
    }
    @FXML
    private void simulatedAnnealing(ActionEvent event) throws IOException, InterruptedException{
       new Thread() {
                // runnable for that thread
                public void run() {
                    try {  
                        SA();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
    }
    @FXML
    private void representation(ArrayList<Customer> list,int colorN) throws InterruptedException{
        //afficher les cercle
        for(int i=0;i<list.size();i++){
            double x=list.get(i).getXCOORD()*1004/100;
            double y=list.get(i).getYCOORD()*596/100;
            if(i==0){
                Circle r =new Circle(x, y, 7,Paint.valueOf("#ccc"));
                r.setStroke(Paint.valueOf("#fc003a"));
                 panel.getChildren().add(r);
            }else{
                panel.getChildren().add(new Circle(x,y,4,Paint.valueOf("#000000")));
            }
            
        }
        //afficher les lignes
        
        for(int i=0;i<list.size()-1;i++){
            double x=list.get(i).getXCOORD()*1004/100;
            double y=list.get(i).getYCOORD()*596/100;
            double xNext=list.get(i+1).getXCOORD()*1004/100;
            double yNext=list.get(i+1).getYCOORD()*596/100;
            Line line1=new Line(x, y, xNext, yNext);
            line1.setStroke(Paint.valueOf(color(colorN)));
            line1.setStrokeWidth(2);
            panel.getChildren().add(line1);
        } 
        //afficher les numero des client
    }
    public String color(int i){
        String[] color=new String[14];
        color[0]="#fcc118";
        color[1]="#007bff";
        color[2]="#4caf50";
        color[3]="#ee8232";
        color[4]="#43619b";
        color[5]="#f2104d";
        color[6]="#6b3030";
        color[7]="#ff00af";
        color[8]="#00ffff";
        color[9]="#cc8abd";
        color[10]="#ff00af";
        color[11]="#ff00af";
        color[12]="#ff00af";
        color[13]="#ff00af";
        return color[i];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
