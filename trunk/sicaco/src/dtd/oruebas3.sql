SELECT
     asc1.`ASC_CODIGO` AS ASC_CODIGO,
     cta_etr_empresa_trabajo.`ETR_NOMBRE` AS EMPRESA,
     CONCAT( per.`PER_PRIMER_APELLIDO`,', ',
             per.`PER_PRIMER_NOMBRE`) AS NOMBRE,
    (IF(asc1.asc_pago_ingreso = 'S', 0.0, 
    	(SELECT par.par_valor_number
    	FROM ctr_par_parametros par
    	where par.par_nombre = 'VALOR_INSCRIPCION')
    )) AS VALOR_INSCRIPCION,
    (SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
         `cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas
         ON asoc.`ASC_ID` = cas.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND
        IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) AS TOTAL_APORTACION,
    (SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND   cah.`cah_id` LIKE 'B%' AND
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))  AS TOTAL_AHORRO,
    IF(  
		(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
	     FROM
	       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
	       ON asoc.`ASC_ID` = cas.`ASC_ID`
	       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
	    WHERE
	        cas.`EST_ID`=13 AND
		pre.`pre_saldo_actual_t` > 0 AND  
		IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
			(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
			IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
			    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,0.0, 
		(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
	     FROM
	       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
	       ON asoc.`ASC_ID` = cas.`ASC_ID`
	       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
	    WHERE
	        cas.`EST_ID`=13 AND
		pre.`pre_saldo_actual_t` > 0 AND  
		IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
			(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
			IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
			    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))
    )AS TOTAL_PRESTAMO,
    
    if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,
0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))  AS TOTAL_SEGURO
FROM
     `sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1
     ON per.`PER_ID` = asc1.`PER_ID`
     INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo  
     ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID`
     INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo 
	ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID`
WHERE
     asc1.asc_codigo = '000149'
001315
002911
003351
003918
004423
004535

--****************************************************

SELECT (sum(cah.cah_cuota) + sum(pre.pre_cuota) )
from cta_cah_cuenta_ahorro cah, cta_pre_prestamo pre


--****************

SELECT
	(IF(asc1.asc_pago_ingreso = 'S', 0.0, 
    	(SELECT par.par_valor_number
    	FROM ctr_par_parametros par
    	where par.par_nombre = 'VALOR_INSCRIPCION')
    )) +
    (SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
         `cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas
         ON asoc.`ASC_ID` = cas.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND
        IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) +
    (SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND   cah.`cah_id` LIKE 'B%' AND
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))
+
IF((SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
	     FROM
	       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
	       ON asoc.`ASC_ID` = cas.`ASC_ID`
	       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
	    WHERE
	        cas.`EST_ID`=13 AND
		pre.`pre_saldo_actual_t` > 0 AND  
		IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
			(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
			IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
			    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,0.0, 
		(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
	     FROM
	       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
	       ON asoc.`ASC_ID` = cas.`ASC_ID`
	       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
	    WHERE
	        cas.`EST_ID`=13 AND
		pre.`pre_saldo_actual_t` > 0 AND  
		IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
			(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
			IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
			    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))
+
if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,
0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))    

FROM
     `sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1
     ON per.`PER_ID` = asc1.`PER_ID`
     INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo  
     ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID`
     INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo 
	ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID`
WHERE
     asc1.`ASC_CODIGO`= '8601497' 


--OTROS
(SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND
	pre.`pre_saldo_actual_t` > 0 AND  
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))  AS TOTAL_PRESTAMO,
    if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,
0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))  AS TOTAL_SEGURO

--Anterior

SELECT
     asc1.`ASC_CODIGO` AS ASC_CODIGO,
     cta_etr_empresa_trabajo.`ETR_NOMBRE` AS EMPRESA,
     CONCAT( per.`PER_PRIMER_APELLIDO`,', ',
             per.`PER_PRIMER_NOMBRE`) AS NOMBRE,
    (SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
         `cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas
         ON asoc.`ASC_ID` = cas.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND
        IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) AS TOTAL_APORTACION,
    (SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND   cah.`cah_id` LIKE 'B%' AND
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))  AS TOTAL_AHORRO,
    (SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND
        pre.`pre_saldo_actual_t` > 0 AND  
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S'))))  AS TOTAL_PRESTAMO,
    if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))) is null,
0.0, (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   
	IF(asc1.`ASC_PAGARA_PADRE`<> 'Y',asoc.`ASC_ID`=asc1.`ASC_ID`,
		(asoc.`ASC_ID`=asc1.`ASC_ID` OR asoc.`ASC_ID`
		IN (SELECT a.`ASC_ID` FROM cta_asc_asociado a
		    WHERE a.`ASC_ASOCIADO_PADRE`=asc1.`ASC_ID` AND a.`ASC_PAGARA_PADRE`='S')))))  AS TOTAL_SEGURO
FROM
     `sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1
     ON per.`PER_ID` = asc1.`PER_ID`
     INNER JOIN `cta_dpt_departamento_trabajo` cta_dpt_departamento_trabajo  
     ON cta_dpt_departamento_trabajo.`DPT_ID` = asc1.`DPT_ID`
     INNER JOIN `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo 
	ON cta_dpt_departamento_trabajo.`ETR_ID` = cta_etr_empresa_trabajo.`ETR_ID`
WHERE
     asc1.`EST_ID_2`=0 AND asc1.`EST_ID`=0 AND asc1.`ASC_ASOCIADO_PADRE` IS NULL 
    AND if(-1=-1,1=1,cta_etr_empresa_trabajo.`ETR_ID`=-1)
ORDER BY EMPRESA



SELECT distinct cta_cas_cuenta_asociado.`ASC_ID` AS ASC_ID 
FROM 
 `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro INNER JOIN  
 `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado 
 ON cta_cah_cuenta_ahorro.`cah_id` = cta_cas_cuenta_asociado.`cah_id`
 INNER JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro 
 ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID`

where cta_tah_tipo_ahorro.`TAH_ID`=3  and cta_cas_cuenta_asociado.`cas_fecha_cierre`=  adddate(curdate(), INTERVAL 7 DAY)



update cta_cas_cuenta_asociado 
set    cas_fecha_cierre=(select t.tah_fecha_fin from cta_tah_tipo_ahorro t where t.tah_id=3)
where
	asc_id in (SELECT distinct cas.`ASC_ID` AS ASC_ID 
	FROM 
 		`cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro INNER JOIN  
 		`cta_cas_cuenta_asociado` cas 
 		ON cta_cah_cuenta_ahorro.`cah_id` = cas.`cah_id`
 		INNER JOIN `cta_tah_tipo_ahorro` cta_tah_tipo_ahorro 
 		ON cta_cah_cuenta_ahorro.`tah_id` = cta_tah_tipo_ahorro.`TAH_ID`
	where 	cta_tah_tipo_ahorro.`TAH_ID`=3)
	
	