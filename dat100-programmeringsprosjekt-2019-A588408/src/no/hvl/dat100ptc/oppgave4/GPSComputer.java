package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {
		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();
	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {
		double distance = 0;
		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {
		double elevation = 0;
		// TODO - START
		for (int i = 1; i < gpspoints.length; i++) {
			if (gpspoints[i].getElevation() > gpspoints[i - 1].getElevation()) {
				elevation += (gpspoints[i].getElevation() - gpspoints[i - 1].getElevation());
			}
		}
		return elevation;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int totalT = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		// throw new UnsupportedOperationException(TODO.method());
		return totalT;
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {
		// TODO - START // OPPGAVE - START
		double[] gjHastigheter = new double[gpspoints.length - 1];
		for (int i = 0; i < gpspoints.length - 1; i++) {
			gjHastigheter[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
		return gjHastigheter;
	}

	public double maxSpeed() {
		double[] speeds = speeds();
		double maxspeed = 0;
		// TODO - START
		for (int i = 0; i < speeds.length; i++) {
			if (speeds[i] > maxspeed) {
				maxspeed = speeds[i];
			}
		}
		return maxspeed;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public double averageSpeed() {
		double[] gjHastigheter = speeds();
		double average = 0;
		double sum = 0;
		// TODO - START
		for (int i = 0; i < gjHastigheter.length; i++) {
			sum += gjHastigheter[i];
		}
		average = sum / gjHastigheter.length;
		return average;
		// thow new UnsupportedOperationException(TODO.method());
		// TDO - SLUTT
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */
	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {
		double kcal;
		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;
		// TODO - START
		// Her skal vi inf�re noen mattematiske operasjoner, slik at vi lager en switch:
		int speedScale = ((int) speedmph) / 2;
		switch (speedScale) {
		case 5:
			met = 6.0;
			break;
		case 6:
			met = 8.0;
			break;
		case 7:
			met = 10.0;
			break;
		case 8:
			met = 12.0;
			break;
		case 9:
			met = 12.0;
			break;
		default:
			if (speedScale > 9) {
				met = 16.0;
			} else if (speedScale != 0) {
				met = 4.0;
			}
		}
		kcal = met * weight * secs / 3600.0;
		return kcal;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public double totalKcal(double weight) {
		double totalkcal = 0;
		// TODO - START
		totalkcal = kcal(weight, totalTime(), averageSpeed());
		return totalkcal;
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		System.out.println("==============================================");
		// TODO - START
		System.out.println("Total Time" + "\t" + ":" + "\t" + totalTime());
		System.out.println("Total distance" + "\t" + ":" + "\t" + totalDistance());
		System.out.println("Total elevation" + "\t" + ":" + "\t" + totalElevation());
		System.out.println("Max speed" + "\t" + ":" + "\t" + maxSpeed());
		System.out.println("Average speed" + "\t" + ":" + "\t" + averageSpeed());
		System.out.println("Energy" + "\t" + ":" + "\t" + totalKcal(WEIGHT));
		// throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
	}

	public  double[] climbs() {
		double[] climbs =new double[gpspoints.length-1];
		for(int i=0;i<gpspoints.length;i++) {
			double elev=0;
			if(gpspoints[i+1].getElevation()>gpspoints[i].getElevation()) {
				elev=gpspoints[i+1].getElevation()-gpspoints[i].getElevation();
			}
			climbs[i]=(elev*100)/totalElevation();
		}
		return climbs;
	}

	public void maxClimb() {
		
		double maxClimb=GPSUtils.findMax(climbs());
		
	}
	
}
