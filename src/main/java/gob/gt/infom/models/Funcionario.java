package gob.gt.infom.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "funcionarios")
public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String codigo;
  private String nombre;
  private String apellido;
  private Date fecha_nacimiento;
  private String dpi;
  private String carnet;
  private Date fecha_carnet;
  private String acta_toma_posecion;
  private Date fecha_acta_toma_posecion;
  private String estado;
  @Lob
  private String imagen_carnet;
  @Lob
  private String imagen_acta_toma_posecion;
  @Lob
  private String imagen_fotografia;
  @Lob
  private String imagen_firma;
  private Integer id_municipalidad;
  private Integer id_puesto;
  private Integer id_profesion;
  private Integer id_estado_civil;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipalidad", insertable = false, updatable = false)
  private Municipalidad municipalidad;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_puesto", insertable = false, updatable = false)
  private Puesto puesto;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_profesion", insertable = false, updatable = false)
  private Profesion profesion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_estado_civil", insertable = false, updatable = false)
  private EstadoCivil estado_civil;

}
