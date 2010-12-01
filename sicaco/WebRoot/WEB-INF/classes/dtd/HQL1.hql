SELECT  IF(cas.ctaCahCuentaAhorro.cahSaldoActual IS NULL,0,cas.ctaCahCuentaAhorro.cahSaldoActual) AS saldo 
FROM CtaCasCuentaAsociado cas 
WHERE cas.ctrEstEstado.estId = 9 
AND cas.ctaCahCuentaAhorro.cahId LIKE 'A%' 