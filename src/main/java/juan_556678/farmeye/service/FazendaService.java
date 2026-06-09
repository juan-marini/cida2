package juan_556678.farmeye.service;

import jakarta.persistence.EntityNotFoundException;
import juan_556678.farmeye.dto.FazendaDto;
import juan_556678.farmeye.entities.Fazenda;
import juan_556678.farmeye.exceptions.DatabaseException;
import juan_556678.farmeye.exceptions.ResourceNotFoundException;
import juan_556678.farmeye.repositories.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FazendaService {

    @Autowired
    private FazendaRepository fazendaRepository;

    @Transactional(readOnly = true)
    public List<FazendaDto> findAllFazendas() {

        return fazendaRepository.findAll()
                .stream().map(FazendaDto::new).toList();
    }

    @Transactional(readOnly = true)
    public FazendaDto findFazendaById(Long id) {

        Fazenda fazenda = fazendaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        return new FazendaDto(fazenda);
    }

    @Transactional
    public FazendaDto saveFazenda(FazendaDto inputDto) {

        Fazenda fazenda = new Fazenda();
        copyDtoToFazenda(inputDto, fazenda);
        fazenda = fazendaRepository.save(fazenda);
        return new FazendaDto(fazenda);
    }

    @Transactional
    public FazendaDto updateFazenda(Long id, FazendaDto inputDto) {

        try {
            Fazenda fazenda = fazendaRepository.getReferenceById(id);
            copyDtoToFazenda(inputDto, fazenda);
            fazenda = fazendaRepository.save(fazenda);
            return new FazendaDto(fazenda);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteFazendaById(Long id) {

        if (!fazendaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        try {
            fazendaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir fazenda. Existem leituras associadas a ela");
        }
    }

    private void copyDtoToFazenda(FazendaDto inputDto, Fazenda fazenda) {

        fazenda.setNome(inputDto.getNome());
        fazenda.setMunicipio(inputDto.getMunicipio());
        fazenda.setAreaHectares(inputDto.getAreaHectares());
        fazenda.setDataCadastro(inputDto.getDataCadastro());
    }
}
