package com.alltheducks.remotegenerator.service;

import com.alltheducks.remotegenerator.example.inheritancechildannotated.ExampleChildWithAnnotation;
import com.alltheducks.remotegenerator.example.inheritanceparentannotated.ExampleParentWithAnnotation;
import com.alltheducks.remotegenerator.example.mixedannotations.AnnotatedOne;
import com.alltheducks.remotegenerator.example.mixedannotations.AnnotatedTwo;
import com.alltheducks.remotegenerator.example.mixedannotations.NotAnnotatedOne;
import com.alltheducks.remotegenerator.example.mixedannotations.NotAnnotatedTwo;
import com.alltheducks.remotegenerator.example.oneannotatedclass.Example;
import com.alltheducks.remotegenerator.example.threeannotatedclasses.ExampleOne;
import com.alltheducks.remotegenerator.example.threeannotatedclasses.ExampleThree;
import com.alltheducks.remotegenerator.example.threeannotatedclasses.ExampleTwo;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class RemoteModelDiscoveryServiceTest {

    RemoteModelDiscoveryService remoteModelDiscoveryService;

    @Before
    public void setUp() {
        remoteModelDiscoveryService = new RemoteModelDiscoveryService();
    }

    @Test
    public void testEnumerateClasses_withOneAnnotatedClass_expectExampleOne() throws Exception {
        remoteModelDiscoveryService.addPackage("com.alltheducks.remotegenerator.example.oneannotatedclass");
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        assertEquals(1, classes.size());
        assertTrue("Contains Example", classes.contains(Example.class));
    }

    @Test
    public void testEnumerateClasses_withThreeAnnotatedClasses_expectThreeClasses() throws Exception {
        remoteModelDiscoveryService.addPackage("com.alltheducks.remotegenerator.example.threeannotatedclasses");
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        assertEquals(3, classes.size());
        assertTrue("Contains ExampleOne", classes.contains(ExampleOne.class));
        assertTrue("Contains ExampleTwo", classes.contains(ExampleTwo.class));
        assertTrue("Contains ExampleThree", classes.contains(ExampleThree.class));
    }

    @Test
    public void testEnumerateClasses_withMixedAnnotatedClasses_expectOnlyAnnotatedClasses() throws Exception {
        remoteModelDiscoveryService.addPackage("com.alltheducks.remotegenerator.example.mixedannotations");
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        assertEquals(2, classes.size());
        assertTrue("Contains AnnotatedOne", classes.contains(AnnotatedOne.class));
        assertTrue("Contains AnnotatedTwo", classes.contains(AnnotatedTwo.class));
        assertFalse("Does not contain NotAnnotatedOne", classes.contains(NotAnnotatedOne.class));
        assertFalse("Does not contain NotAnnotatedTwo", classes.contains(NotAnnotatedTwo.class));
    }

    @Test
    public void testEnumerateClasses_withInheritanceChildAnnotated_expectOnlyChild() throws Exception {
        remoteModelDiscoveryService.addPackage("com.alltheducks.remotegenerator.example.inheritancechildannotated");
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        assertEquals(1, classes.size());
        assertTrue("Contains ExampleChildWithAnnotation", classes.contains(ExampleChildWithAnnotation.class));
    }

    @Test
    public void testEnumerateClasses_withInheritanceParentAnnotated_expectOnlyParent() throws Exception {
        remoteModelDiscoveryService.addPackage("com.alltheducks.remotegenerator.example.inheritanceparentannotated");
        Collection<Class<?>> classes = remoteModelDiscoveryService.enumerateClasses();

        assertEquals(1, classes.size());
        assertTrue("Contains ExampleParentWithAnnotation", classes.contains(ExampleParentWithAnnotation.class));
    }
}
