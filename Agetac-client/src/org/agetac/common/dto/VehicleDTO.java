package org.agetac.common.dto;


public class VehicleDTO implements IModel {

	public enum VehicleState {
	    DISPO_CASERNE, ALERTE, PARTIS, SUR_LES_LIEUX, TRANSPORT_HOPITAL, DISPO_RADIO, TEMPS_DEPASSE, DEMOBILISE
	}
	
	public enum VehicleType {
		BEA, BRS, BLS, EMB, BLSP, CAEM, CCFM, CCGC, CCGCLC, DA,
		EPS, ESPM, FMOGP, FPT, MPR, PCM, PEVSD, SAC_PS, UTP, VPRO,
		VRCB, VICB, VAR, VL, VLCC, VLDP, VLCGD, VLCS, VLCG, VLHR,
		VLSV, VLOS, VLS, VNRBC, VPL, VPHV, VRAD, VSAV, VSM, VSR,
		VTP, VTU, VCYNO
	}
	
	private long id;
	private String name;
	private VehicleState state;
	private VehicleType type;
	private PositionDTO position;
	private BarrackDTO barrack;
	private GroupDTO group;
	
	public VehicleDTO() {}
	
	public VehicleDTO(String n, VehicleState s, VehicleType t, PositionDTO p, BarrackDTO b) {
		this.name = n;
		this.state = s;
		this.type = t;
		this.position = p;
		this.barrack = b;
	}
	
	public VehicleDTO(PositionDTO p, VehicleType t, String barrack, VehicleState s, GroupDTO g, String string) {
		this.position = p;
		this.type = t;
		this.state = s;
		this.group = g;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleState getState() {
		return state;
	}

	public void setState(VehicleState state) {
		this.state = state;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
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
	
	public String getBarrackName() {
		if (barrack != null) {
			return barrack.getName();
		}
		return null;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}
	
}
