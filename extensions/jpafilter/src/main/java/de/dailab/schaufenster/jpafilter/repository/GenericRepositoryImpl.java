package de.dailab.schaufenster.jpafilter.repository;

import de.dailab.schaufenster.jpafilter.annotation.EntityFilter;
import de.dailab.schaufenster.jpafilter.annotation.FilterQuery;
import org.apache.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@NoRepositoryBean
public class GenericRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID>, Serializable {

    private static final long serialVersionUID = -5178117799834425008L;

    static Logger logger = Logger.getLogger(GenericRepositoryImpl.class);

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final DefaultPersistenceProvider provider;

    private Class<?> springDataRepositoryInterface;

    public Class<?> getSpringDataRepositoryInterface() {
        return springDataRepositoryInterface;
    }

    public void setSpringDataRepositoryInterface(
            Class<?> springDataRepositoryInterface) {
        this.springDataRepositoryInterface = springDataRepositoryInterface;
    }

    /**
     * Creates a new {@link SimpleJpaRepository} to manage objects of the given
     * {@link JpaEntityInformation}.
     *
     * @param entityInformation
     * @param entityManager
     */
    public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, Class<?> springDataRepositoryInterface) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = DefaultPersistenceProvider.fromEntityManager(entityManager);
        this.springDataRepositoryInterface = springDataRepositoryInterface;
    }

    /**
     * Creates a new {@link SimpleJpaRepository} to manage objects of the given
     * domain type.
     *
     * @param domainClass
     * @param em
     */
    public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getMetadata(domainClass, em), em, null);
    }

    @Override
    public  <S extends T> S save(S entity) {
        Session session = em.unwrap(Session.class).getSessionFactory().openSession();
        Transaction tx  = session.beginTransaction();
//        if (entityInformation.isNew(entity)) {
//            session.saveOrUpdate(entity);
//        } else {
//            session.update(entity);
//        }
        session.saveOrUpdate(entity);
        tx.commit();
        session.close();
        return entity;
    }

    //@Transactional
    public  <S extends T> S saveAndFlush(S entity) {

        S result = save(entity);
        flush();

        return result;

    }

    //@Transactional
    public T saveWithoutFlush(T entity) {

        return save(entity);

    }

    @Transactional
    public List<T> saveWithoutFlush(Iterable<? extends T> entities) {
        List<T> result = new ArrayList<T>();
        if (entities == null) {
            return result;
        }

        for (T entity : entities) {
            result.add(saveWithoutFlush(entity));
        }
        return result;
    }

    public List<T> doQueryWithFilter(String filterName, String filterQueryName, Map inFilterParams, Map inQueryParams) {
        if (GenericRepository.class.isAssignableFrom(getSpringDataRepositoryInterface())) {
            Annotation entityFilterAnn = getSpringDataRepositoryInterface().getAnnotation(EntityFilter.class);
            if (entityFilterAnn != null) {
                EntityFilter entityFilter = (EntityFilter) entityFilterAnn;
                FilterQuery[] filterQuerys = entityFilter.filterQueries();
                for (FilterQuery fQuery : filterQuerys) {
                    if (StringUtils.equals(filterQueryName, fQuery.name())) {
                        String jpql = fQuery.jpql();
                        Filter filter = em.unwrap(Session.class).enableFilter(filterName);

                        //set de.dailab.schaufenster.jpa_filter_extension.filter parameter
                        for (Object key : inFilterParams.keySet()) {
                            String filterParamName = key.toString();
                            Object filterParamValue = inFilterParams.get(key);
                            filter.setParameter(filterParamName, filterParamValue);
                        }

                        //set query parameter
                        Query query = em.createQuery(jpql);
                        for (Object key : inQueryParams.keySet()) {
                            String queryParamName = key.toString();
                            Object queryParamValue = inQueryParams.get(key);
                            query.setParameter(queryParamName, queryParamValue);
                        }
                        return query.getResultList();
                    }
                }
            }
        }
        return null;
    }

} 