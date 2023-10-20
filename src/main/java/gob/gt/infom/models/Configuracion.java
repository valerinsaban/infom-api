package gob.gt.infom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "configuraciones")
public class Configuracion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Lob
  private String logo;
  @Lob
  private String portada;
  private String nombre;
  private String correo;
  private String telefono;
  private String direccion;
  private String sitio_web;
  private String porcentaje_interes;
  private String porcentaje_iva;
  private String periodo_fin;

}
