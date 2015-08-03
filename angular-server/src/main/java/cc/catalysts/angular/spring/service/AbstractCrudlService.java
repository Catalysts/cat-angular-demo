package cc.catalysts.angular.spring.service;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.exception.NotFoundException;
import cc.catalysts.angular.spring.util.SearchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Klaus Lehner
 */
public abstract class AbstractCrudlService<
        ID extends Serializable,
        ENTITY extends Identifiable<ID>,
        LIST_DTO,
        DETAIL_DTO,
        SEARCH_REQUEST extends SearchRequest> implements CrudlService<ID, LIST_DTO, DETAIL_DTO, SEARCH_REQUEST> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCrudlService.class);

    protected final JpaRepository<ENTITY, ID> jpaRepository;
    protected final ConversionService conversionService;

    private final Class<ENTITY> entityType;
    private final Class<LIST_DTO> listType;
    private final Class<DETAIL_DTO> detailType;

    @SuppressWarnings("unchecked")
    public AbstractCrudlService(JpaRepository<ENTITY, ID> jpaRepository, ConversionService conversionService) {
        this.jpaRepository = jpaRepository;
        this.conversionService = conversionService;

        final Class<?>[] localParameters = GenericTypeResolver.resolveTypeArguments(
                getClass(), AbstractCrudlService.class);
        entityType = (Class<ENTITY>) localParameters[1];
        listType = (Class<LIST_DTO>) localParameters[2];
        detailType = (Class<DETAIL_DTO>) localParameters[3];
    }

    @Override
    @Transactional(readOnly = true)
    public final DETAIL_DTO get(ID aLong) {
        ENTITY data = jpaRepository.findOne(aLong);
        if (data == null) {
            throw new NotFoundException(aLong + " not found");
        } else {
            if (!canRead(data)) {
                throw new AccessDeniedException("");
            }
            return conversionService.convert(data, detailType);
        }
    }

    @Override
    public final PageDto<LIST_DTO> list(final SEARCH_REQUEST searchRequest) {
        return doList(searchRequest);
    }

    protected LIST_DTO convertForList(ENTITY entity) {
        return conversionService.convert(entity, listType);
    }

    protected DETAIL_DTO convertForDetail(ENTITY entity) {
        return conversionService.convert(entity, detailType);
    }

    protected PageDto<LIST_DTO> doList(final SEARCH_REQUEST searchRequest) {
        final Page<ENTITY> page = jpaRepository.findAll(SearchUtils
                .toPageRequest(searchRequest));

        final List<LIST_DTO> resultList = page.getContent().stream()
                .map(this::convertForList)
                .collect(Collectors.toList());

        return new PageDto<>(resultList, page.getTotalElements());
    }

    protected abstract boolean canRead(ENTITY entity);

    protected abstract boolean canUpdate(ENTITY entity);

    protected abstract boolean canDelete(ENTITY entity);

    @Override
    @Transactional(readOnly = false)
    public DETAIL_DTO update(ID entityId, DETAIL_DTO fghData) {
        ENTITY entity = jpaRepository.findOne(entityId);
        if (entity == null) {
            throw new NotFoundException(entityId + " not found");
        }
        if (!canUpdate(entity)) {
            throw new AccessDeniedException("");
        }
        entity = mergeFromDto(fghData, entity);
        entity = jpaRepository.save(entity);

        return convertForDetail(entity);
    }


    @Override
    @Transactional(readOnly = false)
    public DETAIL_DTO create(DETAIL_DTO fghData) {
        ENTITY entity;
        try {
            entity = entityType.newInstance();
        } catch (final InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        if (!canUpdate(entity)) {
            throw new AccessDeniedException("");
        }
        entity = mergeFromDto(fghData, entity);
        entity = jpaRepository.save(entity);

        return convertForDetail(entity);
    }

    protected abstract ENTITY mergeFromDto(DETAIL_DTO dto, ENTITY entity);

    @Override
    @Transactional(readOnly = false)
    public final void delete(ID entityId) {
        ENTITY entity = jpaRepository.findOne(entityId);
        if (entity == null) {
            throw new NotFoundException(entityId + " not found");
        }
        if (!canDelete(entity)) {
            throw new AccessDeniedException("");
        }
        doDelete(entity);
    }

    protected void doDelete(ENTITY entity) {
        jpaRepository.delete(entity);
    }
}
