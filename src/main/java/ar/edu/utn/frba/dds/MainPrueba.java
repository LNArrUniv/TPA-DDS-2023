package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Modelos.PersonaDesignada;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;

public class MainPrueba {

  public static void main(String[] args) {
    PersonaDesignada pepeEncargadoCNRT = new PersonaDesignada("Pepe", "Jose", "pepejose23", "1234");
    PersonaDesignada manuelEncargadoTrenesArg = new PersonaDesignada("Manuel", "Jose", "manujose674", "4321");
    OrganismoDeControl cnrt = new OrganismoDeControl("Comisión Nacional de Regulación del Transporte", pepeEncargadoCNRT);
    EntidadPropietaria trenesArg = new EntidadPropietaria("Trenes Argentinos",
                                                        "Gestionamos y operamos la red ferroviaria del país y hacemos las obras necesarias para que el traslado de pasajeros y el transporte de carga por tren sea cada vez más seguro y eficiente.",
                                                                  manuelEncargadoTrenesArg, cnrt);
    Entidad lineaSarmiento = new Entidad("Linea Sarmiento",
                                          "Una de las 7 líneas suburbanas de los Ferrocarriles metropolitanos de Buenos Aires, presta servicios de pasajeros entre las estaciones Once, en el barrio porteño de Balvanera, y las estaciones de Moreno, Lobos y Mercedes en el oeste del Gran Buenos Aires.",
                                                    trenesArg, null);

    EntityManagerHelper.beginTransaction();
    EntityManagerHelper.persist(lineaSarmiento);
    EntityManagerHelper.commit();
  }

}
