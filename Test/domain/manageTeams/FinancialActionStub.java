package domain.manageTeams;

import main.domain.Asset.BoardMember;
import main.domain.manageTeams.FinancialAction;

public class FinancialActionStub extends FinancialAction {

    public FinancialActionStub(BoardMember boardMember) {
        super("Game revenue",5000, boardMember);
    }
}
