package juan_556678.farmeye.controller;

import jakarta.validation.Valid;
import juan_556678.farmeye.dto.FazendaDto;
import juan_556678.farmeye.service.FazendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fazendas")
public class FazendaController {

    @Autowired
    private FazendaService fazendaService;

    @GetMapping
    public ResponseEntity<List<FazendaDto>> getAllFazendas() {
        List<FazendaDto> fazendas = fazendaService.findAllFazendas();
        return ResponseEntity.ok(fazendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FazendaDto> getFazendaById(@PathVariable Long id) {
        FazendaDto fazendaDto = fazendaService.findFazendaById(id);
        return ResponseEntity.ok(fazendaDto);
    }

    @PostMapping
    public ResponseEntity<FazendaDto> createFazenda(@RequestBody @Valid FazendaDto fazendaDto) {
        fazendaDto = fazendaService.saveFazenda(fazendaDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(fazendaDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(fazendaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FazendaDto> updateFazenda(@PathVariable Long id,
                                                     @Valid @RequestBody FazendaDto fazendaDto) {
        fazendaDto = fazendaService.updateFazenda(id, fazendaDto);
        return ResponseEntity.ok(fazendaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFazenda(@PathVariable Long id) {
        fazendaService.deleteFazendaById(id);
        return ResponseEntity.noContent().build();
    }
}
