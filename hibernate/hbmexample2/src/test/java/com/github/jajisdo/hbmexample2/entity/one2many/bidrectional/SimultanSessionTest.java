package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by domann on 27.07.15.
 */
public class SimultanSessionTest {

    protected static final String CONTEXT_LOCATION = "/inmemory-database-test-annotation-context.xml";
    protected static SessionFactory sessionFactory;
    protected static EntityManagerFactory entityManagerFactory;
    protected MotherBiDirectional mother;

    @BeforeClass
    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        entityManagerFactory = (EntityManagerFactory) context.getBean("entityManagerFactory");
    }

    @AfterClass
    public static void terminate() {

    }

    @Before
    public void setUp() {
        mother = FamilyCreator.createFamily();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSimultanSession() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(mother);
        tx.commit();
        session.close();
    }

    @Test
    public void testSimultanSessionFromEM() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Session session = em.unwrap(Session.class).getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(mother);
        tx.commit();
        session.close();
    }
}
