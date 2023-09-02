package gob.gt.infom.repositories;

import java.util.List;
 
import gob.gt.infom.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {

  public List<Menu> findAllByOrderByOrdenAsc();

}
