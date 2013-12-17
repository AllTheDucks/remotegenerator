package com.alltheducks.javamodeltoclosure;

import com.alltheducks.javamodeltoclosure.FieldDiscoveryService;
import com.alltheducks.javamodeltoclosure.example.ignoreannotatedfields.ExampleWithIgnoredFields;
import com.alltheducks.javamodeltoclosure.example.inheritancechildannotated.ExampleChildWithAnnotation;
import com.alltheducks.javamodeltoclosure.example.oneannotatedclass.Example;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.Assert.*;

public class FieldDiscoveryServiceTest {

    @Test
    public void testEnumerateFields_withExampleClass_returnsTwoFields() throws Exception {
        FieldDiscoveryService service = new FieldDiscoveryService();
        Set<Field> fields = service.enumerateFields(Example.class);

        Field aString = Example.class.getDeclaredField("aString");
        Field anInt = Example.class.getDeclaredField("anInt");

        assertEquals(2, fields.size());
        assertTrue("Contains 'aString'", fields.contains(aString));
        assertTrue("Contains 'anInt'", fields.contains(anInt));
    }

    @Test
    public void testEnumerateFields_withInheritenceChildAnnotated_returnsParentAndChildFields() throws Exception {
        FieldDiscoveryService service = new FieldDiscoveryService();
        Set<Field> fields = service.enumerateFields(ExampleChildWithAnnotation.class);

        Field childString = ExampleChildWithAnnotation.class.getDeclaredField("childString");
        Field parentString = ExampleChildWithAnnotation.class.getSuperclass().getDeclaredField("parentString");

        assertEquals(2, fields.size());
        assertTrue("Contains 'childString'", fields.contains(childString));
        assertTrue("Contains 'parentString'", fields.contains(parentString));
    }

    @Test
    public void testEnumerateFields_withIgnoredField_returnFieldsWithoutIgnoreAnnotationOnly() throws Exception {
        FieldDiscoveryService service = new FieldDiscoveryService();
        Set<Field> fields = service.enumerateFields(ExampleWithIgnoredFields.class);

        Field aString = ExampleWithIgnoredFields.class.getDeclaredField("aString");
        Field ignoredString = ExampleWithIgnoredFields.class.getDeclaredField("ignoredString");

        assertEquals(1, fields.size());
        assertTrue("Contains 'aString'", fields.contains(aString));
        assertFalse("Contains 'ignoredString'", fields.contains(ignoredString));
    }

}
