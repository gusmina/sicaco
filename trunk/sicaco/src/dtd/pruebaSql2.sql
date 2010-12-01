-- super consulta de descuentos

select a.asc_id, b.asc_codigo, concat(c.per_primer_apellido, ', ', c.per_primer_nombre) nombre,
       a.total_aportacion, a.total_ahorro, a.total_prestamo, a.total_seguro
       
from (        

	select a.asc_id, sum(a.total_aportacion) total_aportacion, sum(a.total_ahorro) total_ahorro, 
			sum(a.total_prestamo) total_prestamo, sum(a.total_seguro) total_seguro
	
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
	
	group by a.asc_id
) a

join cta_asc_asociado b

on a.asc_id = b.asc_id


join sec_per_persona c

on b.per_id = c.per_id;


