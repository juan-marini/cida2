package juan_556678.farmeye.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import juan_556678.farmeye.entities.Fazenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FazendaDto {

    private Long id;

    @NotBlank(message = "Campo nome não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Campo município não pode ser vazio, nulo ou em branco")
    @Size(min = 2, max = 80, message = "O campo município deve ter entre 2 e 80 caracteres")
    private String municipio;

    @NotNull(message = "O campo área em hectares é obrigatório")
    @Positive(message = "O campo área em hectares deve ser um número positivo e maior que zero")
    private Double areaHectares;

    @NotNull(message = "O campo data de cadastro é obrigatório")
    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    private LocalDate dataCadastro;

    public FazendaDto(Fazenda fazenda) {
        id = fazenda.getId();
        nome = fazenda.getNome();
        municipio = fazenda.getMunicipio();
        areaHectares = fazenda.getAreaHectares();
        dataCadastro = fazenda.getDataCadastro();
    }
}
