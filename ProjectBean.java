package com.edeal.recruitment;
package com.edeal.recruitment;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
public class ProjectBean extends ProjectGenericBean {

  private static final String toStringSeparator = " - ";

  private String titre;
  private int numero;
  private Date dateDebut;
  private Date dateFin;


  public ProjectBean(String titre, int numero, Date dateDebut, Date dateFin) {
    this.titre = titre;
		this.numero = numero;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
}

  public String toString() {
    return new StringBuilder().append(numero).append(toStringSeparator)
        .append(StringUtils.isEmpty(titre) ? "-?-" : titre).toString();
  }

  @Override
  public Period getDuration() {
    if (dateDebut != null && dateFin != null) {
      LocalDate startDate = toLocalDate(dateDebut);
      LocalDate endDate = toLocalDate(dateFin);
      return Period.between(startDate, endDate);
    }
    return Period.ZERO;
  }

  @Override
  public String getDurationToString() {

    return getDuration().getYears() + "Year(s)" + toStringSeparator + getDuration().getMonths()
        + "Month(s)" + toStringSeparator + getDuration().getDays() + "Day(s)";
  }

  @Override
  public String getDurationToString(String flag) {
    LocalDate startLocalDate = toLocalDate(dateDebut);
    LocalDate endLocalDate = toLocalDate(dateFin);
    switch (flag.trim().toUpperCase()) {
      case "YEAR":
        return ChronoUnit.YEARS.between(startLocalDate, endLocalDate) + " Year(s)";
      case "MONTH":
        return ChronoUnit.MONTHS.between(startLocalDate, endLocalDate) + " Month(s)";
      case "DAY":
        return ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + " Day(s)";
      //if the flag is not valid, call the getDurationToString method without a flag
      default:
        return getDurationToString();
    }
  }

  //move the conversion of Date to LocalDate to a separate method to avoid repetition
  public LocalDate toLocalDate(Date date) {
    Calendar calDate = new GregorianCalendar();
    calDate.setTime(date);
    return LocalDate.of(calDate.get(Calendar.YEAR), calDate.get(Calendar.MONTH), calDate.get(Calendar.DAY_OF_MONTH));
  }
}
