package shoesStore.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {
    public static java.sql.Date randomSqlDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        LocalDate randomLocalDate = LocalDate.ofEpochDay(randomEpochDay);
        return java.sql.Date.valueOf(randomLocalDate);
    }
}
