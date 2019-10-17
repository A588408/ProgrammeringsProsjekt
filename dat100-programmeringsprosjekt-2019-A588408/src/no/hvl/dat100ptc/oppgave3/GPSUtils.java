package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {
		double max;
		max = da[0];
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {
		double min;
		// TODO - START
		min = da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUT
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudesTab = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latitudesTab[i] = gpspoints[i].getLatitude();
		}
		return latitudesTab;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		// TODO - START
		double[] longitudesTab = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitudesTab[i] = gpspoints[i].getLongitude();
		}
		return longitudesTab;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double d;
		// TODO - START
		double latitude1 = Math.toRadians(gpspoint1.getLatitude()), latitude2 = Math.toRadians(gpspoint2.getLatitude());
		double teta = Math.toRadians(gpspoint2.getLatitude() - gpspoint1.getLatitude());
		double lamda = Math.toRadians(gpspoint2.getLongitude() - gpspoint1.getLongitude());
		double a = (Math.pow(Math.sin(teta / 2), 2))
				+ (Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(lamda) / 2, 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d= c * 6371000;
		return d ;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double secs = gpspoint2.getTime()-gpspoint1.getTime();
		double speed;
		// TODO - START
		speed = (distance(gpspoint1, gpspoint2) / 1000) / (secs / 3600);
		return speed;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}
	

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		// TODO - START
		int hh = secs / 3600;
		int mm = (secs % 3600) / 60;
		int ss = (secs % 3600) % 60;
		String ftime = hh + TIMESEP + mm + TIMESEP + ss;
		while (ftime.length() < 10) {
			ftime = " " + ftime;
		}
		return ftime;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		String str;
		// TODO - START
		d = Math.round(d * 100) / 100.0;
		String d2 = Double.toString(d);
		while (d2.length() < TEXTWIDTH) {
			d2 = " " + d2;
		}
		return d2;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}
}
