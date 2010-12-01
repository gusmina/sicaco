-- super consulta de descuentos


select a.asc_id, sum(a.total_aportacion) total_aportacion, sum(a.total_ahorro) total_ahorro, 
		sum(a.total_prestamo) total_prestamo, sum(a.total_seguro) 

from (

	select a.asc_id, a.total_aportacion, a.total_ahorro, a.total_prestamo, a.total_seguro
	
	from (
	
		select a.asc_id, c.cah_cuota total_aportacion, 0 total_ahorro, 0 total_prestamo, 0 total_seguro
		
		from (
		
			select asc_id, asc_codigo
			from cta_asc_asociado
			where est_id = 0
			and est_id_2 = 0
		
		) a
		
		join (
		
			select cas_cuenta, asc_id, cah_id, est_id
			from cta_cas_cuenta_asociado
			where cah_id like 'A%'
			and est_id = 9
		
		) b
		
		on a.asc_id = b.asc_id
		
		join (
		
			select cah_id, cah_cuota
			from cta_cah_cuenta_ahorro
		 
		) c
		
		on b.cah_id = c.cah_id
		
		
		union
		
		
		select a.asc_id, 0 total_aportacion, sum(a.cah_cuota) total_ahorro, 0 total_prestamo, 0 total_seguro
		
		from (
		
			select a.asc_id, a.asc_codigo, c.cah_id, c.cah_cuota
			
			from (
			
				select asc_id, asc_codigo
				from cta_asc_asociado
				where est_id = 0
				and est_id_2 = 0
			
			) a
			
			join (
			
				select cas_cuenta, asc_id, cah_id, est_id
				from cta_cas_cuenta_asociado
				where cah_id like 'B%'
				and est_id = 9
			
			) b
			
			on a.asc_id = b.asc_id
			
			join (
			
				select cah_id, cah_cuota
				from cta_cah_cuenta_ahorro
			 
			) c
			
			on b.cah_id = c.cah_id
			
			order by asc_id
		) a
		
		group by a.asc_id
		
		union
		
		select a.asc_id, 0 total_aportacion, 0 total_ahorro, sum(a.pre_cuota) total_prestamo, 0 total_seguro
		
		from (
		
			select a.asc_id, a.asc_codigo, c.pre_id, c.pre_cuota
			
			from (
			
				select asc_id, asc_codigo
				from cta_asc_asociado
				where est_id = 0
				and est_id_2 = 0
			
			) a
			
			join (
			
				select cas_cuenta, asc_id, pre_id, est_id
				from cta_cas_cuenta_asociado
				where pre_id is not NULL
				and est_id = 13
			
			) b
			
			on a.asc_id = b.asc_id
			
			join (
			
				select pre_id, pre_cuota
				from cta_pre_prestamo
			 
			) c
			
			on b.pre_id = c.pre_id
			
			order by asc_id
		) a
		
		group by a.asc_id
		
		union
		
		select a.asc_id, 0 total_aportacion, 0 total_ahorro, 0 total_prestamo, sum(a.seg_cuota) total_seguro
		
		from (
		
			select a.asc_id, a.asc_codigo, c.seg_id, c.seg_cuota
			
			from (
			
				select asc_id, asc_codigo
				from cta_asc_asociado
				where est_id = 0
				and est_id_2 = 0
			
			) a
			
			join (
			
				select cas_cuenta, asc_id, seg_id, est_id
				from cta_cas_cuenta_asociado
				where seg_id is not NULL
				and est_id = 11
			
			) b
			
			on a.asc_id = b.asc_id
			
			join (
			
				select seg_id, seg_cuota
				from cta_seg_seguros
			 
			) c
			
			on b.seg_id = c.seg_id
			
			order by asc_id
		) a
		
		group by a.asc_id
		
	) a
	
	order by a.asc_id
	
) a

group by a.asc_id;

-- Obtener apellidos y nombres

select a.asc_id, a.asc_codigo, b.per_id, concat(b.per_primer_apellido, ', ', b.per_primer_nombre) nombre_completo

from (

	select asc_id, asc_codigo, per_id
	from cta_asc_asociado
	where est_id = 0
	and est_id_2 = 0

) a

join (

	select per_id, per_primer_apellido, per_primer_nombre
	from sec_per_persona

) b

on a.per_id = b.per_id

