package juan_556678.farmeye.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import juan_556678.farmeye.entities.LeituraNdvi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeituraNdviDto {

    private Long id;

    @NotBlank(message = "Campo nome da área não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 100, message = "O campo nome da área deve ter entre 3 e 100 caracteres")
    private String nomeArea;

    @NotNull(message = "O campo valor NDVI é obrigatório")
    @DecimalMin(value = "-1.0", message = "O valor NDVI deve ser maior ou igual a -1.0")
    @DecimalMax(value = "1.0", message = "O valor NDVI deve ser menor ou igual a 1.0")
    private Double valorNdvi;

    @NotBlank(message = "Campo status não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 20, message = "O campo status deve ter entre 3 e 20 caracteres")
    private String status;

    @NotNull(message = "O campo data da leitura é obrigatório")
    @PastOrPresent(message = "A data da leitura não pode ser futura")
    private LocalDate dataLeitura;

    @NotNull(message = "Campo fazenda requerido")
    private FazendaDto fazenda;

    public LeituraNdviDto(LeituraNdvi leitura) {
        id = leitura.getId();
        nomeArea = leitura.getNomeArea();
        valorNdvi = leitura.getValorNdvi();
        status = leitura.getStatus();
        dataLeitura = leitura.getDataLeitura();
        fazenda = new FazendaDto(leitura.getFazenda());
    }
}
