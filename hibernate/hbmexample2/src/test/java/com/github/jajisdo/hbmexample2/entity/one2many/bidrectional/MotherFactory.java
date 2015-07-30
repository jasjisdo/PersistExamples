package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
public class MotherFactory {

    public static MotherBiDirectional createFamily(){
        return createMother(createChildren());
    }

    private static List<ChildBiDirectional> createChildren() {

        List<ChildBiDirectional> children = new ArrayList<>();

        ChildBiDirectional child1 = new ChildBiDirectional("john");
        ChildBiDirectional child2 = new ChildBiDirectional("doe");
        ChildBiDirectional child3 = new ChildBiDirectional("jane");

        children.add(child1);
        children.add(child2);
        children.add(child3);

        return children;

    }

    private static MotherBiDirectional createMother(List<ChildBiDirectional> children) {
        return new MotherBiDirectional("mara", children);
    }
}
