package gob.gt.infom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "municipalidades")
public class Municipalidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String direccion;
  @NotNull
  private Integer id_departamento;
  @NotNull
  private Integer id_municipio;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
  private Departamento departamento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
  private Municipio municipio;

}
