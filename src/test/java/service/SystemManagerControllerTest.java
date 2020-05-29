package service;

public class SystemManagerControllerTest
{

/**

    System system;
    SystemManager systemManager;
    Fan fan ;
    @Before
    public void setUp()
    {
        try
        {
            system = System.getInstance();
            fan = system.createNewFanUser("marko","MarkoPolo","markO12345");
            systemManager=system.createNewSysteamManager("systemMnager11","sPois9fff","amnon");
        }
        catch (Exception e)
        {

        }
        try {
            system=System.getInstance();
            systemManager = (SystemManager) system.login("systemMnager11", "sPois9fff");
            fan =(Fan)system.login("MarkoPolo", "markO12345");
        }
        catch (Exception e)
        {

        }
    }
    @Test
    public void removeUserIntegration4() throws Exception {
        String message="";
        try
        {
            systemManager.removeUserFromSystem(fan.getAccount().getUserName());
            fan =(Fan)system.login("MarkoPolo", "markO12345");
        }
        catch (Exception e)
        {
            message=e.getMessage();
        }
        boolean flag=message.equals(" wrong userName or password, please try again");
        assertTrue(flag);
        fan = system.createNewFanUser("marko","MarkoPolo","markO12345");
    }

    @Test
    public void closeTeamIntegration1() throws Exception
    {
        Owner owner = system.createNewOwnerUser("owner","Owner1234","ownerCheck1");
        system.createTeam(owner.getAccount().getUserName(),"teamToClose");
        systemManager.closeTeam("teamToClose");
        assertTrue(owner.getTeam().isStatus()==false);
    }

    @Test
    @Category({RegressionTests.class, IntegrationTests.class})
    public void answerComplaintIntegratio2()
    {
        try
        {

            fan.fillingComplaint("complaint");
            Set set = systemManager.watchComplaints().entrySet();
            String answer ="answer";
            Iterator it = set.iterator();
            while(it.hasNext())
            {
                Map.Entry<Integer,String> entry=(Map.Entry<Integer, String>) it.next();
                systemManager.answerComplaint(entry.getKey(),answer);
            }
            boolean flag=false;
            for (Notification notifcation:fan.readNotification())
            {
                if(notifcation.getDetails().equals("System answer: "+answer))
                {
                    flag=true;
                }
            }
            assertTrue(flag);
            fan.readNotification();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    @Test
    public void getNewComplaintsIntegration3()
    {
        try
        {
            fan.fillingComplaint("complaint");
            assertTrue( systemManager.watchComplaints().isEmpty()==false);
            Set set = systemManager.watchComplaints().entrySet();
            Iterator it = set.iterator();
            boolean flag=false;
            while(it.hasNext())
            {
               Map.Entry<Integer,String> entry=(Map.Entry<Integer, String>) it.next();
               if(entry.getValue().equals("complaint"))
               {
                   flag=true;
               }
            }
            assertTrue(flag);
        }
        catch (Exception e)
        {

        }


    }
    **/
}