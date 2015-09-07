package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CountryDto;
import cc.catalysts.angular.demo.entity.Country;
import cc.catalysts.angular.demo.entity.FacetTerm;
import cc.catalysts.angular.demo.repository.CountryRepository;
import cc.catalysts.angular.demo.service.CountryService;
import cc.catalysts.angular.demo.web.controller.api.CountrySearchRequest;
import cc.catalysts.angular.spring.dto.FacetDto;
import cc.catalysts.angular.spring.dto.FacetTermDto;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.dto.PageWithFacetDto;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import cc.catalysts.angular.spring.util.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CountryServiceImpl extends AbstractCrudlService<Long, Country, CountryDto, CountryDto, CountrySearchRequest> implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ConversionService conversionService) {
        super(countryRepository, conversionService);
        this.countryRepository = countryRepository;
    }

    @Override
    protected boolean canRead(Country country) {
        return true;
    }

    @Override
    protected boolean canUpdate(Country country) {
        return true;
    }

    @Override
    protected boolean canDelete(Country country) {
        return true;
    }

    @Override
    protected Page<Country> executeFindAll(CountrySearchRequest searchRequest) {

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(searchRequest.getName())) {
            return countryRepository.findByNameStartingWith(searchRequest.getName(), SearchUtils
                    .toPageRequest(searchRequest));
        }

        return super.executeFindAll(searchRequest);
    }

    @Override
    protected Country mergeFromDto(CountryDto countryDto, Country country) {

        country.setName(countryDto.getName());
        country.setDisabled(countryDto.isDisabled());
        country.setCode(countryDto.getCode());

        return country;
    }

    // enable facets

    protected FacetTermDto<String> convertFacetTerm(FacetTerm facetTerm) {
        return new FacetTermDto<>(facetTerm.getName(), facetTerm.getName(), facetTerm.getValue());
    }

    @Override
    protected PageDto<CountryDto> convertList(Page<Country> countries) {
        final List<CountryDto> resultList = countries.getContent().stream()
                .map(this::convertForList)
                .collect(Collectors.toList());

        List<FacetTerm> facetTerms = countryRepository.findFacetTerms();
        List<FacetTermDto<String>> facetTermDtos = facetTerms.stream().map(this::convertFacetTerm).collect(Collectors.toList());

        FacetDto<String> facet = new FacetDto<>("name", facetTermDtos);

        return new PageWithFacetDto<>(resultList, countries.getTotalElements(), Collections.singleton(facet));
    }
}
