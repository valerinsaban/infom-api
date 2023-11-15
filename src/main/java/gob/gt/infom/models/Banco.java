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
@Table(name = "bancos")
public class Banco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nombre;
  private String siglas;
  private String r_nombre;
  private String r_apellido;
  private String fecha_nacimiento;
  private String dpi;
  private String notario_autoriza;
  private String acta_notarial;
  private String fecha_acta_notarial;
  private String libro;
  private String folio;
  private Integer id_profesion;
  private Integer id_estado_civil;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_profesion", insertable = false, updatable = false)
  private Profesion profesion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_estado_civil", insertable = false, updatable = false)
  private EstadoCivil estado_civil;

}