order by asc_id
                                                                     
                                                                     
-- Query Maguie                                                                 
                                             
SELECT
     cta_asc_asociado1.`ASC_CODIGO` AS ASC_CODIGO,
     CONCAT( sec_per_persona.`PER_PRIMER_APELLIDO`,', ',
             sec_per_persona.`PER_PRIMER_NOMBRE`) AS NOMBRE,
    (SELECT
        SUM(IF(cta_cah_cuenta_ahorro.`cah_cuota` IS NULL,0,cta_cah_cuenta_ahorro.`cah_cuota`)) AS cuota

     FROM
         `cta_asc_asociado` cta_asc_asociado
         INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado
         ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro
         ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id`

    WHERE
        cta_cas_cuenta_asociado.`EST_ID`=9 AND
        cta_asc_asociado.`ASC_ID`=cta_asc_asociado1.`ASC_ID` AND
        cta_cah_cuenta_ahorro.`cah_id` LIKE 'A%') AS TOTAL_APORTACION,
    (SELECT
        SUM(IF(cta_cah_cuenta_ahorro.`cah_cuota` IS NULL,0,cta_cah_cuenta_ahorro.`cah_cuota`)) AS cuota

     FROM
       `cta_asc_asociado` cta_asc_asociado
       INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado
       ON cta_asc_asociado.`ASC_ID` = cta_cas_cuenta_asociado.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cta_cah_cuenta_ahorro
       ON cta_cas_cuenta_asociado.`cah_id` = cta_cah_cuenta_ahorro.`cah_id`

    WHERE
        cta_cas_cuenta_asociado.`EST_ID`=9 AND
        cta_asc_asociado.`ASC_ID`=cta_asc_asociado1.`ASC_ID` AND
        cta_cah_cuenta_ahorro.`cah_id` LIKE 'B%')  AS TOTAL_AHORRO,

    (SELECT
        SUM(IF(cta_pre_prestamo.`pre_cuota` IS NULL,0,cta_pre_prestamo.`pre_cuota`)) AS cuota
SELECT
     asc1.`ASC_CODIGO` AS ASC_CODIGO,
     CONCAT( per.`PER_PRIMER_APELLIDO`,', ',
             per.`PER_PRIMER_NOMBRE`) AS NOMBRE,
    (SELECT
        SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota

     FROM
         `cta_asc_asociado` asoc
         INNER JOIN `cta_cas_cuenta_asociado` cas
         ON asoc.`ASC_ID` = cas.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cah
         ON cas.`cah_id` = cah.`cah_id`

    WHERE
        cas.`EST_ID`=9 AND
        asoc.`ASC_ID`=asc1.`ASC_ID` AND
        cah.`cah_id` LIKE 'A%') AS TOTAL_APORTACION,
    (SELECT
        SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota

     FROM
       `cta_asc_asociado` asoc
       INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cah
       ON cas.`cah_id` = cah.`cah_id`

    WHERE
        cas.`EST_ID`=9 AND
        asoc.`ASC_ID`=asc1.`ASC_ID` AND
        cah.`cah_id` LIKE 'B%')  AS TOTAL_AHORRO,

    (SELECT
        SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota

     FROM
       `cta_asc_asociado` asoc
       INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_pre_prestamo` pre
       ON cas.`pre_id` = pre.`pre_id`

    WHERE
        cas.`EST_ID`=13 AND
        asoc.`ASC_ID`=asc1.`ASC_ID`)  AS TOTAL_PRESTAMO,

    (SELECT
        SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota

     FROM
       `cta_asc_asociado` asoc
       INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg
       ON cas.`seg_id` = seg.`seg_id`

    WHERE
        cas.`EST_ID`=11 AND
        asoc.`ASC_ID`=asc1.`ASC_ID`)  AS TOTAL_SEGURO

    /* cta_asc_asociado.`ASC_PAGARA_PADRE` AS cta_asc_asociado_ASC_PAGARA_PADRE*/
