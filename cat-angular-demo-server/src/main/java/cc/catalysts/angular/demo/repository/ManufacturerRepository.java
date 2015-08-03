package cc.catalysts.angular.demo.repository;

import cc.catalysts.angular.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
