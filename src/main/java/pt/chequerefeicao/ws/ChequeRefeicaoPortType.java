
package pt.chequerefeicao.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ChequeRefeicaoPortType", targetNamespace = "urn:pt:chequerefeicao:ws")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ChequeRefeicaoPortType {


    /**
     * 
     * @param valor
     * @param endossavel
     * @param titular
     * @return
     *     returns java.lang.String
     * @throws UtilizadorInexistente_Exception
     * @throws ValorInvalido_Exception
     */
    @WebMethod
    @WebResult(name = "resultado", targetNamespace = "")
    @RequestWrapper(localName = "emitir", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.Emitir")
    @ResponseWrapper(localName = "emitirResponse", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.EmitirResponse")
    public String emitir(
        @WebParam(name = "titular", targetNamespace = "")
        String titular,
        @WebParam(name = "valor", targetNamespace = "")
        int valor,
        @WebParam(name = "endossavel", targetNamespace = "")
        boolean endossavel)
        throws UtilizadorInexistente_Exception, ValorInvalido_Exception
    ;

    /**
     * 
     * @param numeros
     * @param beneficiario
     * @return
     *     returns java.lang.Integer
     * @throws ChequeJaUsado_Exception
     * @throws UtilizadorInexistente_Exception
     * @throws ChequeInexistente_Exception
     */
    @WebMethod
    @WebResult(name = "valorTotal", targetNamespace = "")
    @RequestWrapper(localName = "sacar", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.Sacar")
    @ResponseWrapper(localName = "sacarResponse", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.SacarResponse")
    public Integer sacar(
        @WebParam(name = "beneficiario", targetNamespace = "")
        String beneficiario,
        @WebParam(name = "numeros", targetNamespace = "")
        List<String> numeros)
        throws ChequeInexistente_Exception, ChequeJaUsado_Exception, UtilizadorInexistente_Exception
    ;

    /**
     * 
     * @param terceiro
     * @param titular
     * @param numero
     * @return
     *     returns java.lang.String
     * @throws ChequeJaUsado_Exception
     * @throws UtilizadorInexistente_Exception
     * @throws ChequeInexistente_Exception
     * @throws ChequeNaoEndossavel_Exception
     */
    @WebMethod
    @WebResult(name = "resultado", targetNamespace = "")
    @RequestWrapper(localName = "endossar", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.Endossar")
    @ResponseWrapper(localName = "endossarResponse", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.EndossarResponse")
    public String endossar(
        @WebParam(name = "titular", targetNamespace = "")
        String titular,
        @WebParam(name = "terceiro", targetNamespace = "")
        String terceiro,
        @WebParam(name = "numero", targetNamespace = "")
        String numero)
        throws ChequeInexistente_Exception, ChequeJaUsado_Exception, ChequeNaoEndossavel_Exception, UtilizadorInexistente_Exception
    ;

    /**
     * 
     * @param sacados
     * @param titular
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws UtilizadorInexistente_Exception
     */
    @WebMethod
    @WebResult(name = "resultado", targetNamespace = "")
    @RequestWrapper(localName = "listar", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.Listar")
    @ResponseWrapper(localName = "listarResponse", targetNamespace = "urn:pt:chequerefeicao:ws", className = "pt.chequerefeicao.ws.ListarResponse")
    public List<String> listar(
        @WebParam(name = "titular", targetNamespace = "")
        String titular,
        @WebParam(name = "sacados", targetNamespace = "")
        boolean sacados)
        throws UtilizadorInexistente_Exception
    ;

}
