package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;
import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import com.github.jajisdo.hbmexample2.service.ChildBiDirectionalService;
import com.github.jajisdo.hbmexample2.service.MotherBiDirectionalService;
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
    protected static MotherBiDirectionalService motherBiDirectionalService;
    protected static ChildBiDirectionalService childBiDirectionalService;

    protected MotherBiDirectional mother;
    private static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        motherBiDirectionalService = ContextUtil.getDbService(context, MotherBiDirectionalService.class);
        childBiDirectionalService = ContextUtil.getDbService(context, ChildBiDirectionalService.class);
    }

    @AfterClass
    public static void terminate() {
        context.close();
    }

    @Before
    public void setUp() {
        mother = MotherFactory.createFamily();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testRepositories() {
        assertTrue(mother.isNew());
        MotherBiDirectional persistedMother = motherBiDirectionalService.store(mother);
        assertNotNull(persistedMother);
        assertFalse(mother.isNew());
        assertEquals(mother, persistedMother);
        assertEquals(1, motherBiDirectionalService.count());
        assertEquals(3, childBiDirectionalService.count());
        motherBiDirectionalService.removeAll();
        childBiDirectionalService.removeAll();
        assertEquals(0, motherBiDirectionalService.count());
        assertEquals(0, childBiDirectionalService.count());
    }

    @Test
    public void testAdoptionInDatabase() {

        // persist new child as orphan.
        ChildBiDirectional maria = new ChildBiDirectional("maria");
        ChildBiDirectional persistedMaria = childBiDirectionalService.store(maria);
        assertEquals(1, childBiDirectionalService.count());
        assertEquals(maria, persistedMaria);

        // add maria to children of mother
        List<ChildBiDirectional> children = mother.getChildren();
        children.add(persistedMaria);
        assertEquals(4, mother.getChildren().size());

        motherBiDirectionalService.store(mother);

        assertEquals(1, motherBiDirectionalService.count());
        assertEquals(4, childBiDirectionalService.count());

        for (int i = 0; i < 100; i++) {
            motherBiDirectionalService.store(mother);
        }

        assertEquals(1, motherBiDirectionalService.count());
        assertEquals(4, childBiDirectionalService.count());
    }

    @Test
    public void testBornInDatabase() {

        MotherBiDirectional mother = new MotherBiDirectional("mutter", new ArrayList<ChildBiDirectional>());
        motherBiDirectionalService.store(mother);
        motherBiDirectionalService.flush();

        // persist new child as orphan.
        ChildBiDirectional maria = new ChildBiDirectional("maria");
        mother.getChildren().add(maria);
        maria.setMother(mother);
        childBiDirectionalService.store(maria);

        assertEquals(1, motherBiDirectionalService.count());
        assertEquals(1, childBiDirectionalService.count());

    }
}