FROM
     `sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1
     ON per.`PER_ID` = asc1.`PER_ID`
WHERE
         asc1.`EST_ID_2`=0

--Ultima consulta de Maguie

SELECT
     asc1.asc_id as ASC_ID, asc1.`ASC_CODIGO` AS ASC_CODIGO,
     CONCAT( per.`PER_PRIMER_APELLIDO`,', ',
             per.`PER_PRIMER_NOMBRE`) AS NOMBRE,
    (SELECT  SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
         `cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas
         ON asoc.`ASC_ID` = cas.`ASC_ID`
         INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' AND
        asoc.`ASC_ID`=asc1.`ASC_ID`) AS TOTAL_APORTACION,
    (SELECT SUM(IF(cah.`cah_cuota` IS NULL,0,cah.`cah_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc    INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_cah_cuenta_ahorro` cah  ON cas.`cah_id` = cah.`cah_id`
    WHERE
        cas.`EST_ID`=9 AND   asoc.`ASC_ID`=asc1.`ASC_ID` AND
        cah.`cah_id` LIKE 'B%')  AS TOTAL_AHORRO,

    (SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc   INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND  asoc.`ASC_ID`=asc1.`ASC_ID`)  AS TOTAL_PRESTAMO,

    if((SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   asoc.`ASC_ID`=asc1.`ASC_ID`) IS NULL,0,
        (SELECT SUM(IF(seg.`seg_cuota` IS NULL,0,seg.`seg_cuota`)) AS cuota
     FROM
       `cta_asc_asociado` asoc  INNER JOIN `cta_cas_cuenta_asociado` cas
       ON asoc.`ASC_ID` = cas.`ASC_ID`
       INNER JOIN `cta_seg_seguros` seg  ON cas.`seg_id` = seg.`seg_id`
    WHERE
        cas.`EST_ID`=11 AND   asoc.`ASC_ID`=asc1.`ASC_ID`)) 
          AS TOTAL_SEGURO


FROM
     `sec_per_persona` per INNER JOIN `cta_asc_asociado` asc1
     ON per.`PER_ID` = asc1.`PER_ID`
WHERE
         asc1.`EST_ID_2`=0 AND asc1.`EST_ID`=0

   --Prueba      
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
    AND if(1=-1,1=1,cta_etr_empresa_trabajo.`ETR_ID`=1)
ORDER BY EMPRESA

LIMIT null,null


--Intento fiadores empleados
SELECT
     cta_pre_prestamo.`PRE_CUOTA` AS cta_pre_prestamo_PRE_CUOTA,
     cta_pxt_persona_externa.`PXT_ID` AS cta_pxt_persona_externa_PXT_ID,
     cta_pxt_persona_externa.`PXT_EMPRESA` AS EMPRESA
FROM
     `cta_pre_prestamo` cta_pre_prestamo INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado ON cta_pre_prestamo.`PRE_ID` = cta_cas_cuenta_asociado.`pre_id`
     INNER JOIN `cta_fxp_fiador_prestamo` cta_fxp_fiador_prestamo ON cta_pre_prestamo.`PRE_ID` = cta_fxp_fiador_prestamo.`pre_id`
     INNER JOIN `cta_mxp_movimiento_prestamo` cta_mxp_movimiento_prestamo ON cta_pre_prestamo.`PRE_ID` = cta_mxp_movimiento_prestamo.`PRE_ID`
     INNER JOIN `cta_pxt_persona_externa` cta_pxt_persona_externa ON cta_fxp_fiador_prestamo.`pxt_id` = cta_pxt_persona_externa.`PXT_ID`
     AND cta_pxt_persona_externa.`PXT_ID` = cta_cas_cuenta_asociado.`PXT_ID`,
     `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo
     
WHERE UCASE(TRIM(cta_etr_empresa_trabajo.`ETR_NOMBRE`)) = UCASE(TRIM(cta_pxt_persona_externa.`PXT_EMPRESA`))



--Prueba
SELECT DISTINCT
     cta_pxt_persona_externa.`PXT_EMPRESA` AS EMPRESA_NOMBRE,
     cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO` AS CODIGO_EMPLEADO,
     (SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`))
     FROM
       `cta_pxt_persona_externa` p   INNER JOIN `cta_cas_cuenta_asociado` cas
       ON p.`PXT_ID` = cas.`PXT_ID`
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND  
	p.`PXT_CODIGO_EMPLEADO`=cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO`) AS DESCUENTO
     
FROM
     `cta_pxt_persona_externa` cta_pxt_persona_externa 
     INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado 
     ON cta_pxt_persona_externa.`PXT_ID` = cta_cas_cuenta_asociado.`PXT_ID`
     
WHERE cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO` IS NOT NULL
	AND 
	IF(-1=-1,1=1,
	cta_pxt_persona_externa.`PXT_EMPRESA` =  
	(SELECT E.ETR_NOMBRE
	FROM CTA_ETR_EMPRESA_TRABAJO E
	WHERE E.ETR_ID = -1))
	
	IN
		(SELECT cta_etr_empresa_trabajo.`ETR_NOMBRE`
		 FROM `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo
		WHERE cta_etr_empresa_trabajo.`ETR_ID`=1)
		
SELECT DISTINCT
     cta_pxt_persona_externa.`PXT_EMPRESA` AS EMPRESA_NOMBRE,
     cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO` AS CODIGO_EMPLEADO,
     (SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`))
     FROM
       `cta_pxt_persona_externa` p   INNER JOIN `cta_cas_cuenta_asociado` cas
       ON p.`PXT_ID` = cas.`PXT_ID`
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND  
	p.`PXT_ID`=cta_pxt_persona_externa.`PXT_ID`) AS DESCUENTO
     
