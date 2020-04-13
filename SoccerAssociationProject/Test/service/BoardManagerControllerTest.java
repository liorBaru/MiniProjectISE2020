package service;

import domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardManagerControllerTest {
    Owner owner;
    @org.junit.Before
    public void setUp(){
        List<Owner> ownerList=new ArrayList<>();
        owner=new Owner("admin","12341234","Bill","Owner",null,null,null);
        ownerList.add(owner);
        Team team=new Team(ownerList,"M.C");
        owner.setTeam(team);
    }

    @org.junit.Test
    public void addAssets() {
        Field filed=new Field("Tady");
        BoardManagerController bmc = new BoardManagerController();
        bmc.addAssets(filed,owner);
        assertTrue(owner.getTeam().getAssetsOfTeam().contains(filed));
    }

    @org.junit.Test
    public void removeAssets() {
        Field filed=new Field("Tady");
        BoardManagerController bmc = new BoardManagerController();
        assertFalse(owner.getTeam().getAssetsOfTeam().contains(filed));
    }

    @org.junit.Test
    public void testRemoveAssets() {
    }
}