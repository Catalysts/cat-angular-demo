package cc.catalysts.angular.demo.repository;

import cc.catalysts.angular.demo.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Page<Country> findByNameStartingWith(String name, Pageable pageRequest);

    Country findByCode(String code);
}
