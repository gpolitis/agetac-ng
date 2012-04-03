package org.agetac.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.entity.IEntity;

public interface IInterventionEngine {
	
	/**
	 * Ajoute une entitee a l'intervention
	 * et envoi la modif au server
	 * @param entity
	 */
	public void addEntity(IEntity entity);
	
	/**
	 * Supprime une entitee de l'intervention
	 * et envoi la modif au server
	 * @param entity
	 */
	public void removeEntity(IEntity entity);
	
	/**
	 * Met a jour une entitee et envoi la modif au server
	 * @param entity
	 */
	public void editEntity(IEntity entity);
	
	/**
	 * Effectue une mise a jour complete des donnees de l'intervention
	 * en allant chercher toutes les informations sur le serveur
	 */
	public void updateIntervention();
	
	/**
	 * Ajoute un message a l'intervention
	 * et envoi la modif au server
	 * @param m
	 * @return 
	 */
	public boolean sendMessage(MessageDTO m); 
	
	/**
	 * L'intervention actuelle
	 * @return intervention l'intervention en cours
	 */
	public InterventionDTO getIntervention();
	
	/**
	 * Ajoute un observeur
	 * @param o
	 */
	public void addObserver(Observer o);

	/**
	 * La liste des entitees de l'intervention
	 * @return entities liste des entitees
	 */
	public ArrayList<IEntity> getEntities();

	/**
	 * Demande au thread de MAJ de l'intervention
	 * de s'arreter des que possible
	 */
	
	public List<MessageDTO> getListMessages();
	
	public void stopUpdates();
	
	/**
	 * L'id de l'intervention actuelle
	 * ou -1 si aucune intervention n'a ete recuperee
	 * @return id de l'intervention courante
	 */
	public int getInterventionId();
}
