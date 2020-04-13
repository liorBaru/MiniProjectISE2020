package domain;

import java.util.List;

public abstract class  BoardMember extends StaffMember
{
    private BoardMember boss;
    private List<BoardMember> appointments;
    private List<Permission> permissions;

    public BoardMember(String userName, String password, String name, String job, Team team, BoardMember boss) {
        super(userName, password, name, job, team);
        this.boss = boss;
    }
}