FROM
     `cta_pxt_persona_externa` cta_pxt_persona_externa INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado ON cta_pxt_persona_externa.`PXT_ID` = cta_cas_cuenta_asociado.`PXT_ID`
     
WHERE cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO` IS NOT NULL
	AND cta_pxt_persona_externa.`PXT_EMPRESA` IN
		(SELECT cta_etr_empresa_trabajo.`ETR_NOMBRE`
		 FROM `cta_etr_empresa_trabajo` cta_etr_empresa_trabajo
		WHERE cta_etr_empresa_trabajo.`ETR_ID`=1)

		
select * from Con_Pco_Partida_Contable as model 
where model.pco_Fecha_Ingreso_Partida= 
(select max(m.pco_Fecha_Ingreso_Partida)
 from Con_Pco_Partida_Contable as m  
 where m.pco_Estado='P' 
 group by m.pco_Fecha_Ingreso_Partida)
 
 
 SELECT DISTINCT
     () AS DESCUENTO
     
FROM
     `cta_pxt_persona_externa` cta_pxt_persona_externa 
     INNER JOIN `cta_cas_cuenta_asociado` cta_cas_cuenta_asociado 
     ON cta_pxt_persona_externa.`PXT_ID` = cta_cas_cuenta_asociado.`PXT_ID`
     
WHERE cta_pxt_persona_externa.`PXT_CODIGO_EMPLEADO` = '9489191989'


SELECT    SUM(IF(pre.`pre_cuota` IS NULL,0,pre.`pre_cuota`))
     FROM
     	cta_cas_cuenta_asociado cas inner join cta_pxt_persona_externa p
     	on cas.pxt_id = p.pxt_id
       INNER JOIN `cta_pre_prestamo` pre ON cas.`pre_id` = pre.`pre_id`
    WHERE
        cas.`EST_ID`=13 AND  
	p.`PXT_CODIGO_EMPLEADO`='9489191989'
	

select asc2.asc_id
from cta_asc_asociado asc2
where
(
SELECT 
	(SELECT  IF(cah.`cah_saldo_actual` IS NULL,0,cah.`cah_saldo_actual`) AS saldo
	FROM 
		`cta_asc_asociado` asoc INNER JOIN `cta_cas_cuenta_asociado` cas 
	        ON asoc.`ASC_ID` = cas.`ASC_ID` 
		INNER JOIN `cta_cah_cuenta_ahorro` cah ON cas.`cah_id` = cah.`cah_id` 
		WHERE 
	        	cas.`EST_ID`=9 AND cah.`cah_id` LIKE 'A%' 
			and asoc.`ASC_ID`=asc1.`ASC_ID` 
	) AS TOTAL_APORTACION 
FROM 
    `cta_asc_asociado` asc1
WHERE 
     asc1.`EST_ID_2`=0 and asc1.`ASC_ID` = asc2.`ASC_ID`) > 0
     
     

-- Pruebas de orden de compra para sacar credito
select pre.* from Cta_Pre_Prestamo pre where pre.pre_Id = 
	(select pre2.pre_Id 
	from Cta_Cas_Cuenta_Asociado cas
	inner join cta_pre_prestamo pre2 on pre2.pre_id = cas.pre_id
	where cas.asc_Id = '200811193313' 
	and cas.pre_id is not null 
	and pre2.tpr_id is null)

