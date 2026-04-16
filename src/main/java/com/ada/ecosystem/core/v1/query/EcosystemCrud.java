
package com.ada.ecosystem.core.v1.query;

import java.util.List;

import com.ada.ecosystem.core.v1.exceptions.EcosystemException;



/**
 * The Interface EcosystemCrud.
 *
 * @param <D> Dto generic type
 * @param <T> id generic type
 */
public interface EcosystemCrud<D, T> {

	/**
	 * Read.
	 *
	 * @return the list
	 */
	public List<D> read();

	/**
	 * Read only one.
	 *
	 * @param id the id
	 * @return the d
	 * @throws Exception the exception
	 */
	public D readOnlyOne(T id) throws EcosystemException;

	/**
	 * Creates the.
	 *
	 * @param register the register
	 * @return the d
	 * @throws Exception the exception
	 */
	public D create(D register) throws EcosystemException;

	/**
	 * Update.
	 *
	 * @param id       the id
	 * @param register the register
	 * @return the d
	 * @throws Exception the exception
	 */
	public D update(T id, D register) throws EcosystemException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the d
	 * @throws Exception the exception
	 */
	public D delete(T id) throws EcosystemException;

}
