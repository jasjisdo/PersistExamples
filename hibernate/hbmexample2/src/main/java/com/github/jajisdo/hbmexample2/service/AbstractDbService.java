package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.Entity;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by domann on 08.04.15.
 */
@Repository
public abstract class AbstractDbService<E extends Entity, R extends HibernateJpaRepository<E, Long>> {

    protected R repository;

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public abstract E findById (long id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    public abstract Iterable<? extends E> findAll();

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return true if an entity with the given id exists, {@literal false} otherwise
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     */
    public boolean exists(Long id){
        return repository.exists(id);
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    public long count(){
        return repository.count();
    }

    // STORE

    /**
     * Stores a given entity. Use the returned instance for further operations as the store operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the stored entity
     */
    public <e extends E> e store(e entity){
        return repository.save(entity);
    }

    /**
     * Stores all given entities.
     *
     * @param entities
     * @return the stored entities
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    public void store(Iterable<? extends E> entities){
        repository.save(entities);
    }

    // REMOVE

    /**
     * Removes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    public void remove(Long id){
        repository.delete(id);
    }

    /**
     * Removes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     */
    public void remove(E entity) {
        repository.delete(entity);
    }

    /**
     * Removes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is (@literal null}.
     */
    public void remove(Iterable<? extends E> entities){
        repository.delete(entities);
    }

    public void removeAll(){
        repository.deleteAll();
    }

    /**
     * Flushes all pending changes to the database.
     */
    public void flush(){
    	repository.flush();
    }

    /**
     * Deletes all entities managed by the repository.
     */
    public abstract void setRepository(R repository);
}
