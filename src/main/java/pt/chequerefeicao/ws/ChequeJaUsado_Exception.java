
package pt.chequerefeicao.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ChequeJaUsado", targetNamespace = "urn:pt:chequerefeicao:ws")
public class ChequeJaUsado_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ChequeJaUsado faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ChequeJaUsado_Exception(String message, ChequeJaUsado faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ChequeJaUsado_Exception(String message, ChequeJaUsado faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: pt.chequerefeicao.ws.ChequeJaUsado
     */
    public ChequeJaUsado getFaultInfo() {
        return faultInfo;
    }

}
