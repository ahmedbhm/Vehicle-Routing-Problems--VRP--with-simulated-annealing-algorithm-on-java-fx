package functions;

public class Customer {
 private int CUST_NO;
 private int XCOORD;
 private int YCOORD;
 private int DEMAND;
 private int READY_TIME;
 private int DUE_DATE;
 private int SERVICE_TIME;
   public Customer(Customer c){
    this.CUST_NO=c.CUST_NO;
    this.XCOORD=c.XCOORD;
    this.YCOORD=c.YCOORD;
    this.DEMAND=c.DEMAND;
    this.READY_TIME=c.READY_TIME;
   this.DUE_DATE=c.DUE_DATE;
  this.SERVICE_TIME=c.SERVICE_TIME;
  }
  Customer(int CUST_NO,int XCOORD,int YCOORD,int DEMAND,int READY_TIME,int DUE_DATE,int SERVICE_TIME){
  this.CUST_NO=CUST_NO;
  this.XCOORD=XCOORD;
  this.YCOORD=YCOORD;
  this.DEMAND=DEMAND;
  this.READY_TIME=READY_TIME;
  this.DUE_DATE=DUE_DATE;
  this.SERVICE_TIME=SERVICE_TIME;
 }

    public int getCUST_NO() {
        return CUST_NO;
    }

    public int getXCOORD() {
        return XCOORD;
    }

    public int getYCOORD() {
        return YCOORD;
    }

    public int getDEMAND() {
        return DEMAND;
    }

    public int getREADY_TIME() {
        return READY_TIME;
    }

    public int getDUE_DATE() {
        return DUE_DATE;
    }

    public int getSERVICE_TIME() {
        return SERVICE_TIME;
    }

    public void setCUST_NO(int CUST_NO) {
        this.CUST_NO = CUST_NO;
    }

    public void setXCOORD(int XCOORD) {
        this.XCOORD = XCOORD;
    }

    public void setYCOORD(int YCOORD) {
        this.YCOORD = YCOORD;
    }

    public void setDEMAND(int DEMAND) {
        this.DEMAND = DEMAND;
    }

    public void setREADY_TIME(int READY_TIME) {
        this.READY_TIME = READY_TIME;
    }

    public void setDUE_DATE(int DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public void setSERVICE_TIME(int SERVICE_TIME) {
        this.SERVICE_TIME = SERVICE_TIME;
    }

    @Override
    public String toString() {
        return "Customer{" + "CUST_NO=" + CUST_NO + ", XCOORD=" + XCOORD + ", YCOORD=" + YCOORD + ", DEMAND=" + DEMAND + ", READY_TIME=" + READY_TIME + ", DUE_DATE=" + DUE_DATE + ", SERVICE_TIME=" + SERVICE_TIME + '}';
    }
  
}
