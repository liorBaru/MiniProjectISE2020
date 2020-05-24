package main.ExternalSystems;

public class TaxProxy implements TaxSystem {
    private static TaxSystem object;
    @Override
    public double getTaxRate(double revenueAmount) {
        if(object == null)
        {
            object = new TaxImplement();
        }
        return object.getTaxRate(revenueAmount);
     }
}
