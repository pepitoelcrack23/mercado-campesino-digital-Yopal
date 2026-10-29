import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class ServicioReservas {
    // RF-06: validar hora límite.
    // RF-07: verificar inventario suficiente.
    public boolean puedeReservar(
            int disponible,
            int solicitado,
            LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new IllegalArgumentException(
                "La fecha y hora son obligatorias");
        }
        if (disponible < 0 || solicitado <= 0) {
            throw new IllegalArgumentException(
                "Cantidad inválida");
        }
        if (fechaHora.getDayOfWeek() == DayOfWeek.SATURDAY
                || fechaHora.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }
        LocalDateTime cierre = fechaHora
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))
            .with(LocalTime.of(18, 0));
        if (fechaHora.isAfter(cierre)) {
            return false;
        }
        return solicitado <= disponible;
    }
}
