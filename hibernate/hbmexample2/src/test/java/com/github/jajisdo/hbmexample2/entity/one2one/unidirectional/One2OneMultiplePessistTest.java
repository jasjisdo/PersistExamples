package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import com.github.jajisdo.hbmexample2.service.MatriculationNumberUniDirectionalService;
import com.github.jajisdo.hbmexample2.service.StudentUniDirectionalService;
import com.github.jajisdo.hbmexample2.util.ContextUtil;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by domann on 30.07.15.
 */
public class One2OneMultiplePessistTest {

    protected static final String CONTEXT_LOCATION = "/inmemory-database-test-annotation-context.xml";
    protected static StudentUniDirectionalService studentUniDirectionalService;
    protected static MatriculationNumberUniDirectionalService matriculationNumberUniDirectionalService;

    protected StudentUniDirectional student;
    private static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        studentUniDirectionalService = ContextUtil.getDbService(context, StudentUniDirectionalService.class);
        matriculationNumberUniDirectionalService = ContextUtil.getDbService(context, MatriculationNumberUniDirectionalService.class);
    }

    @AfterClass
    public static void terminate() {
        context.close();
    }

    @Before
    public void setUp() {
        student = StudentFactory.createStudent();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testRepositories() {
        assertTrue(student.isNew());
        studentUniDirectionalService.store(student);
        studentUniDirectionalService.store(student);
        /*
        assertNotNull(persistedMother);
        assertFalse(mother.isNew());
        assertEquals(mother, persistedMother);
        assertEquals(1, motherBiDirectionalService.count());
        assertEquals(3, childBiDirectionalService.count());
        motherBiDirectionalService.removeAll();
        childBiDirectionalService.removeAll();
        assertEquals(0, motherBiDirectionalService.count());
        assertEquals(0, childBiDirectionalService.count());
        */
    }

}
