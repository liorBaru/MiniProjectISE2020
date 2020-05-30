package Logger;

import domain.manageEvents.Notification;

import java.util.Observable;

public class NotificationSystem extends Observable
{
     public void sendNotificationToUser(String userName)
     {
         if(userName.isEmpty()==false)
         {
            setChanged();
            notifyObservers(userName);
         }
     }
}
