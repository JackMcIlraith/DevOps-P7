package revolut;

public class PaymentService {
    private String serviceName;
    private boolean transactionStatus = true;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public void transactionSuccess(){
        this.transactionStatus =  true;
    }

    public void transactionFail(){
        this.transactionStatus = false;
    }

    public boolean isTransactionStatus(){
        return transactionStatus;
    }
}
