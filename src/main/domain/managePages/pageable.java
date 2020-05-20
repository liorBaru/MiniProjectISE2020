package main.domain.managePages;

import java.sql.SQLException;

public interface pageable
{
    void uploadDataToPage(String data) throws Exception;
    String getPageOwnerName();

}
