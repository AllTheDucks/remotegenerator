package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.example.inheritancechildannotated.ExampleChildWithAnnotation;
import com.alltheducks.javamodeltoclosure.example.inheritanceparentannotated.ExampleParentWithAnnotation;
import com.alltheducks.javamodeltoclosure.example.mixedannotations.AnnotatedOne;
import com.alltheducks.javamodeltoclosure.example.mixedannotations.AnnotatedTwo;
import com.alltheducks.javamodeltoclosure.example.mixedannotations.NotAnnotatedOne;
import com.alltheducks.javamodeltoclosure.example.mixedannotations.NotAnnotatedTwo;
import com.alltheducks.javamodeltoclosure.example.oneannotatedclass.Example;
import com.alltheducks.javamodeltoclosure.example.threeannotatedclasses.ExampleOne;
import com.alltheducks.javamodeltoclosure.example.threeannotatedclasses.ExampleThree;
import com.alltheducks.javamodeltoclosure.example.threeannotatedclasses.ExampleTwo;
import com.alltheducks.javamodeltoclosure.service.ConversionModelDiscoveryService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class ConversionModelDiscoveryServiceTest {

    ConversionModelDiscoveryService conversionModelDiscoveryService;

    @Before
    public void setUp() {
        conversionModelDiscoveryService = new ConversionModelDiscoveryService();
    }

    @Test
    public void testEnumerateClasses_withOneAnnotatedClass_expectExampleOne() throws Exception {
        Collection<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example.oneannotatedclass");

        assertEquals(1, classes.size());
        assertTrue("Contains Example", classes.contains(Example.class));
    }

    @Test
    public void testEnumerateClasses_withThreeAnnotatedClasses_expectThreeClasses() throws Exception {
        Collection<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example.threeannotatedclasses");

        assertEquals(3, classes.size());
        assertTrue("Contains ExampleOne", classes.contains(ExampleOne.class));
        assertTrue("Contains ExampleTwo", classes.contains(ExampleTwo.class));
        assertTrue("Contains ExampleThree", classes.contains(ExampleThree.class));
    }

    @Test
    public void testEnumerateClasses_withMixedAnnotatedClasses_expectOnlyAnnotatedClasses() throws Exception {
        Collection<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example.mixedannotations");

        assertEquals(2, classes.size());
        assertTrue("Contains AnnotatedOne", classes.contains(AnnotatedOne.class));
        assertTrue("Contains AnnotatedTwo", classes.contains(AnnotatedTwo.class));
        assertFalse("Does not contain NotAnnotatedOne", classes.contains(NotAnnotatedOne.class));
        assertFalse("Does not contain NotAnnotatedTwo", classes.contains(NotAnnotatedTwo.class));
    }

    @Test
    public void testEnumerateClasses_withInheritanceChildAnnotated_expectOnlyChild() throws Exception {
        Collection<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example.inheritancechildannotated");

        assertEquals(1, classes.size());
        assertTrue("Contains ExampleChildWithAnnotation", classes.contains(ExampleChildWithAnnotation.class));
    }

    @Test
    public void testEnumerateClasses_withInheritanceParentAnnotated_expectOnlyParent() throws Exception {
        Collection<Class<?>> classes = conversionModelDiscoveryService.enumerateClasses("com.alltheducks.javamodeltoclosure.example.inheritanceparentannotated");

        assertEquals(1, classes.size());
        assertTrue("Contains ExampleParentWithAnnotation", classes.contains(ExampleParentWithAnnotation.class));
    }
}
