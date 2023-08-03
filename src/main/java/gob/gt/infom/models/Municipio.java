package gob.gt.infom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "municipios")
public class Municipio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @NotNull
  @Size(max = 80)
  private String codigo;

  @NotBlank
  @NotNull
  private String descripcion;

  @NotNull
  private Integer id_departamento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
  private Departamento departamento;

}
