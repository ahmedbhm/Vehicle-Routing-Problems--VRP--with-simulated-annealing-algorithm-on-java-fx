/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author bhm
 */
public class TourCollection {
    ArrayList<Tour> collection =new ArrayList();
    
    public TourCollection(){
        collection.clear();
    }
    
    //another Constructor
    //starts a tour from another tour
    @SuppressWarnings("unchecked")
	public TourCollection(ArrayList<Tour> collection){
        this.collection.clear();
        for(int i=0;i<collection.size();i++){
            Tour t=new Tour(collection.get(i).getTour());
            this.collection.add(t);
        }
    }
    public void intialSolution(){
        int i=1;
        int capacité=0;
        Tour t=new Tour();
        while(i<Functions.listCustomer.size()){
            if(capacité<=200 && capacité+Functions.listCustomer.get(i).getDEMAND()<200){
               t.add(Functions.listCustomer.get(i));
               capacité+=Functions.listCustomer.get(i).getDEMAND();
               i++;
            }else{
                capacité=0;
                this.collection.add(t);
                t=new Tour();
            }
        }
        this.collection.add(t);
    }
    public void GenerateSolution(Tour OneTour){
        int i=0;
        int capacité=0;
        Tour t=new Tour();
        while(i<OneTour.tourSize()){
            if(capacité<=200 && capacité+OneTour.getCustomer(i).getDEMAND()<200){
               t.add(OneTour.getCustomer(i));
               capacité+=OneTour.getCustomer(i).getDEMAND();
               i++;
            }else{
                capacité=0;
                this.collection.add(Functions.RS(t));
                t=new Tour();
            }
        }
        this.collection.add(Functions.RS(t));
    }
    public int getSize(){
        return this.collection.size();
    }
    public void addTour(Tour t){
        collection.add(t);
    }
    public Tour getTour(int index){
        return collection.get(index);
    }
    public int getTotalDistance(){
        int somme=0;
        for(int i=0;i<getSize();i++){
            somme+=collection.get(i).getTotalDistance();
        }
        return somme;
    }

    public ArrayList<Tour> getCollection() {
        return collection;
    }
    public boolean respectCapacité(){
        for(int i=0;i<this.getSize();i++){
            if(this.getTour(i).getTotalCapacité()>200){
                return false;
            }
        }
        return true;
    }
    public void showSolution(){
        for(int i=0;i<getSize();i++){
            System.out.println("---------tour------->"+i);
              System.out.println(this.collection.get(i));
        }
    }
    
}