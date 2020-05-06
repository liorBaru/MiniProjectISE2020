package main.domain.manageTeams;

import main.domain.Asset.BoardMember;

import java.util.Date;

public class FinancialAction
{
    private String description;
    private Date date;
    private double price;
    private BoardMember boardMember;

    public FinancialAction(String description, double price, BoardMember boardMember)
    {
        this.description=description;
        this.date= new Date();
        this.price=price;
        this.boardMember=boardMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object)
    {
        FinancialAction financialAction = (FinancialAction)object;
        if(financialAction.getDate().equals(this.getDescription())&&
                financialAction.getDescription().equals(this.getDescription()))
        {
            return true;
        }
        return false;
    }
}
