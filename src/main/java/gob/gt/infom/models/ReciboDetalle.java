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
@Table(name = "recibos_detalles")
public class ReciboDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String cantidad;
  private String tipo;
  private String descripcion;
  private String precio_unitario;
  private String descuentos;
  private String impuestos;
  private String subtotal;
  private Integer id_recibo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_recibo", insertable = false, updatable = false)
  private Recibo recibo;

}
