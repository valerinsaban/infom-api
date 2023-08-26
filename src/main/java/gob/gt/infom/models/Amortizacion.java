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
  private Long id;
  private String capital;
  private String intereses;
  private String cuota;
  private String saldo;
  private String fecha_inicio;
  private String fecha_fin;
  private Integer dias;
  private Long id_prestamo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
  private Prestamo prestamo;

}
