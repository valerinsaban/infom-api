package gob.gt.infom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "analistas")
public class Analista {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String codigo;

  private String nombre;

  private String apellido;

  private String cedula_orden;

  private String cedula_registro;

  private String dpi;

  private Integer id_departamento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
  private Departamento departamento;

  private Integer id_municipio;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
  private Municipio municipio;

}
