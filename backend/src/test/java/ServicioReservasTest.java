import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ServicioReservasTest {
    private final ServicioReservas servicio =
        new ServicioReservas();

    @Test
    void permiteReservaAntesDelCierre() {
        LocalDateTime fecha =
            LocalDateTime.of(2026, 10, 30, 17, 0);
        assertTrue(servicio.puedeReservar(10, 3, fecha));
    }

    @Test
    void rechazaReservaDespuesDelCierre() {
        LocalDateTime fecha =
            LocalDateTime.of(2026, 10, 30, 18, 1);
        assertFalse(servicio.puedeReservar(10, 3, fecha));
    }

    @Test
    void rechazaReservaSuperiorAlInventario() {
        LocalDateTime fecha =
            LocalDateTime.of(2026, 10, 30, 17, 0);
        assertFalse(servicio.puedeReservar(2, 5, fecha));
    }
}
