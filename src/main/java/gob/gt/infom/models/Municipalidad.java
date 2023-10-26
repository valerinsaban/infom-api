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
@Table(name = "municipalidades")
public class Municipalidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String direccion;
  private String correo;
  private String telefono;
  private String nit;
  private String no_cuenta;
  private Integer id_departamento;
  private Integer id_municipio;
  private Integer id_banco;
  private Integer id_partido_politico;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
  private Departamento departamento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
  private Municipio municipio;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_banco", insertable = false, updatable = false)
  private Banco banco;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_partido_politico", insertable = false, updatable = false)
  private PartidoPolitico partido_politico;

}
