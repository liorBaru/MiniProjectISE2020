package domain;

public class Field implements Asset {

    String fieldName;

    public Field(String fieldName) {
        this.fieldName=fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getType() {
        return "Field: " +this.fieldName;
    }
}
