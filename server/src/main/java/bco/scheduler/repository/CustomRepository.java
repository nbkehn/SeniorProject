package bco.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Repository interface with ability to refresh data
 * @param <T>
 * @param <ID>
 * @author Noah Trimble
 * https://stackoverflow.com/questions/45491551/refresh-and-fetch-an-entity-after-save-jpa-spring-data-hibernate
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    /**
     * Refresh an entity
     * @param t entity to refresh
     */
    void refresh(T t);
}