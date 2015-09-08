package cc.catalysts.angular.demo.repository;

import cc.catalysts.angular.demo.entity.Country;
import cc.catalysts.angular.demo.entity.FacetTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Page<Country> findByNameStartingWith(String name, Pageable pageRequest);

    @Query("select new cc.catalysts.angular.demo.entity.FacetTerm(substring(c.name,1,1), count(c)) from Country c group by substring(c.name,1,1)")
    List<FacetTerm> findFacetTerms();
}
