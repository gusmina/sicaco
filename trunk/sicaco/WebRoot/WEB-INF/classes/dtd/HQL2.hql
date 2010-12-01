from CtaAscAsociado asc2 
where (SELECT 
	(SELECT  if(cah.cahSaldoActual IS NULL,0,cah.cahSaldoActual) AS saldo 
		FROM CtaCasCuentaAsociado cas 
		INNER JOIN cas.ctaAscAsociado as asoc 
		INNER JOIN cas.ctaCahCuentaAhorro as cah 
		WHERE cas.ctrEstEstado.estId = 9 
		AND cah.cahId LIKE 'A%' 
		AND asoc.ascId = asc1.ascId ) AS TOTAL_APORTACION 
	FROM CtaAscAsociado asc1 
	WHERE asc1.ctrEstEstado.estId = 0 
	and asc1.ascId = asc2.ascId) > 0