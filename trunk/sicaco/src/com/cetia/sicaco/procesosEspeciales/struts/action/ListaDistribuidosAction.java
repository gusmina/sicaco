/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.cetia.sicaco.procesosEspeciales.struts.action;

import java.util.HashMap;
import com.mad.utilidades.Format;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Transaction;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;

import com.cetia.sicaco.hibernate.CtaAscAsociado;
import com.cetia.sicaco.hibernate.CtaAscAsociadoDAO;
import com.cetia.sicaco.hibernate.CtrEstEstadoDAO;
import com.cetia.sicaco.hibernate.SecPerPersona;
import com.cetia.sicaco.procesosEspeciales.struts.form.ListaDistribuidosForm;
import com.cetia.sicaco.struts.Constantes;
import com.cetia.sicaco.struts.DMLAction;

/** 
 * MyEclipse Struts
 * Creation date: 01-20-2009
 * 
 * XDoclet definition:
 * @struts.action path="/listaDistribuidos" name="liquidacionAsociadoForm" parameter="accion" scope="request"
 * @struts.action-forward name="lista" path="pagina-lista.procesosEspeciales.listaDistribuidos"
 */
public class ListaDistribuidosAction extends DMLAction {
	
	public String TABLA_ID = "ctaCasCuentaAsociado";
	public int TAM_LISTA_ACT = 0;
	
	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CtaAscAsociadoDAO asociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		TableFacade tableFacade = TableFacadeFactory.createTableFacade(TABLA_ID, request);
		tableFacade.setExportTypes(response, ExportType.CSV, ExportType.JEXCEL);
		tableFacade.setStateAttr("restore");
		Limit limit = tableFacade.getLimit();
		int totalRows = asociadoDAO.getAsociadosSinDistribuirRows();
		tableFacade.setTotalRows(totalRows);
		int rowStart = limit.getRowSelect().getRowStart();
		int rowEnd = limit.getRowSelect().getRowEnd();
		List lst = asociadoDAO.findAllAsociadosSinDistribuir(rowStart, rowEnd);
		tableFacade.setItems(lst);
		if (limit.isExported()) {
			//---- exporta la tabla
		//	export(tableFacade);
			return null; 
		} else {
			//---- genera el html de la tabla para ser mostrada
			String html = html(tableFacade, request);
			request.setAttribute("_lista2", html);
			/*request.setAttribute("_lista2", html);*/
		}
		//----- Variables de configuracion
		request.setAttribute("edit", 0);
		request.setAttribute(Constantes.ACCION_KEY, "/listaDistribuidos");
		return mapping.findForward("lista");
	}
	
	public ActionForward actualizarAsociados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		CtaAscAsociadoDAO ascAsociadoDAO = new CtaAscAsociadoDAO(getSessionHibernate(request));
		ListaDistribuidosForm listaForm = (ListaDistribuidosForm) form;
		String args = "";
		if(listaForm.getElementos() != null){
			String[] ids = listaForm.getElementos();
			for(int i=0;i<ids.length;i++){
				args+= ("'"+ids[i]+"',");
			}
			args = args.substring(0, args.lastIndexOf(","));
			Transaction tx = ascAsociadoDAO.getSession().beginTransaction();
			try{
				if(ascAsociadoDAO.updateEstadoDistribucion(args)==1){
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.listaDistribuidos.exito"));
				}else{
					errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.listaDistribuidos.error"));
				}
				tx.commit();
			}catch (Exception e) {
				log.debug("La actualizacion de asociados con distribucion pendiente ha fallado",e);
			}finally{
				ascAsociadoDAO.getSession().flush();
				ascAsociadoDAO.getSession().clear();
				
			}
		}else{
			errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.listaDistribuidos.error"));
		}
		saveMessages(request, errors);
		return lista(mapping, form, request, response);
	}
	
	private String html(final TableFacade tableFacade, final HttpServletRequest request) {
		tableFacade.setColumnProperties("ascCodigoAsociado","secPerPersona.perPrimerNombre","ascDividendoAportaciones","ascDividendoPrestamo","ascSalario","estId","ascId");
		Table table = tableFacade.getTable();
        //---- Titulo de la tabla
		table.setCaptionKey("tbl.ascAsociado.caption");
		
		
		Row row = table.getRow();
		Column nombreColumna = row.getColumn("ascCodigoAsociado");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascCodigo");
		
		
		nombreColumna = row.getColumn("secPerPersona.perPrimerNombre");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascNombre");
		//----- Implementamos la edicion de asociado
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaAscAsociado asociado = (CtaAscAsociado)item;
				SecPerPersona persona = asociado.getSecPerPersona();
				value = persona.getPerPrimerApellido();
				value = value + (isObjectNull(persona.getPerSegundoApellido())?"":(" "+persona.getPerSegundoApellido()));
				value = value  + ", " +persona.getPerPrimerNombre();
				value = value  + (isObjectNull(persona.getPerSegundoNombre())?"":(" "+persona.getPerSegundoNombre()));		
				return value.toString();	
			}
		});
		
		nombreColumna = row.getColumn("ascDividendoAportaciones");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascDividendoAportaciones");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				return Format.formatDinero(value);
			}
		});
		
		nombreColumna = row.getColumn("ascDividendoPrestamo");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascDividendoPrestamo");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				return Format.formatDinero(value);
			}
		});
		
		nombreColumna = row.getColumn("ascSalario");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascTotalDividendos");
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){
			public Object getValue(Object item, String property, int rowcount) {
				CtaAscAsociado asociado = (CtaAscAsociado) item;
				return Format.formatDinero(asociado.getAscDividendoAportaciones()+asociado.getAscDividendoPrestamo());
			}
		});
		
		nombreColumna = row.getColumn("estId");
		nombreColumna.setTitleKey("tbl.ascAsociado.ascEstado");
		
		nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

			public Object getValue(Object item, String property, int rowcount) {
				Object value = new BasicCellEditor().getValue(item, property, rowcount);
				CtaAscAsociado asociado = (CtaAscAsociado)item;
				CtrEstEstadoDAO estadoDAO = new CtrEstEstadoDAO(getSessionHibernate(request));
				value = estadoDAO.findById(asociado.getEstId()).getEstNombre().toString();
				return value;
			}
		});

        nombreColumna = row.getColumn("ascId");
        nombreColumna.setTitleKey("tbl.ascAsociado.Acciones");
        nombreColumna.getCellRenderer().setCellEditor(new CellEditor(){

            public Object getValue(Object item, String property, int rowcount) {
                CtaAscAsociado asociado = (CtaAscAsociado) item;
                HtmlBuilder html = new HtmlBuilder();
               	html.input().type("checkbox").name("elementos").value(asociado.getAscId()).id(asociado.getAscId());
               	html.close();
               	return html.toString();
            }
           
        });
        
        return tableFacade.render();
    }
	
	protected Map<String, String> getKeyMethodMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cmd.listaDistribucion.lista", "lista");
		map.put("cmd.listaDistribucion.guardarCambios", "actualizarAsociados");
		return map;
	}
}