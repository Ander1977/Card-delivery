import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    private static final String DATE__FORMAT = "dd.MM.yyyy";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE__FORMAT);

    public String localDate() {
        Date currentDate = new Date();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(3);
        Date currentDatePlusThreeDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return dateFormat.format(currentDatePlusThreeDay) ;
    }

}
