package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktur
		this.time=time;
		this.latitude=latitude;
		this.longitude=longitude;
		this.elevation=elevation;

		//throw new UnsupportedOperationException(TODO.construtor("GPSPoint"));

	}

	// TODO - get/set metoder
	public int getTime() {
		return time;
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setTime(int time) {
		this.time=time;
				
		//throw new UnsupportedOperationException(TODO.method());

	}

	public double getLatitude() {
		return latitude;
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setLatitude(double latitude) {
		this.latitude=latitude;
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getLongitude() {
		return longitude;
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setLongitude(double longitude) {
		this.longitude=longitude;
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getElevation() {
		return elevation;
	//	throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setElevation(double elevation) {
		this.elevation=elevation;
		//throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public String toString() {
		
		String str;
		
		// TODO - start
		//1 (2.0,3.0) 5.0\n
		return time+" ("+latitude+","+longitude+") "+elevation+"\n";

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - slutt
		
	}
}
