package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleIdTest {
    @Test
    void createsValidId() {
        SampleId id = new SampleId("abc-123");
        assertEquals("abc-123", id.value());
    }

    @Test
    void rejectsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId(null));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("   "));
    }

    @Test
    void equalityByValue() {
        SampleId id1 = new SampleId("abc");
        SampleId id2 = new SampleId("abc");
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}