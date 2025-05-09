package Atividade.senai.controller;

import Atividade.senai.model.Evento;
import Atividade.senai.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoRestController {

    @Autowired
    private EventoRepository eventoRepository;

    // GET - Listar todos
    @GetMapping
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {
            return ResponseEntity.ok(optionalEvento.get());
        } else {
            return ResponseEntity.status(404).body("Evento não encontrado");
        }
    }


    // POST - Criar novo
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Evento evento) {
        Evento salvo = eventoRepository.save(evento);
        return ResponseEntity.status(201).body(salvo);
    }

    // PUT - Atualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Evento eventoAtualizado) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.setTitulo(eventoAtualizado.getTitulo());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setData(eventoAtualizado.getData());
            eventoRepository.save(evento);
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.status(404).body("Evento não encontrado");
        }
    }


    // DELETE - Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.ok("Evento excluído com sucesso");
        } else {
            return ResponseEntity.status(404).body("Evento não encontrado");
        }
    }
}
