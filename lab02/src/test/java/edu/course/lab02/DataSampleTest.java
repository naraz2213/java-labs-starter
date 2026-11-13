package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSampleTest {

    @Test
    void createsValidSample() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0, 3.0});
        assertEquals("s-1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, sample.getFeatures());
    }

    @Test
    void changeStatusUpdatesAndIsReadyWorks() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0});
        sample.changeStatus(SampleStatus.READY);
        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());
    }

    // --- ТЕСТЫ ИНВАРИАНТОВ (ОГРАНИЧЕНИЯ) ---

    @Test
    void rejectsNullOrBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(null, "cat", SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("   ", "cat", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void rejectsNullOrBlankLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", null, SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void rejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", null, new double[]{1.0}));
    }

    @Test
    void rejectsNullOrEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, null));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{}));
    }

    @Test
    void rejectsNonFiniteFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, Double.NaN}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{Double.POSITIVE_INFINITY}));
    }

    @Test
    void rejectsNullChangeStatus() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0});
        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
    }

    // --- ТЕСТЫ ЗАЩИТЫ СОСТОЯНИЯ (ИНКАПСУЛЯЦИЯ) ---

    @Test
    void constructorCopiesFeaturesArray() {
        double[] original = {1.0, 2.0};
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, original);

        // Меняем исходный массив после создания объекта
        original[0] = 99.0;

        // Объект не должен измениться
        assertArrayEquals(new double[]{1.0, 2.0}, sample.getFeatures());
    }

    @Test
    void getterReturnsCopyOfFeaturesArray() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0});

        // Получаем массив и меняем его
        double[] leaked = sample.getFeatures();
        leaked[0] = 99.0;

        // Внутреннее состояние не должно измениться
        assertArrayEquals(new double[]{1.0, 2.0}, sample.getFeatures());
    }

    @Test
    void calculatesAverageFeatures() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0, 3.0, 4.0});
        assertEquals(2.5, sample.averageFeatures(), 0.0001);
    }
    @Test
    void normalizedFeaturesWorksAndDoesNotChangeOriginal() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{10.0, 20.0, 30.0});

        double[] normalized = sample.normalizedFeatures();

        // Проверяем нормализацию (min=10, max=30)
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, normalized, 0.0001);

        // Проверяем, что исходный объект не изменился
        assertArrayEquals(new double[]{10.0, 20.0, 30.0}, sample.getFeatures());
    }
}