package br.com.mastertech.dnd;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.model.IModel;

@Controller
public class AppController {

    @GetMapping
    public String mostrarHome(){
        return "index";
    }


    @GetMapping("/rolagem")
    public String mostrarRolagem(){
        return "rolagem";
    }

    @PostMapping("/resultado")
    public String mostrarResultado(@ModelAttribute Rolagem rolagem, Model model) throws DadoInvalidoException {

        try{
            Dado dado = new Dado(rolagem.getLados());
            Sorteador sorteador = new Sorteador(dado);
            Resultado resultado = sorteador.sortear(rolagem.getVezes());

            model.addAttribute("resultado", resultado);
            return "resultado";
        }catch (DadoInvalidoException e){
            return "erro";
        }
    }

}
