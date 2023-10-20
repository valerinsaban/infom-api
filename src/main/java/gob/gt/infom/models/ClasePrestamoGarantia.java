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
@Table(name = "clases_prestamos_garantias")
public class ClasePrestamoGarantia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer id_garantia;
  private Integer id_clase_prestamo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_garantia", insertable = false, updatable = false)
  private Garantia garantia;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_clase_prestamo", insertable = false, updatable = false)
  private ClasePrestamo clasePrestamo;

}