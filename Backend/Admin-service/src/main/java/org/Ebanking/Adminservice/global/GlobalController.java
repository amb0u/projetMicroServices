package org.Ebanking.Adminservice.global;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void addAttributes(ModelMap model) {

        model.addAttribute("ttt",5 );
    }

}
