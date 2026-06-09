package juan_556678.farmeye.service;

import jakarta.persistence.EntityNotFoundException;
import juan_556678.farmeye.dto.LeituraNdviDto;
import juan_556678.farmeye.entities.Fazenda;
import juan_556678.farmeye.entities.LeituraNdvi;
import juan_556678.farmeye.exceptions.DatabaseException;
import juan_556678.farmeye.exceptions.ResourceNotFoundException;
import juan_556678.farmeye.repositories.FazendaRepository;
import juan_556678.farmeye.repositories.LeituraNdviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeituraNdviService {

    @Autowired
    private LeituraNdviRepository leituraNdviRepository;

    @Autowired
    private FazendaRepository fazendaRepository;

    @Transactional(readOnly = true)
    public List<LeituraNdviDto> findAllLeituras() {
        List<LeituraNdvi> leituras = leituraNdviRepository.findAll();
        return leituras.stream().map(LeituraNdviDto::new).toList();
    }

    @Transactional(readOnly = true)
    public LeituraNdviDto findLeituraById(Long id) {
        LeituraNdvi leitura = leituraNdviRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new LeituraNdviDto(leitura);
    }

    @Transactional
    public LeituraNdviDto saveLeitura(LeituraNdviDto leituraNdviDto) {
        try {
            LeituraNdvi leitura = new LeituraNdvi();
            copyDtoToLeitura(leituraNdviDto, leitura);
            leitura = leituraNdviRepository.save(leitura);
            return new LeituraNdviDto(leitura);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível salvar leitura. Fazenda inexistente. ID: " + leituraNdviDto.getFazenda());
        }
    }

    @Transactional
    public LeituraNdviDto updateLeitura(Long id, LeituraNdviDto leituraNdviDto) {
        try {
            LeituraNdvi leitura = leituraNdviRepository.getReferenceById(id);
            copyDtoToLeitura(leituraNdviDto, leitura);
            leitura = leituraNdviRepository.save(leitura);
            return new LeituraNdviDto(leitura);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteLeituraById(Long id) {
        if (!leituraNdviRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        leituraNdviRepository.deleteById(id);
    }

    private void copyDtoToLeitura(LeituraNdviDto leituraNdviDto, LeituraNdvi leitura) {
        leitura.setNomeArea(leituraNdviDto.getNomeArea());
        leitura.setValorNdvi(leituraNdviDto.getValorNdvi());
        leitura.setStatus(leituraNdviDto.getStatus());
        leitura.setDataLeitura(leituraNdviDto.getDataLeitura());
        Fazenda fazenda = fazendaRepository.getReferenceById(leituraNdviDto.getFazenda().getId());
        leitura.setFazenda(fazenda);
    }
}
