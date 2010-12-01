/*
 * Generated by JasperReports - 2/26/09 3:46 PM
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;


/**
 *
 */
public class RepEstCuentaOrdxAsoc_1235684812112_681922 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_FECHA = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_ASC_ID = null;
    private JRFillParameter parameter_FECHA2 = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillField field_OCO_CODIGO = null;
    private JRFillField field_OCO_EMISION = null;
    private JRFillField field_ASC_CODIGO = null;
    private JRFillField field_OCO_PAGADO = null;
    private JRFillField field_OCO_ESTADO = null;
    private JRFillField field_NOMBRE_COMPLETO = null;
    private JRFillField field_OCO_MONTO = null;
    private JRFillField field_PRO_CODIGO = null;
    private JRFillField field_OCO_SALDO = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_SUM_OCO_PAGADO_1 = null;
    private JRFillVariable variable_SUM_OCO_MONTO_1 = null;
    private JRFillVariable variable_SUM_OCO_SALDO_1 = null;
    private JRFillVariable variable_SUM_OCO_MONTO_2 = null;
    private JRFillVariable variable_SUM_OCO_PAGADO_2 = null;
    private JRFillVariable variable_SUM_OCO_SALDO_2 = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_FECHA = (JRFillParameter)pm.get("FECHA");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_ASC_ID = (JRFillParameter)pm.get("ASC_ID");
        parameter_FECHA2 = (JRFillParameter)pm.get("FECHA2");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_OCO_CODIGO = (JRFillField)fm.get("OCO_CODIGO");
        field_OCO_EMISION = (JRFillField)fm.get("OCO_EMISION");
        field_ASC_CODIGO = (JRFillField)fm.get("ASC_CODIGO");
        field_OCO_PAGADO = (JRFillField)fm.get("OCO_PAGADO");
        field_OCO_ESTADO = (JRFillField)fm.get("OCO_ESTADO");
        field_NOMBRE_COMPLETO = (JRFillField)fm.get("NOMBRE_COMPLETO");
        field_OCO_MONTO = (JRFillField)fm.get("OCO_MONTO");
        field_PRO_CODIGO = (JRFillField)fm.get("PRO_CODIGO");
        field_OCO_SALDO = (JRFillField)fm.get("OCO_SALDO");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
        variable_SUM_OCO_PAGADO_1 = (JRFillVariable)vm.get("SUM_OCO_PAGADO_1");
        variable_SUM_OCO_MONTO_1 = (JRFillVariable)vm.get("SUM_OCO_MONTO_1");
        variable_SUM_OCO_SALDO_1 = (JRFillVariable)vm.get("SUM_OCO_SALDO_1");
        variable_SUM_OCO_MONTO_2 = (JRFillVariable)vm.get("SUM_OCO_MONTO_2");
        variable_SUM_OCO_PAGADO_2 = (JRFillVariable)vm.get("SUM_OCO_PAGADO_2");
        variable_SUM_OCO_SALDO_2 = (JRFillVariable)vm.get("SUM_OCO_SALDO_2");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(new String());//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA2.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_ASC_CODIGO.getValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_NOMBRE_COMPLETO.getValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_OCO_CODIGO.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_PRO_CODIGO.getValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_ESTADO.getValue()));//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_EMISION.getValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(((java.lang.Double)variable_SUM_OCO_MONTO_2.getValue()));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_PAGADO_2.getValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_SALDO_2.getValue()));//$JR_EXPR_ID=32$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(new String());//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getOldValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getOldValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getOldValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getOldValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getOldValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getOldValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA2.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_ASC_CODIGO.getOldValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_NOMBRE_COMPLETO.getOldValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_OCO_CODIGO.getOldValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_PRO_CODIGO.getOldValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getOldValue()));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getOldValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getOldValue()));//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_ESTADO.getOldValue()));//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_EMISION.getOldValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(((java.lang.Double)variable_SUM_OCO_MONTO_2.getOldValue()));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_PAGADO_2.getOldValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_SALDO_2.getOldValue()));//$JR_EXPR_ID=32$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(new String());//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_FECHA2.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_ASC_CODIGO.getValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_NOMBRE_COMPLETO.getValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_OCO_CODIGO.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_PRO_CODIGO.getValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_OCO_MONTO.getValue()));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_PAGADO.getValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)field_OCO_SALDO.getValue()));//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_ESTADO.getValue()));//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.String)field_OCO_EMISION.getValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(((java.lang.Double)variable_SUM_OCO_MONTO_2.getEstimatedValue()));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_PAGADO_2.getEstimatedValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.math.BigDecimal)(((java.math.BigDecimal)variable_SUM_OCO_SALDO_2.getEstimatedValue()));//$JR_EXPR_ID=32$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
