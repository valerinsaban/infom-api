package gob.gt.infom.models;

import java.util.Date;

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
@Table(name = "empleados")
public class Empleado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nombre;

  private String apellido;

  private Date fecha_nacimiento;

  private String cedula_orden;

  private String cedula_registro;

  private String dpi;

  private String carnet;

  private Date fecha_carnet;

  private String imagen_carnet;

  private String acta_toma_posecion;

  private Date fecha_acta_toma_posecion;

  private String imagen_acta_toma_posecion;

  private String imagen_fotografia;

  private String imagen_firma;

  private Integer id_departamento;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_departamento", insertable = false, updatable = false)
  private Departamento departamento;

  private Integer id_municipio;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
  private Municipio municipio;

  private Integer id_tipo_empleado;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_tipo_empleado", insertable = false, updatable = false)
  private TipoEmpleado tipo_empleado;

  private Integer id_profesion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_profesion", insertable = false, updatable = false)
  private Profesion profesion;

  private Integer id_estado_civil;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_estado_civil", insertable = false, updatable = false)
  private EstadoCivil estado_civil;

}
