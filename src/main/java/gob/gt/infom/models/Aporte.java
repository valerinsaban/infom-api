package gob.gt.infom.models;

import jakarta.persistence.Column;
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
  private String mes;
  private String constitucional;
  private String iva_paz;
  private String vehiculos;
  private String petroleo;
  private String total;
  @Column(name="codigo_departamento")
  private String codigoDepartamento;
  @Column(name="codigo_municipio")
  private String codigoMunicipio;
  private Integer id_importe;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_importe", insertable = false, updatable = false)
  private Importe importe;

}
