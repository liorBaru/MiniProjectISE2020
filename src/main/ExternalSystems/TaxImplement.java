package main.ExternalSystems;

public class TaxImplement implements TaxSystem {

    public TaxImplement() {

    }

    @Override
    public double getTaxRate(double revenueAmount) {
        return 5.5;
    }

}
