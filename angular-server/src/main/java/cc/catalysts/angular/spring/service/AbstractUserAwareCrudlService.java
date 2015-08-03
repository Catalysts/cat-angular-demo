package cc.catalysts.angular.spring.service;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.exception.NotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Klaus Lehner
 */
public abstract class AbstractUserAwareCrudlService<USER extends UserDetails,
        ID extends Serializable,
        ENTITY extends Identifiable<ID>,
        LIST_DTO,
        DETAIL_DTO,
        SEARCH_REQUEST extends SearchRequest> implements UserAwareCrudlService<USER, ID, LIST_DTO, DETAIL_DTO, SEARCH_REQUEST> {

    protected final JpaRepository<ENTITY, ID> jpaRepository;
    protected final Converter<ENTITY, DETAIL_DTO> dtoConverter;

    public AbstractUserAwareCrudlService(JpaRepository<ENTITY, ID> jpaRepository, Converter<ENTITY, DETAIL_DTO> dtoConverter) {
        this.jpaRepository = jpaRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public final DETAIL_DTO get(USER USER, ID aLong) {
        ENTITY data = jpaRepository.findOne(aLong);
        if (data == null) {
            throw new NotFoundException(aLong + " not found");
        } else {
            if (!canRead(USER, data)) {
                throw new AccessDeniedException("");
            }
            return dtoConverter.convert(data);
        }
    }

    protected abstract boolean canRead(USER USER, ENTITY entity);

    protected abstract boolean canUpdate(USER USER, ENTITY entity);

    protected abstract boolean canDelete(USER USER, ENTITY entity);

    @Override
    @Transactional(readOnly = false)
    public DETAIL_DTO update(USER USER, ID entityId, DETAIL_DTO fghData) {
        ENTITY entity = jpaRepository.findOne(entityId);
        if (entity == null) {
            throw new NotFoundException(entityId + " not found");
        }
        if (!canUpdate(USER, entity)) {
            throw new AccessDeniedException("");
        }
        entity = mergeFromDto(USER, fghData, entity);
        entity = jpaRepository.save(entity);

        return dtoConverter.convert(entity);
    }

    protected abstract ENTITY mergeFromDto(USER USER, DETAIL_DTO dto, ENTITY entity);

    @Override
    @Transactional(readOnly = false)
    public final void delete(USER USER, ID entityId) {
        ENTITY entity = jpaRepository.findOne(entityId);
        if (entity == null) {
            throw new NotFoundException(entityId + " not found");
        }
        if (!canDelete(USER, entity)) {
            throw new AccessDeniedException("");
        }
        doDelete(entity);
    }

    protected void doDelete(ENTITY entity) {
        jpaRepository.delete(entity);
    }
}
