package no.hvl.dat100ptc.oppgave5;
import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
public class test extends EasyGraphics {


	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;
	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public test() {
		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		gpspoints = gpscomputer.getGPSPoints();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);
		//showRouteMap(MARGIN + MAPYSIZE);
	//	playRoute(MARGIN + MAPYSIZE);
		//showStatistics();
		
		for(int i =0;i<800;i++) {
			fillCircle(i,20,3);
		}
	}
}

	