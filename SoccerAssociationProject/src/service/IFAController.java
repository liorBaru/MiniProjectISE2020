package service;

import domain.*;
import domain.System;
import java.util.Date;

/**
 * lior class
 */
public class IFAController {

    public boolean addPlayer(IFA ifa, String pName, Date birthDay,String password, String userName)
    {
        if(ifa!=null && pName!=null && birthDay!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addPlayer(pName, birthDay,password, userName);
            }
        }
        return false;
    }
    public boolean addCoach(IFA ifa, String cName, Date birthDay,String password, String userName)
    {
        if(ifa!=null && cName!=null && birthDay!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addCoach(cName, birthDay,password, userName);
            }
        }
        return false;
    }
    public boolean addRefree(IFA ifa, String rName,Date birthDay,String password, String userName, String type)
    {
        if(ifa!=null && rName!=null && birthDay!=null && password!=null && userName!=null && type!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifa.addRefree(rName,birthDay,password, userName, type);
            }
        }
        return false;
    }
    public boolean addIFA(IFA ifaManager, String ifaName, String password, String userName)
    {
        if(ifaManager!=null && ifaName!=null && password!=null && userName!=null){
            if( isPassAndUserNIsLegal(password, userName) )
            {
                ifaManager.addNewIFA( ifaName, password, userName);
            }
        }
        return false;
    }


    /**
     * chech if the user nane and password is legal
     * @param password
     * @param userName
     * @return
     */
    public boolean isPassAndUserNIsLegal(String password, String userName){
        if(password!=null && userName!=null){
            if(userName.length()>=6 && password.length()>=8 ){ //add pass contain capital small and number ??
                return true;
            }
        }
        return false;
    }


}//class
