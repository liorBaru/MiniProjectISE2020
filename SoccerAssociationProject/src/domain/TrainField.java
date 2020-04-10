package domain;

public class TrainField implements Asset {

    String fieldTrainingName;

    public TrainField(String fieldTrainingName) {
        this.fieldTrainingName=fieldTrainingName;
    }

    public String getFieldName() {
        return fieldTrainingName;
    }

    @Override
    public String getType() {
        return "Field Training "+this.fieldTrainingName;
    }
}
