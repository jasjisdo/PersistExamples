package de.dailab.schaufenster.jpafilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by domann on 09.07.15.
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    public T saveWithoutFlush(T entity);

    public List<T> saveWithoutFlush(Iterable<? extends T> entities);

    public List<T> doQueryWithFilter(String filterName, String filterQueryName, Map inFilterParams, Map inQueryParams);
}
