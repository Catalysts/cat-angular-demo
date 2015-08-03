package cc.catalysts.angular.spring.service;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.exception.NotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Klaus Lehner
 */
public abstract class AbstractCrudlService<
        ID extends Serializable,
        ENTITY extends Identifiable<ID>,
        LIST_DTO,
        DETAIL_DTO,
        SEARCH_REQUEST extends SearchRequest> implements CrudlService<ID, LIST_DTO, DETAIL_DTO, SEARCH_REQUEST> {

    protected final JpaRepository<ENTITY, ID> jpaRepository;
    protected final Converter<ENTITY, DETAIL_DTO> dtoConverter;

    public AbstractCrudlService(JpaRepository<ENTITY, ID> jpaRepository, Converter<ENTITY, DETAIL_DTO> dtoConverter) {
        this.jpaRepository = jpaRepository;
        this.dtoConverter = dtoConverter;
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
            return dtoConverter.convert(data);
        }
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

        return dtoConverter.convert(entity);
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
