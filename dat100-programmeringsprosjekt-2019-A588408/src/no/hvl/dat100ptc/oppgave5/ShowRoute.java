package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;
	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {
		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		gpspoints = gpscomputer.getGPSPoints();
	}

	public static void main(String[] args) {

		launch(args);

	}

	public void run() {
		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);
		showRouteMap(MARGIN + MAPYSIZE);
		playRoute(MARGIN + MAPYSIZE);
		
	}

	// antall x-pixels per lengdegrad
	public double xstep() {
		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));
		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		return ystep;
		// TODO - START
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public void showRouteMap(int ybase) {
		// double x = gpspoints[0].getLatitude();
		// double y = ybase;
		// fillCircle((int) x, (int) y, 4);

		int x = MARGIN;
		int y = 300;
		for (int i = 1; i < (gpspoints.length - 1); i++) {

			// (int) (MAPYSIZE /
			// (Math.abs(GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints))
			// - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints)))));
			if (gpspoints[i].getLongitude() < gpspoints[i - 1].getLongitude()) {
				x -= 4;
			} else {
				x += 4;
			}
			if (gpspoints[i].getLatitude() < gpspoints[i - 1].getLatitude()) {
				y += 10;
			} else {
				y -= 10;
			}
			setColor(0, 255, 0);
			fillCircle(x, y, 1);
			int x2 = x;
			int y2 = y;
			if (gpspoints[i + 1].getLongitude() < gpspoints[i].getLongitude()) {
				x2 -= 4;
			} else {
				x2 += 4;
			}
			if (gpspoints[i + 1].getLatitude() < gpspoints[i].getLatitude()) {
				y2 += 10;
			} else {
				y2 -= 10;
			}

			drawLine(x, y, x2, y2);

		}

		// y += MAPXSIZE / (gpspoints[i + 1].getLatitude() -
		// gpspoints[i].getLatitude());
		// x += MAPXSIZE / Math.abs((gpspoints[i + 1].getLongitude() -
		// gpspoints[i].getLatitude()));
		// x+=(int)((gpspoints[i+1].getLatitude()-gpspoints[i].getLatitude()))/800;
		// y+=(int)((gpspoints[i+1].getLongitude()-gpspoints[i].getLatitude()))/800;
		// }
		setColor(0,0,0);
		drawString("        Speed" + "            "+"Average speed", MAPXSIZE+MARGIN-gpspoints.length - 1, MARGIN);
		setColor(200, 50, 150);
		fillCircle(10+MAPXSIZE+MARGIN-gpspoints.length - 1, MARGIN-5,5);
		setColor(0, 200, 0);
		fillCircle(80+MAPXSIZE+MARGIN-gpspoints.length - 1, MARGIN-5,5);
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int speed = (int) GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
			setColor(200, 50, 150);
			int xx = MAPXSIZE+MARGIN-gpspoints.length - 1 + i;
			drawLine(xx , 150, xx, speed + MARGIN);
			setColor(0, 200, 0);
			fillCircle(xx, MARGIN + (int) Math.round(gpscomputer.averageSpeed()), 2);
			
		}
		// TODO - START
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public void showStatistics() {
		int TEXTDISTANCE = 20;
		setColor(0, 0, 0);
		setFont("Courier", 12);
		int lng = gpspoints.length - 1;

		String TT = "Total time is:" + "       " + GPSUtils.formatTime(gpspoints[lng].getTime()) + "  Hours";
		String TD = "Total distance is:" + "   "
				+ GPSUtils.formatDouble((GPSUtils.distance(gpspoints[0], gpspoints[lng]) / 1000)) + "     Km";
		String TE = "Total elevation is:" + "  " + GPSUtils.formatDouble(ShowProfile.maxElevation(gpspoints))
				+ "  meter";
		// feil her: *fikset
		String MS = "Max Speed is:" + "        " + GPSUtils.formatDouble((gpscomputer.maxSpeed())) + "   Km/h";
		String AS = "Average speed is:" + "    " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + "   Km/h";
		String KHL = "Burned calories are:" + " " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + "   Kcal";

		drawString(TT, MARGIN, MARGIN);
		drawString(TD, MARGIN, MARGIN + TEXTDISTANCE);
		drawString(TE, MARGIN, MARGIN + 2 * TEXTDISTANCE);
		drawString(MS, MARGIN, MARGIN + 3 * TEXTDISTANCE);
		drawString(AS, MARGIN, MARGIN + 4 * TEXTDISTANCE);
		drawString(KHL, MARGIN, MARGIN + 5 * TEXTDISTANCE);
		// TODO - START
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {
		showStatistics();
		int x = MARGIN;
		int y = 300;
		setColor(93, 153, 206);
		int id = fillCircle(x, y, 10);
		for (int i = 1; i < (gpspoints.length - 1); i++) {

			if (gpspoints[i].getLongitude() < gpspoints[i - 1].getLongitude()) {
				x -= 4;
			} else {
				x += 4;
			}
			if (gpspoints[i].getLatitude() < gpspoints[i - 1].getLatitude()) {
				y += 10;
			} else {
				y -= 10;
			}
			setColor(93, 173, 226);
			fillCircle(x, y, 3);
			setColor(93, 173, 226);
			moveCircle(id, x, y);
			pause(100);
			// TODO - START
			// throw new UnsupportedOperationException(TODO.method());
			// TODO - SLUTT
		}

	}
}
