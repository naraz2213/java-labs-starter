package edu.course.lab02;

public record SampleId(String value) {

    // Компактный конструктор для проверки инварианта
    public SampleId {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("SampleId value must not be null or blank");
        }
    }
}