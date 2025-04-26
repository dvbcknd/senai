package Atividade.senai.controller;

import Atividade.senai.model.Evento;
import Atividade.senai.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/")
    public String paginaInicial(Model model) {
        List<Evento> eventos = eventoRepository.findAll();
        model.addAttribute("eventos", eventos);
        return "index";
    }

    @PostMapping("/eventos")
    public String salvarEvento(Evento evento) {
        eventoRepository.save(evento);
        return "redirect:/";
    }
}
