package com.github.jajisdo.hbmexample2.entity.one2many.bidrectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Parent;
import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
public class ParentFactory {

    public static Parent createFamily(){
        return createMother(createChildren());
    }

    private static List<Child> createChildren() {

        List<Child> children = new ArrayList<>();

        Child child1 = new Child("john");
        Child child2 = new Child("doe");
        Child child3 = new Child("jane");

        children.add(child1);
        children.add(child2);
        children.add(child3);

        return children;

    }

    private static Parent createMother(List<Child> children) {
        return new Parent("mara", children);
    }
}
