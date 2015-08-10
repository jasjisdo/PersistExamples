package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import com.github.jajisdo.hbmexample2.service.MatriculationNumberService;
import com.github.jajisdo.hbmexample2.service.StudentService;
import com.github.jajisdo.hbmexample2.util.ContextUtil;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by domann on 30.07.15.
 */
public class One2OneMultiplePessistTest {

    protected static final String CONTEXT_LOCATION = "/inmemory-database-test-annotation-context.xml";
    protected static StudentService studentUniDirectionalService;
    protected static MatriculationNumberService matriculationNumberUniDirectionalService;

    protected Student student;
    private static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        studentUniDirectionalService = ContextUtil.getDbService(context, StudentService.class);
        matriculationNumberUniDirectionalService = ContextUtil.getDbService(context, MatriculationNumberService.class);
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
        assertFalse(parent.isNew());
        assertEquals(parent, persistedMother);
        assertEquals(1, parentService.count());
        assertEquals(3, childService.count());
        parentService.removeAll();
        childService.removeAll();
        assertEquals(0, parentService.count());
        assertEquals(0, childService.count());
        */
    }

}
