/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Functions {
    public static ArrayList<Customer> listCustomer=new ArrayList<Customer>();
    public static ArrayList<Customer> read_file(String inFile) throws FileNotFoundException, IOException{
      InputStream flux =new FileInputStream(inFile);
      InputStreamReader lecture=new InputStreamReader(flux);
      BufferedReader buff=new BufferedReader(lecture);
      String ligne;
      int i=1;
      ArrayList<Customer> list=new ArrayList<Customer>();
      while (((ligne = buff.readLine()) != null)&&i<111) {
         if(i>9){
             list.add(i-10, extract_digits(ligne,7));
         }
         i++;
      }
      return list;
    }
    public static int[][] ArrayListToMatrix(ArrayList<Customer> list,int row,int col){
        int[][] matrix=new int[row][col];
        for(int i=0;i<row;i++){
                matrix[i][0]=list.get(i).getCUST_NO();
                matrix[i][1]=list.get(i).getXCOORD();
                matrix[i][2]=list.get(i).getYCOORD();
                matrix[i][3]=list.get(i).getDEMAND();
                matrix[i][4]=list.get(i).getREADY_TIME();
                matrix[i][5]=list.get(i).getDUE_DATE();
                matrix[i][6]=list.get(i).getSERVICE_TIME();
        }
        return matrix;
    }
    public static Customer extract_digits(String ligne,int nbr_col){
      int[] digits=new int[nbr_col];
      String[] part=ligne.split(" ");
      int j=0;
      for(int i=0;i<part.length;i++){
          if(part[i].matches("\\S+")){
             digits[j]=Integer.parseInt(part[i]);
             j++;
          }    
      }
      return new Customer(digits[0],digits[1],digits[2],digits[3],digits[4],digits[5],digits[6]);
   }
    
    public static void print_matrix(int[][] matrix,int row,int col){
        for(int i=0;i<row;i++){
          for(int j=0;j<col;j++){
            System.out.print(matrix[i][j]+"    ");
          }
          System.out.println("\n");
        }
        
    }
    public static double euclideanDistance(double x1,double y1,double x2,double y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    
    public static double[][] euclideanDistanceMartrix(int[][] data_matrix){
        double[][] matrix=new double[101][101];
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                matrix[i][j]=euclideanDistance(data_matrix[i][1],data_matrix[i][2],data_matrix[j][1],data_matrix[j][2]);
            }
        }
        return matrix;
    }
    
    public static int randomInt(int min , int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int)d;
	}
    /**
	 * this method returns a random number bin 0 w 1 
    */
    public static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}
		
	/**
	 * Calculates the acceptance probability
	 * @param currentDistance the total distance of the current tour
	 * @param newDistance the total distance of the new tour
	 * @param temperature the current temperature
	 * @return value the probability of whether to accept the new tour
	 */
	public static double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
		// If the new solution is better, accept it
		if (newDistance < currentDistance) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((currentDistance - newDistance) / temperature);
	}
        
        public static Tour RS(Tour t){
          int itr=0;
          double temp = 10000;
          double coolingRate = 0.003;
          Tour currentSolution = new Tour(t.getTour());
          currentSolution.generateIndividual();
          Tour best = new Tour(currentSolution.getTour());
          while (temp > 1 ) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // Get random positions in the tour
            int tourPos1 = Functions.randomInt(1 , newSolution.tourSize());
            int tourPos2 = Functions.randomInt(1 , newSolution.tourSize());
            
            //to make sure that tourPos1 and tourPos2 are different
    		while(tourPos1 == tourPos2) {tourPos2 = Functions.randomInt(1 , newSolution.tourSize());}

            // Get the cities at selected positions in the tour
            Customer customerSwap1 = newSolution.getCustomer(tourPos1);
            Customer customerSwap2 = newSolution.getCustomer(tourPos2);

            // Swap them
            newSolution.setCustomer(tourPos2, customerSwap1);
            newSolution.setCustomer(tourPos1, customerSwap2);
            
            // Get energy of solutions
            int currentDistance   = currentSolution.getTotalDistance();
            int neighbourDistance = newSolution.getTotalDistance();

            // Decide if we should accept the neighbour
            double rand = Functions.randomDouble();
            if (Functions.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getTotalDistance() < best.getTotalDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            if(itr>=10){
                temp *= 1 - coolingRate;
                itr=0;
            }
            itr++;
            
        }
        return best;
        }
        
        public static Tour OneTour(TourCollection c){
            Tour T=new Tour();
            T.getTour().clear();
            for(int i=0;i<c.getSize();i++){
                for(int j=1;j<c.getTour(i).getTour().size();j++){
                    T.add(c.getTour(i).getCustomer(j));
                }
            }
            return T;
        }
        
        public static boolean permutation(TourCollection c,int tp1,int tp2,int cp1,int cp2){
            int d1=c.getTour(tp1).getCustomer(cp1).getDEMAND();
            int d2=c.getTour(tp2).getCustomer(cp2).getDEMAND();
            if((c.getTour(tp1).getTotalCapacité()-d1+d2)<=200 && (c.getTour(tp2).getTotalCapacité()-d2+d1)<=200){
                return true;
            }
            return false;
        }
        public static int bestPosition(Tour tour,Customer customer){
        int best=1;
        Tour t=new Tour(tour.getTour());
        t.add(1, customer);
        int bestDist=t.getTotalDistance();
        t.deleteCustomer(1);
        for(int i=2;i<tour.tourSize()+1;i++){
          Tour ti=new Tour(tour.getTour());
          t.add(i, customer);
          int Disti=t.getTotalDistance();
          if(Disti<bestDist){
              bestDist=Disti;
              best=i;
          }
          t.deleteCustomer(i);
        }
        return best;
    }
}