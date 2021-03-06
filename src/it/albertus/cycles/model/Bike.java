package it.albertus.cycles.model;

import static it.albertus.util.ByteUtils.toInt;
import static it.albertus.util.ByteUtils.toIntArray;
import static it.albertus.util.ByteUtils.toShortArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bike extends BikesInfElement {
	
	public enum Type {
		CLASS_125( 125 ),
		CLASS_250( 250 ),
		CLASS_500( 500 );
		
		private int displacement;
		
		private Type( int displacement ) {
			this.displacement = displacement;
		}
		
		public int getDisplacement() {
			return displacement;
		}
	}
	
	public static final int LENGTH = Settings.LENGTH + Gearbox.LENGTH + Torque.LENGTH;
	
	private final Settings settings; // 0-21
	private final Gearbox gearbox;   // 22-41
	private final Torque torque;     // 42-147
	private final Type type;
	
	public Bike( final Type type, final byte[] inf ) {
		this( type, new Settings( toInt( inf[0], inf[1] ), toInt( inf[2], inf[3] ), toInt( inf[4], inf[5] ), toInt( inf[6], inf[7] ), toInt( inf[8], inf[9] ), toInt( inf[10], inf[11] ), toInt( inf[12], inf[13] ), toInt( inf[14], inf[15] ), toInt( inf[16], inf[17] ), toInt( inf[18], inf[19] ), toInt( inf[20], inf[21] ) ), new Gearbox( toIntArray( Arrays.copyOfRange( inf, 22, 42 ) ) ), new Torque( toShortArray( Arrays.copyOfRange( inf, 42, 148 ) ) ) );
	}
	
	public Bike( final Type type, final Settings settings, final Gearbox gearbox, final Torque torque ) {
		this.settings = settings;
		this.gearbox = gearbox;
		this.torque = torque;
		this.type = type;
	}
	
	@Override
	public List<Byte> toByteList() {
		List<Byte> byteList = new ArrayList<Byte>( LENGTH );
		byteList.addAll( settings.toByteList() );
		byteList.addAll( gearbox.toByteList() );
		byteList.addAll( torque.toByteList() );
		return byteList;
	}
	
	public Settings getSettings() {
		return settings;
	}
	public Gearbox getGearbox() {
		return gearbox;
	}
	public Torque getTorque() {
		return torque;
	}
	public Type getType() {
		return type;
	}
	
}