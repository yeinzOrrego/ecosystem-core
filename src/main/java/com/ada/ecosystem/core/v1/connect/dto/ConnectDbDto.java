/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ada.ecosystem.core.v1.connect.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>Contiene la configuración de conexión a un origen de datos.</p>
 *
 * @author carlos.torres
 * @version 1.0.0
 */
@Schema(name = "ConnectDbDto", description = "Contiene la configuración de conexión a un origen de datos.")
@Data
public class ConnectDbDto implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** Identificador del registro. */
    @Schema(description = "Identificador del registro.", example="100")
    private Long connectDbId;
    
    /** Código de conexión en el formato [Cliente]-[Contexto]. */
    @Schema(description = "Código de conexión en el formato [Cliente]-[Contexto].", example = "ada-prod")
    private String connectCode;
    
    /** Url de conexión. */
    @Schema(description = "Url de conexión.", example = "jdbc:oracle:thin:@//ip:port/dbname")
    private String connectUrl;
    
    /** Usuario de conexión. */
    @Schema(description = "Usuario de conexión.", example = "presup01")
    private String connectUsername;
    
    /** Contraseña de conexión. */
    @Schema(description = "Contraseña de conexión.", example = "presup01")
    private String connectPassword;
    
    /** Driver de conexión. */
    @Schema(description = "Driver de conexión.", example = "oracle.jdbc.driver.OracleDriver")
    private String connectDriver;
    
    /** Configuración de plataforma del driver de conexión. */
    @Schema(description = "Configuración de plataforma del driver de conexión.", example = "org.hibernate.dialect.Oracle8iDialect")
    private String connectPlatform;
    
    /** Ip de conexión. */
    @Schema(description = "Ip de conexión.", example = "127.0.0.1")
    private String connectIp;
    
    /** Puerto de conexión. */
    @Schema(description = "Puerto de conexión.", example = "1521")
    private String connectPort;
    
    /** Nombre del servicio de conexión. */
    @Schema(description = "Nombre del servicio de conexión.", example = "sicof")
    private String connectService;
    
    /** Fecha de creación del registro. */
    @Schema(description = "Fecha de creación del registro.", example = "2023-11-09T15:24:56.942Z")
    private Date dateCreated;
    
    /** Código interno del usuario de creación. */
    @Schema(description = "Código interno del usuario de creación.", example = "5678")
    private BigInteger userCreated;
    
    /** Estado del registro. */
    @Schema(description = "Estado del registro.", example = "A")
    private String status;        
}