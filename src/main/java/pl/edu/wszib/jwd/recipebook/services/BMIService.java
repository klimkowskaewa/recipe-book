package pl.edu.wszib.jwd.recipebook.services;

import org.springframework.stereotype.Service;
import pl.edu.wszib.jwd.recipebook.web.models.BMIModel;

@Service
public class BMIService {

    public double divideBmi(BMIModel.OperationModel model){
        if(model.getHeight() == 0 || model.getWeight() == 0){
            return 0;
        }
       return model.getWeight() / (model.getHeight() * model.getHeight());
    }

    public BMIModel.OperationModel clearBmi(BMIModel.OperationModel model){
        model.setWeight(0);
        model.setHeight(0);
        return model;
    }
}