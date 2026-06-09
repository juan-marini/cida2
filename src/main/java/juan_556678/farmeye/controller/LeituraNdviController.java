package juan_556678.farmeye.controller;

import jakarta.validation.Valid;
import juan_556678.farmeye.dto.LeituraNdviDto;
import juan_556678.farmeye.service.LeituraNdviService;
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
@RequestMapping("/leituras")
public class LeituraNdviController {

    @Autowired
    private LeituraNdviService leituraNdviService;

    @GetMapping
    public ResponseEntity<List<LeituraNdviDto>> getAllLeituras() {

        List<LeituraNdviDto> list = leituraNdviService.findAllLeituras();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeituraNdviDto> getLeituraById(@PathVariable Long id) {

        LeituraNdviDto leituraNdviDto = leituraNdviService.findLeituraById(id);

        return ResponseEntity.ok(leituraNdviDto);
    }

    @PostMapping
    public ResponseEntity<LeituraNdviDto> createLeitura(@RequestBody @Valid LeituraNdviDto leituraNdviDto) {

        leituraNdviDto = leituraNdviService.saveLeitura(leituraNdviDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(leituraNdviDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(leituraNdviDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeituraNdviDto> updateLeitura(@PathVariable Long id,
                                                         @RequestBody @Valid LeituraNdviDto leituraNdviDto) {

        leituraNdviDto = leituraNdviService.updateLeitura(id, leituraNdviDto);

        return ResponseEntity.ok(leituraNdviDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeitura(@PathVariable Long id) {

        leituraNdviService.deleteLeituraById(id);

        return ResponseEntity.noContent().build();
    }
}
