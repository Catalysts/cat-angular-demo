package cc.catalysts.angular.demo.repository;

import cc.catalysts.angular.demo.entity.Industry;
import cc.catalysts.angular.demo.entity.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mustafa on 06.08.2015.
 */

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    Page<Industry> findAllByManufacturer(Manufacturer manufacturer, Pageable pageable);
}
