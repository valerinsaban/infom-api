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
@Table(name = "aportes")
public class Aporte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer anio;
  private Integer mes;
  private Integer monto;
  private Integer id_municipalidad;
  private Integer id_garantia;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipalidad", insertable = false, updatable = false)
  private Municipalidad municipalidad;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_garantia", insertable = false, updatable = false)
  private Garantia garantia;

}
