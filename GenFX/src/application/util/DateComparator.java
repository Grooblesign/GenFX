package application.util;

import java.util.Comparator;

import application.models.AbstractDateModel;
import application.models.Event;

public class DateComparator implements Comparator<AbstractDateModel> {
	@Override
	public int compare(AbstractDateModel o1, AbstractDateModel o2) {
		
		int dateValue1 = calculateDateValue(o1.getDate().get());
		int dateValue2 = calculateDateValue(o2.getDate().get());
		
		if (dateValue1 < dateValue2) {
			return -1;
		}
		if (dateValue1 > dateValue2) {
			return 1;
		}
		return 0;
	}

	int calculateDateValue(String dateString) {
		
		int dateValue = 0;
		
		dateString = dateString.toLowerCase().trim();
		
		if (dateString.length() > 0) {
			String[] parts = dateString.split(" ");
			
			for (String part : parts) {
				if (part.equals("q2")) {
					dateValue += 90;
				}
				if (part.equals("q3")) {
					dateValue += 181;
				}
				if (part.equals("q4")) {
					dateValue += 283;
				}
				
				if (part.length() == 4) {
					try {
						dateValue += Integer.parseInt(part);
					} catch (Exception ex) {
					}
				}
				
				if (part.equals("or") || part.equals("-") || part.equals("and")) {
					break;
				}
			}
		}
		
		return dateValue;
	}
}
