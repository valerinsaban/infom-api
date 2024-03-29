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
@Table(name = "amortizaciones_detalles")
public class AmortizacionDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String mes;
  private String fecha_inicio;
  private String fecha_fin;
  private Integer dias;
  private String saldo_inicial;
  private String capital;
  private String interes;
  private String iva;
  private String cuota;
  private String saldo_final;
  private String tasa;
  private Integer id_amortizacion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_amortizacion", insertable = false, updatable = false)
  private Amortizacion amortizacion;

}
