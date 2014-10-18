package ee.ttu.repository.classifier;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
@NoRepositoryBean
public interface ClassifierRepository<T> extends Repository<T, Long> {

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    T findOne(Long id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Find the classifier by the name field
     *
     * @param name Classifier value
     * @return The classifier instance
     */
    T findByName(String name);

}
