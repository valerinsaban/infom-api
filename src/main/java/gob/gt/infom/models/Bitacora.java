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
@Table(name = "bitacoras")
public class Bitacora {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String fecha;
  private String tipo;
  private String accion;
  private String descripcion;
  private String body;
  private String id_usuario;
  private String id_menu;
  private String id_submenu;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
  private Usuario usuario;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_menu", insertable = false, updatable = false)
  private Menu menu;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_submenu", insertable = false, updatable = false)
  private Submenu submenu;

}
