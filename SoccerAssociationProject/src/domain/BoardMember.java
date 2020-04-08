package domain;

import java.util.List;

public class BoardMember extends StaffMember
{
    private BoardMember boss;
    private List<BoardMember> appointments;
    private List<Premission> premissions;

}
