package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Child;
import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Parent;
import com.github.jajisdo.hbmexample2.service.ChildService;
import com.github.jajisdo.hbmexample2.service.ParentService;
import com.github.jajisdo.hbmexample2.util.ContextUtil;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by domann on 15.07.15.
 */
public class OrphanAdoptionTest {

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
    public void testAdoptionInDatabase() {

        // persist new child as orphan.
        Child maria = new Child("maria");
        Child persistedMaria = childService.store(maria);
        assertEquals(1, childService.count());
        assertEquals(maria, persistedMaria);

        // add maria to children of parent
        List<Child> children = parent.getChildren();
        children.add(persistedMaria);
        assertEquals(4, parent.getChildren().size());

        parentService.store(parent);

        assertEquals(1, parentService.count());
        assertEquals(4, childService.count());

        for (int i = 0; i < 100; i++) {
            parentService.store(parent);
        }

        assertEquals(1, parentService.count());
        assertEquals(4, childService.count());
    }

    @Test
    public void testBornInDatabase() {

        Parent mother = new Parent("mutter", new ArrayList<Child>());
        parentService.store(mother);
        parentService.flush();

        // persist new child as orphan.
        Child maria = new Child("maria");
        mother.getChildren().add(maria);
        //maria.setParent(parent);
        childService.store(maria);

        assertEquals(1, parentService.count());
        assertEquals(1, childService.count());

    }
}
