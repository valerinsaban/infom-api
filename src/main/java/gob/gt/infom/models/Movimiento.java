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
@Table(name = "movimientos")
public class Movimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String fecha;
  private String saldo_inicial;
  private String cargo;
  private String abono;
  private String saldo_final;
  private String descripcion;
  private String capital;
  private String interes;
  private String iva;
  private Integer id_prestamo;
  private Integer id_orden_pago;
  private Integer id_recibo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
  private Prestamo prestamo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_orden_pago", insertable = false, updatable = false)
  private OrdenPago ordenPago;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_recibo", insertable = false, updatable = false)
  private Recibo recibo;

}
