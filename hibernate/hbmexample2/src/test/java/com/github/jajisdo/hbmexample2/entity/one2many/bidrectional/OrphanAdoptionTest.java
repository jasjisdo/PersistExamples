package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;
import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import com.github.jajisdo.hbmexample2.service.ChildBiDirectionalService;
import com.github.jajisdo.hbmexample2.service.MotherBiDirectionalService;
import com.github.jajisdo.hbmexample2.util.ContextUtil;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    @BeforeClass
    public static void init() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
        motherBiDirectionalService = ContextUtil.getDbService(context, MotherBiDirectionalService.class);
        childBiDirectionalService = ContextUtil.getDbService(context, ChildBiDirectionalService.class);
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

    }
}
