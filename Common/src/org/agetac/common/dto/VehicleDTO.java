package org.agetac.common.dto;

public class VehicleDTO {

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
	private PositionDTO position;
	private BarrackDTO barrack;
	
	public VehicleDTO() {}
	
	public VehicleDTO(String n, VehiculeState s, VehiculeType t, PositionDTO p, BarrackDTO b) {
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

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public BarrackDTO getBarrack() {
		return barrack;
	}

	public void setBarrack(BarrackDTO barrack) {
		this.barrack = barrack;
	}
	
}
