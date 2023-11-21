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
@Table(name = "recibos")
public class Recibo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String numero;
  private String fecha;
  private String nit;
  private String nombre;
  private String monto;
  private String estado;
  private String descripcion;
  private String firma;
  private Integer id_factura;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_factura", insertable = false, updatable = false)
  private Factura factura;

}
