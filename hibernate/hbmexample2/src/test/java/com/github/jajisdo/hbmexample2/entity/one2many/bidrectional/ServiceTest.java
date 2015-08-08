package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Parent;
import com.github.jajisdo.hbmexample2.service.ChildService;
import com.github.jajisdo.hbmexample2.service.ParentService;
import com.github.jajisdo.hbmexample2.util.ContextUtil;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by domann on 08.08.2015.
 */
public class ServiceTest {

    protected static final String CONTEXT_LOCATION = "/inmemory-database-test-annotation-context.xml";
    protected static ParentService parentService;
    protected static ChildService childService;

    protected Parent parent;
    private static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        parentService = ContextUtil.getDbService(context, ParentService.class);
        childService = ContextUtil.getDbService(context, ChildService.class);
    }

    @AfterClass
    public static void terminate() {
        context.close();
    }

    @Before
    public void setUp() {
        parent = ParentFactory.createFamily();
    }

    @After
    public void tearDown() {
        parentService.removeAll();
        childService.removeAll();
    }

    @Test
    public void testServices() {
        assertTrue(parent.isNew());
        Parent persistedMother = parentService.store(parent);
        assertNotNull(persistedMother);
        assertFalse(parent.isNew());
        assertEquals(parent, persistedMother);
        assertEquals(1, parentService.count());
        assertEquals(3, childService.count());

        assertNull(parentService.findById(-1));
        assertNull(parentService.findById(0));
        assertNull(childService.findById(-1));
        assertNull(childService.findById(0));

        assertEquals("mara", parentService.findById(1).getName());
        assertEquals("john", childService.findById(1).getName());
        assertEquals("doe", childService.findById(2).getName());
        assertEquals("jane", childService.findById(3).getName());

        assertEquals(new Long(1L), parentService.findByName("mara").getId());

        parentService.removeAll();
        childService.removeAll();
        assertEquals(0, parentService.count());
        assertEquals(0, childService.count());
    }


}
