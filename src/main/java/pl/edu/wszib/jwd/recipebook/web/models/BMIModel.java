package pl.edu.wszib.jwd.recipebook.web.models;

public class BMIModel {
    public static class OperationModel {

        private String operation;
        private double weight;
        private double height;

        public OperationModel() {
        }

        public OperationModel(String operation) {
            this.operation = operation;
        }

        public OperationModel(double weight, double height) {
            this.weight = weight;
            this.height = height;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }


    }
}