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
@Table(name = "amortizaciones")
public class Amortizacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String fecha_inicio;
  private String fecha_fin;
  private String mes;
  private Integer dias;
  private String saldo_inicial;
  private String capital;
  private String interes;
  private String iva;
  private String cuota;
  private String saldo_final;
  private String tasa;
  private Integer id_cobro;
  private Integer id_prestamo;
  private Integer id_programa;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_cobro", insertable = false, updatable = false)
  private Cobro cobro;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
  private Prestamo prestamo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_programa", insertable = false, updatable = false)
  private Programa programa;

}
