package domain.manageTeams;

import domain.Asset.BoardMember;

public class FinancialActionStub extends FinancialAction {

    public FinancialActionStub(BoardMember boardMember) {
        super("Game revenue",5000, boardMember);
    }
}
