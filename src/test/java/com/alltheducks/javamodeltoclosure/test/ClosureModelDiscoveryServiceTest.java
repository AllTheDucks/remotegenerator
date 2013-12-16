package com.alltheducks.javamodeltoclosure.test;

import com.alltheducks.javamodeltoclosure.ClosureModelDiscoveryService;
import com.alltheducks.javamodeltoclosure.test.example.mixedannotations.AnnotatedOne;
import com.alltheducks.javamodeltoclosure.test.example.mixedannotations.AnnotatedTwo;
import com.alltheducks.javamodeltoclosure.test.example.mixedannotations.NotAnnotatedOne;
import com.alltheducks.javamodeltoclosure.test.example.mixedannotations.NotAnnotatedTwo;
import com.alltheducks.javamodeltoclosure.test.example.oneannotatedclass.Example;
import com.alltheducks.javamodeltoclosure.test.example.threeannotatedclasses.ExampleOne;
import com.alltheducks.javamodeltoclosure.test.example.threeannotatedclasses.ExampleThree;
import com.alltheducks.javamodeltoclosure.test.example.threeannotatedclasses.ExampleTwo;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ClosureModelDiscoveryServiceTest {

    @Test
    public void testEnumerateClasses_withOneAnnotatedClass_expectExampleOne() throws Exception {
        ClosureModelDiscoveryService service = new ClosureModelDiscoveryService("com.alltheducks.javamodeltoclosure.test.example.oneannotatedclass");
        Set<Class<?>> classes = service.enumerateClasses();

        assertEquals(1, classes.size());
        assertTrue("Contains Example", classes.contains(Example.class));
    }

    @Test
    public void testEnumerateClasses_withThreeAnnotatedClasses_expectThreeClasses() throws Exception {
        ClosureModelDiscoveryService service = new ClosureModelDiscoveryService("com.alltheducks.javamodeltoclosure.test.example.threeannotatedclasses");
        Set<Class<?>> classes = service.enumerateClasses();

        assertEquals(3, classes.size());
        assertTrue("Contains ExampleOne", classes.contains(ExampleOne.class));
        assertTrue("Contains ExampleTwo", classes.contains(ExampleTwo.class));
        assertTrue("Contains ExampleThree", classes.contains(ExampleThree.class));
    }

    @Test
    public void testEnumerateClasses_withMixedAnnotatedClasses_expectOnlyAnnotatedClasses() throws Exception {
        ClosureModelDiscoveryService service = new ClosureModelDiscoveryService("com.alltheducks.javamodeltoclosure.test.example.mixedannotations");
        Set<Class<?>> classes = service.enumerateClasses();

        assertEquals(2, classes.size());
        assertTrue("Contains AnnotatedOne", classes.contains(AnnotatedOne.class));
        assertTrue("Contains AnnotatedTwo", classes.contains(AnnotatedTwo.class));
        assertFalse("Does not contain NotAnnotatedOne", classes.contains(NotAnnotatedOne.class));
        assertFalse("Does not contain NotAnnotatedTwo", classes.contains(NotAnnotatedTwo.class));
    }
}
