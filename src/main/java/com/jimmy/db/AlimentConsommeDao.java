package com.jimmy.db;

import java.time.LocalDate;
import java.util.List;

import com.jimmy.classes.AlimentConsomme;
import com.jimmy.exceptions.AlimentConsommeDaoException;

public interface AlimentConsommeDao {

	public void createTable() throws AlimentConsommeDaoException;

	public void deleteTable() throws AlimentConsommeDaoException;

	public List<AlimentConsomme> getAll() throws AlimentConsommeDaoException;

	public AlimentConsomme getById(int id) throws AlimentConsommeDaoException;

	public List<AlimentConsomme> getByIdUtilisateurAndBetweenDateFromAndDateTo(int idUtilisateur, LocalDate dateFrom,
			LocalDate dateTo) throws AlimentConsommeDaoException;

	public int create(AlimentConsomme alimentConsomme) throws AlimentConsommeDaoException;

	public void delete(int id) throws AlimentConsommeDaoException;
}
