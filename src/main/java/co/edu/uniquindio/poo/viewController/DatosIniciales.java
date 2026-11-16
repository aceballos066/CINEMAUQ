package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import java.time.LocalDate;
import java.time.LocalTime;

public final class DatosIniciales {
    private DatosIniciales() { }

    public static Cine crearCine() {
        Cine cine = new Cine("CINEMAUQ", "901234567-8", "Universidad del Quindío - Armenia");

        cine.configurarTarifa(TipoEntrada.GENERAL, 15000);
        cine.configurarTarifa(TipoEntrada.ESTUDIANTE, 12000);
        cine.configurarTarifa(TipoEntrada.NINO, 10000);
        cine.configurarTarifa(TipoEntrada.ADULTO_MAYOR, 11000);

        Administrador raul = new Administrador("ADM-001", "Raúl", "ADM001");
        Operario anderson = new Operario("OP-001", "Anderson Ceballos", "OPE001", LocalDate.now().minusYears(1), 2200000);
        Operario cristian = new Operario("OP-002", "Cristian Sandoval", "OPE002", LocalDate.now().minusMonths(10), 2200000);
        Operario carlos = new Operario("OP-003", "Carlos Castaño", "OPE003", LocalDate.now().minusMonths(8), 2200000);
        cine.registrarPersona(raul);
        cine.registrarPersona(anderson);
        cine.registrarPersona(cristian);
        cine.registrarPersona(carlos);

        Cliente juan = new Cliente("CL-001", "Juan Pérez", "AF001", TipoCliente.GENERAL);
        Cliente laura = new Cliente("CL-002", "Laura Gómez", "AF002", TipoCliente.ESTUDIANTE);
        Cliente mateo = new Cliente("CL-003", "Mateo Ruiz", "AF003", TipoCliente.GENERAL);
        cine.registrarPersona(juan);
        cine.registrarPersona(laura);
        cine.registrarPersona(mateo);
        cine.registrarMembresia(laura, new Membresia("MEM001", LocalDate.now().minusMonths(2), TipoMembresia.PLATA));
        cine.registrarMembresia(mateo, new Membresia("MEM002", LocalDate.now().minusMonths(5), TipoMembresia.ORO));

        registrarPeliculas(cine);
        registrarSalas(cine);
        registrarFunciones(cine);

        cine.venderEntrada(laura, anderson, "F001", TipoEntrada.ESTUDIANTE, "A1");
        cine.venderEntrada(mateo, cristian, "F002", TipoEntrada.GENERAL, "A1");
        cine.venderEntrada(juan, carlos, "F003", TipoEntrada.GENERAL, "A2");

        return cine;
    }

    private static void registrarPeliculas(Cine cine) {
        cine.registrarPelicula(new Pelicula("P001", "Interstellar", Genero.DRAMA, 169, Clasificacion.MAYORES_12));
        cine.registrarPelicula(new Pelicula("P002", "Avengers: Endgame", Genero.ACCION, 181, Clasificacion.MAYORES_12));
        cine.registrarPelicula(new Pelicula("P003", "Spider-Man: No Way Home", Genero.ACCION, 148, Clasificacion.MAYORES_12));
        cine.registrarPelicula(new Pelicula("P004", "The Batman", Genero.ACCION, 176, Clasificacion.MAYORES_15));
        cine.registrarPelicula(new Pelicula("P005", "Joker", Genero.DRAMA, 122, Clasificacion.MAYORES_18));
        cine.registrarPelicula(new Pelicula("P006", "Coco", Genero.ANIMACION, 105, Clasificacion.TODOS));
        cine.registrarPelicula(new Pelicula("P007", "Inside Out 2", Genero.ANIMACION, 96, Clasificacion.TODOS));
        cine.registrarPelicula(new Pelicula("P008", "Dune: Part Two", Genero.ACCION, 166, Clasificacion.MAYORES_12));
        cine.registrarPelicula(new Pelicula("P009", "Toy Story 4", Genero.ANIMACION, 100, Clasificacion.TODOS));
        cine.registrarPelicula(new Pelicula("P010", "Avatar: The Way of Water", Genero.ACCION, 192, Clasificacion.MAYORES_12));
    }

    private static void registrarSalas(Cine cine) {
        cine.registrarSala(new Sala("S001", "Sala Central", 30, 5, 6, TipoSala.TRADICIONAL));
        cine.registrarSala(new Sala("S002", "Sala VIP", 20, 4, 5, TipoSala.VIP));
        cine.registrarSala(new Sala("S003", "Sala 3D", 25, 5, 5, TipoSala.TRES_D));
    }

    private static void registrarFunciones(Cine cine) {
        LocalDate manana = LocalDate.now().plusDays(1);
        LocalDate pasado = LocalDate.now().plusDays(2);
        LocalDate tercerDia = LocalDate.now().plusDays(3);

        programar(cine, "F001", "P001", "S001", manana, LocalTime.of(18, 30), 15000);
        programar(cine, "F002", "P003", "S002", manana, LocalTime.of(20, 0), 17000);
        programar(cine, "F003", "P006", "S001", pasado, LocalTime.of(15, 0), 15000);
        programar(cine, "F004", "P008", "S003", pasado, LocalTime.of(19, 0), 18000);
        programar(cine, "F005", "P007", "S002", manana, LocalTime.of(14, 0), 16000);
        programar(cine, "F006", "P004", "S001", tercerDia, LocalTime.of(21, 0), 15000);
        programar(cine, "F007", "P002", "S003", manana, LocalTime.of(17, 0), 18000);
        programar(cine, "F008", "P010", "S003", tercerDia, LocalTime.of(18, 0), 18000);
        programar(cine, "F009", "P001", "S002", pasado, LocalTime.of(20, 30), 17000);
        programar(cine, "F010", "P003", "S001", tercerDia, LocalTime.of(16, 30), 15000);
    }

    private static void programar(Cine cine, String codigo, String codPelicula, String codSala,
                                  LocalDate fecha, LocalTime hora, double base) {
        cine.programarFuncion(new Funcion(codigo, cine.buscarPelicula(codPelicula), cine.buscarSala(codSala), fecha, hora, base));
    }
}