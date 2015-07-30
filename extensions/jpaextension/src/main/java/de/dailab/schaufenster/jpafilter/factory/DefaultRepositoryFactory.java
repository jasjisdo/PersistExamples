package de.dailab.schaufenster.jpafilter.factory;

/**
 * 
 * The purpose of this class is to override the default behaviour of the spring JpaRepositoryFactory class.
 * It will produce a GenericRepositoryImpl object instead of SimpleJpaRepository. 
 * 
 */
 
import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;
 
import java.io.Serializable;
import javax.persistence.EntityManager;


import de.dailab.schaufenster.jpafilter.repository.DefaultPersistenceProvider;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepositoryImpl;
import org.springframework.data.jpa.repository.query.QueryExtractor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;
 
 
public  class DefaultRepositoryFactory extends JpaRepositoryFactory{
     
 private final EntityManager entityManager;
    private final QueryExtractor extractor;
 
 
    public DefaultRepositoryFactory(EntityManager entityManager) {
     super(entityManager);
        Assert.notNull(entityManager);
        this.entityManager = entityManager;
        this.extractor = DefaultPersistenceProvider.fromEntityManager(entityManager);
    }
     
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
            RepositoryMetadata metadata, EntityManager entityManager) {
 
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        
        JpaEntityInformation<?, Serializable> entityInformation =
                getEntityInformation(metadata.getDomainType());
 
        if (isQueryDslExecutor(repositoryInterface)) {
            return new QueryDslJpaRepository(entityInformation, entityManager);
        } else {
            return new HibernateJpaRepositoryImpl(entityInformation, entityManager, repositoryInterface); //custom implementation
        }
    }
  
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
 
        if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
            return QueryDslJpaRepository.class;
        } else {
            return HibernateJpaRepositoryImpl.class;
        }
    }
     
    /**
     * Returns whether the given repository interface requires a QueryDsl
     * specific implementation to be chosen.
     * 
     * @param repositoryInterface
     * @return
     */
    private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
 
        return QUERY_DSL_PRESENT
                && QueryDslPredicateExecutor.class
                        .isAssignableFrom(repositoryInterface);
    }   
}