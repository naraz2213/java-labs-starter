package edu.course.lab02;

public class DataSample {

    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id must not be null or blank");
        }
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("label must not be null or blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("features must not be null or empty");
        }
        for (double f : features) {
            if (!Double.isFinite(f)) {
                throw new IllegalArgumentException("all features must be finite numbers");
            }
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("new status must not be null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeatures() {
        double sum = 0;
        for (double f : features) {
            sum += f;
        }
        return sum / features.length;
    }
    // Нормализация min-max в диапазон [0.0, 1.0]
    // Возвращает НОВЫЙ массив, исходный объект не меняется
    public double[] normalizedFeatures() {
        double min = features[0];
        double max = features[0];

        for (double f : features) {
            if (f < min) min = f;
            if (f > max) max = f;
        }

        double[] result = new double[features.length];
        if (max == min) {
            return result; // Все значения одинаковые — вернем нули
        }

        for (int i = 0; i < features.length; i++) {
            result[i] = (features[i] - min) / (max - min);
        }
        return result;
    }
}