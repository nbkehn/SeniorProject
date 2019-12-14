package bco.scheduler.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Repository with ability to refresh data
 * @param <T>
 * @param <ID>
 * @author Noah Trimble
 * https://stackoverflow.com/questions/45491551/refresh-and-fetch-an-entity-after-save-jpa-spring-data-hibernate
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomRepository<T, ID> {
    // Entity manager
    private final EntityManager entityManager;

    /**
     * CustomRepositoryImpl Constructor
     * @param entityInformation entity information
     * @param entityManager entity manager
     */
    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Refresh an entity
     * @param t entity to refresh
     */
    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }
}