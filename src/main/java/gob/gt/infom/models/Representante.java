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
@Table(name = "representantes")
public class Representante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String codigo;
  private String nombre;
  private String apellido;
  private String fecha_nacimiento;
  private String dpi;
  private String resolucion;
  private String fecha_resolucion;
  private String acuerdo;
  private String fecha_acuerdo;
  private String jd_resuelve;
  private String fecha_jd_resuelve;
  private String direccion;
  private String autorizacion;
  private String acta_toma_posecion;
  private String fecha_acta_toma_posecion;
  private String estado;
  // @Lob
  // private String imagen_carnet;
  // @Lob
  // private String imagen_acta_toma_posecion;
  // @Lob
  // private String imagen_fotografia;
  // @Lob
  // private String imagen_firma;
  private Integer id_regional;
  private Integer id_puesto;
  private Integer id_profesion;
  private Integer id_estado_civil;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_regional", insertable = false, updatable = false)
  private Regional regional;

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
