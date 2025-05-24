package Atividade.senai.controller;

import Atividade.senai.model.Evento;
import Atividade.senai.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;



@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/")
    public String paginaInicial(Model model) {
        List<Evento> eventos = eventoRepository.findAll();
        model.addAttribute("eventos", eventos);
        model.addAttribute("evento", new Evento());  // Adicionado aqui
        return "index";
    }


    @PostMapping("/eventos")
    public String salvarEvento(@Valid Evento evento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Evento> eventos = eventoRepository.findAll();
            model.addAttribute("eventos", eventos);
            model.addAttribute("evento", evento); // mantém o objeto com erros no model
            return "index";
        }
        eventoRepository.save(evento);
        return "redirect:/";
    }


    @DeleteMapping("/eventos/{id}")
    public String deletarEvento(@PathVariable Long id) {
        eventoRepository.deleteById(id);
        return "redirect:/";
    }

    @Configuration
    public class WebConfig {

        @Bean
        public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
            return new HiddenHttpMethodFilter();
        }
    }


}
