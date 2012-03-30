package org.agetac.server.entities;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class VehicleEntity {

	public enum VehiculeState {
	    DISPO_CASERNE, ALERTE, PARTIS, SUR_LES_LIEUX, TRANSPORT_HOPITAL, DISPO_RADIO, TEMPS_DEPASSE, DEMOBILISE
	}
	
	public enum VehiculeType {
		BEA, BRS, BLS, EMB, BLSP, CAEM, CCFM, CCGC, CCGCLC, DA,
		EPS, ESPM, FMOGP, FPT, MPR, PCM, PEVSD, SAC_PS, UTP, VPRO,
		VRCB, VICB, VAR, VL, VLCC, VLDP, VLCGD, VLCS, VLCG, VLHR,
		VLSV, VLOS, VLS, VNRBC, VPL, VPHV, VRAD, VSAV, VSM, VSR,
		VTP, VTU, VCYNO
	}
	
	private String name;
	private VehiculeState state;
	private VehiculeType type;
	private PositionEntity position;
	private BarrackEntity barrack;
	
	public VehicleEntity() {}
	
	public VehicleEntity(String n, VehiculeState s, VehiculeType t, PositionEntity p, BarrackEntity b) {
		this.name = n;
		this.state = s;
		this.type = t;
		this.position = p;
		this.barrack = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehiculeState getState() {
		return state;
	}

	public void setState(VehiculeState state) {
		this.state = state;
	}

	public VehiculeType getType() {
		return type;
	}

	public void setType(VehiculeType type) {
		this.type = type;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public BarrackEntity getBarrack() {
		return barrack;
	}

	public void setBarrack(BarrackEntity barrack) {
		this.barrack = barrack;
	}
	
}
