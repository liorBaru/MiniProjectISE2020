package ExternalSystems;

public class PaymentProxy implements  PaymentSystem{
    private static PaymentSystem object;

    @Override
    public boolean addPayment(String teamName, String date, double amount) {
        if(object==null){
            object=new PaymentsImplement();
        }
       return object.addPayment(teamName,date,amount);
    }


}
