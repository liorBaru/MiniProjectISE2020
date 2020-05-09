package main.DB;

import main.domain.managePages.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class pageDaoSql implements DaoSql<Page>
{

    @Override
    public Optional<Page> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Page> getAll() {
        return null;
    }

    @Override
    public void save(Page page) throws SQLException {

    }

    @Override
    public void update(Page page, String[] params) {

    }

    @Override
    public void delete(Page page) {

    }
}
