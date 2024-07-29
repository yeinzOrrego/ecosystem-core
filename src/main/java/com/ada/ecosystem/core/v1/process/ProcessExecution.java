package com.ada.ecosystem.core.v1.process;

import com.ada.ecosystem.core.v1.exceptions.EcosystemException;

public interface ProcessExecution<T, R> {	
	public ProcessResult<R> generate(ProcessParameters<T> inputParam) throws EcosystemException;
	public ProcessResult<R> validate(ProcessParameters<T> inputParam) throws EcosystemException;
}