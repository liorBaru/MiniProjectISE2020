package domain;

public class TrainField implements Asset {

    String fieldTrainingName;

    public TrainField(String fieldTrainingName) {
        this.fieldTrainingName=fieldTrainingName;
    }

    public TrainField() {
        super();
    }

    public String getFieldName() {
        return fieldTrainingName;
    }

    @Override
    public String getType() {
        return "Field Training "+this.fieldTrainingName;
    }

    @Override
    public void setTeam(Team team) {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void removeTeam(Team team) {

    }

    @Override
    public String getName() {
        return null;
    }
}
