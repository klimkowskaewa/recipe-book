package pl.edu.wszib.jwd.recipebook.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.jwd.recipebook.services.BMIService;
import pl.edu.wszib.jwd.recipebook.web.models.BMIModel;

@Controller
@RequiredArgsConstructor
public class BMIController {
    BMIModel.OperationModel operationModel = new BMIModel.OperationModel();

    private final BMIService bmiService;

    @GetMapping("/bmi")
    public String getBmiPage(Model model){
        model.addAttribute("operationModel", operationModel);
        return "bmiPage";
    }

    @PostMapping(value="/bmi", params="divideBmi")
    public String divideBmi(@ModelAttribute("operationModel") BMIModel.OperationModel operationModel, Model model ){
        model.addAttribute("calculatedBmi", bmiService.divideBmi(operationModel));
        return "bmiPage";
    }

    @PostMapping(value="/bmi", params="clearBmi")
    public String clearBmi(@ModelAttribute("operationModel") BMIModel.OperationModel operationModel, Model model ){
        model.addAttribute("operationModel", bmiService.clearBmi(operationModel));
        model.addAttribute("result", 0);
        return "bmiPage";
    }
}