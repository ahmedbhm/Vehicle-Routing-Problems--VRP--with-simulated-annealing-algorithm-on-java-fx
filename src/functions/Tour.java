package functions;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{
    private ArrayList<Customer> tour = new ArrayList<Customer>();
    private int distance ;
    public Tour(){
            tour.add(Functions.listCustomer.get(0));
    }
    public void add(Customer c){
        tour.add(c);
    }
    public void add(int pos,Customer c){
        tour.add(pos, c);
    }
    //another Constructor
    //starts a tour from another tour
    @SuppressWarnings("unchecked")
	public Tour(ArrayList<Customer> tour){
        this.tour = (ArrayList<Customer>) tour.clone();
    }
    
    /**
      Returns tour information
      @return currenttour
     */
    public ArrayList<Customer> getTour(){
        return tour;
    }
     
    /**
     * Creates a random tour (i.e. individual or candidate solution)
     */
    public void generateIndividual() {
        Collections.shuffle(tour);
    }

    public Customer getCustomer(int index) {
        return tour.get(index);
    }

    public void setCustomer(int index, Customer customer) {
        tour.set(index, customer);
    }
    public void deleteCustomer(int index){
        tour.remove(index);
    }
    
    /**
     * Computes and returns the total distance of the tour
     * @return distance total distance of the tour
     */
    public int getTotalDistance(){
        int tourDistance=0;
            for (int i=0; i < tourSize(); i++) {
                Customer fromCustomer = getCustomer(i);
                Customer destinationCustomer;
                if(i+1 < tourSize()){
                    destinationCustomer = getCustomer(i+1);
                }
                else{
                    destinationCustomer = Functions.listCustomer.get(0);
                }                
                tourDistance += Functions.euclideanDistance(fromCustomer.getXCOORD(),fromCustomer.getYCOORD(),destinationCustomer.getXCOORD() ,destinationCustomer.getYCOORD()); 
            }
        return tourDistance;
    }
    public int getTotalCapacité(){
       int c=0;
       for(int i=0;i<tourSize();i++){
           c+=tour.get(i).getDEMAND();
       }
       return c;
    }
    public int tourSize() {
        return tour.size();
    }
    
   @Override
    public String toString() {
        String s = Integer.toString(getCustomer(0).getCUST_NO());
        for (int i = 1; i < tourSize(); i++) {
            s += " -> " + getCustomer(i).getCUST_NO();
        }
        return s+"  capacité= "+this.getTotalCapacité()+"   distance= "+this.getTotalDistance();
    }
}
    
