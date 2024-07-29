package com.ada.ecosystem.core.v1.process.documentgenerator;

import java.util.List;

import com.ada.ecosystem.core.v1.process.ProcessResult;

/**
 * <p>Define el flujo de procesos que se deben implementar en los procesos
 * de generación de documentos <b>(Supertabla)</><p>
 * 
 * <p>Los procesos actuales de documentos utilizan el siguiente flujo estandar:
 * 
 * <ul>
 *   <li>Cargar Tabla Temporal</li>
 *   <li>Generar el documento</li>
 *   <li>Aprobar el documento*</li>
 *   <li>Anular el documento*</li>
 * </ul>
 * <span>* El proceso dependerá de la configuración del concepto y del tipo de documento.</span>
 * </p>
 *
 * @param <T> the generic type
 * 
 * @author carlos.torres
 * @version 1.0.0
 */
public interface DocumentDefinitionFlow<T> {	
	
	/**
	 * Fill temporary table.
	 * 
	 * <p>Este método debe ser implementado para el proceso de carga de la tabla temporal del documento
	 * que se creará.</p>
	 *
	 * @param list the list
	 * @return the process result
	 * @author carlos.torres
	 * @version 1.0.0
	 */
	public ProcessResult<Long> fillTemporaryTable(List<T> list, InputParameters inputParameters);
	
	/**
	 * Generate document.
	 * 
	 * <p>Este método debe ser implementado para el proceso de creación del documento.</p>
	 *
	 * @param inputParameters the input parameters
	 * @return the process result
	 * @author carlos.torres
	 * @version 1.0.0
	 */
	public ProcessResult<Long> generateDocument(InputParameters inputParameters);
	
	/**
	 * Approve document.
	 * 
	 * <p>(Si aplica) Este método debe ser implementado para el proceso de aprobación del documento.</p>
	 *
	 * @param inputParameters the input parameters
	 * @return the process result
	 * @author carlos.torres
	 * @version 1.0.0
	 */
	public ProcessResult<Long> approveDocument(InputParameters inputParameters);
	
	/**
	 * Cancel document.
	 * 
	 * <p>(Si aplica) Este método debe ser implementado para el proceso de anulación del documento.</p>
	 *
	 * @param inputParameters the input parameters
	 * @return the process result
	 * @author carlos.torres
	 * @version 1.0.0
	 */
	public ProcessResult<Long> cancelDocument(InputParameters inputParameters);
}