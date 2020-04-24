package domain.manageTeams;

import domain.Asset.BoardMember;
import domain.Asset.Owner;
import domain.Asset.OwnerStub;

public class FinancialActionStub extends FinancialAction {

    public FinancialActionStub(BoardMember boardMember) {
        super("Game revenue",5000, boardMember);
    }
}
