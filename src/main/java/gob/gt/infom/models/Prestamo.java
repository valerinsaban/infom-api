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
@Table(name = "prestamos")
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String no_dictamen;
  private String no_convenio;
  private String no_prestamo;
  private String fecha;
  private String fecha_amortizacion;
  private String monto;
  private Integer plazo_meses;
  private String fecha_acta;
  private Double intereses;
  private Integer periodo_gracia;
  private String destino;
  private Integer no_destinos;
  private String acta;
  private String punto;
  private String fecha_memorial;
  private String certficacion;
  private String no_oficio_aj;
  private String fecha_oficio_aj;
  private String no_oficio_ger;
  private String fecha_oficio_ger;
  private String estado;
  private Integer id_tipo_prestamo;
  private Integer id_programa;
  private Integer id_municipalidad;
  private Integer id_resolucion;
  private Integer id_funcionario;
  private Integer id_regional;
  private Integer id_usuario;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_tipo_prestamo", insertable = false, updatable = false)
  private TipoPrestamo tipoPrestamo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_programa", insertable = false, updatable = false)
  private Programa programa;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipalidad", insertable = false, updatable = false)
  private Municipalidad municipalidad;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_resolucion", insertable = false, updatable = false)
  private Resolucion resolucion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_funcionario", insertable = false, updatable = false)
  private Funcionario funcionario;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_regional", insertable = false, updatable = false)
  private Regional regional;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
  private Usuario usuario;

}
