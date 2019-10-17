package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {
	private static GPSPoint[] gpspoints;
	private static int MARGIN = 50; // margin on the sides

	// FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500;

	static int maxElevation(GPSPoint[] gpspoints) {
		double[] elevations = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			elevations[i] = gpspoints[i].getElevation();
		}
		int max = (int) GPSUtils.findMax(elevations);
		return max;

	}

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		MAXBARHEIGHT = maxElevation(gpspoints);// assume no height above 500 meters

	}

	public static void main(String[] args) {
		launch(args);

	}

	public void run() {

		int N = gpspoints.length; // number of data points
		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);
		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT);
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start

		// TODO - START
		makeWindow("showHightProfile", gpspoints.length,MAXBARHEIGHT);
		for (int i = 0; i < gpspoints.length-1; i++) {
			int elevation=(int) (gpspoints[i+1].getElevation()-gpspoints[i].getElevation());
			setColor(255,30,0);
			drawLine(MARGIN+i,ybase+MARGIN,i+MARGIN,elevation+MARGIN);
		}
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}
}
