package cc.catalysts.angular.demo.repository;

import cc.catalysts.angular.demo.entity.CarModel;
import cc.catalysts.angular.demo.entity.Industry;
import cc.catalysts.angular.demo.entity.Variation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mustafa on 06.08.2015.
 */
@Repository
public interface VariationRepository extends JpaRepository<Variation, Long> {

    Page<Variation> findAllByCarModel(CarModel carModel, Pageable pageable);
}