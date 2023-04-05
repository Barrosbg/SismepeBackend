package br.gov.pe.sismepe.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.dto.EstatisticaAtendimentoDTO;

@Repository
public class EstatisticaAtendimentoRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	public List<EstatisticaAtendimentoDTO> pesquisarEstatisticasDeAtendimentoPrestador(Long prestadorId, String periodoInicio, String periodoFim){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select c.DT_AGENDA_CENTRAL as data, ");
		sb.append("SUM(case when it.TP_SITUACAO = 'A' then 1 else 0 end) AS agendados, ");
		sb.append("SUM(case when a.CD_SITUACAO_ATENDIMENTO = 4 then 1 else 0 end) AS realizados from it_agenda_central it ");
		sb.append("inner join agenda_central_amb ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL ");
		sb.append("inner join agenda_central c on c.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL ");
		sb.append("left join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO ");
		sb.append("where ac.CD_PRESTADOR = :prestadorId ");
		sb.append("and c.DT_AGENDA_CENTRAL between :periodoInicio and :periodoFim ");
		sb.append("and c.SN_BLOQUEADO = 'N' ");
		sb.append("and c.SN_ATIVO = 'S' ");
		sb.append("and it.sn_ativo = 'S' ");
		sb.append("and it.TP_SITUACAO not in ('L', 'C') ");
		sb.append("group by c.DT_AGENDA_CENTRAL order by c.DT_AGENDA_CENTRAL; ");
				
		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("prestadorId", prestadorId);
		query.setParameter("periodoInicio", periodoInicio);
		query.setParameter("periodoFim", periodoFim);
		
		@SuppressWarnings("unchecked")
		final List<Object[]> result = query.getResultList();

		List<EstatisticaAtendimentoDTO> list = new ArrayList<EstatisticaAtendimentoDTO>();
		for (final Object[] obj : result) {
			EstatisticaAtendimentoDTO dto = new EstatisticaAtendimentoDTO();
			dto.setData((Date) obj[0]);
			dto.setAgendados(((BigDecimal) obj[1]).intValue());
			dto.setRealizados(((BigDecimal) obj[2]).intValue());
			
			list.add(dto);
		}
		
		return list;
	
	}
	
	public List<EstatisticaAtendimentoDTO> pesquisarEstatisticasDeAtendimentoPaciente(Long pacienterId, String periodoInicio, String periodoFim){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select c.DT_AGENDA_CENTRAL as data, ");
		sb.append("SUM(case when it.TP_SITUACAO = 'A' then 1 else 0 end) AS agendados, ");
		sb.append("SUM(case when a.CD_SITUACAO_ATENDIMENTO = 4 then 1 else 0 end) AS realizados from it_agenda_central it ");
		sb.append("inner join agenda_central_amb ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL ");
		sb.append("inner join agenda_central c on c.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL ");
		sb.append("left join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO ");
		sb.append("where it.CD_PACIENTE = :pacienterId ");
		sb.append("and c.DT_AGENDA_CENTRAL between :periodoInicio and :periodoFim ");
		sb.append("and c.SN_BLOQUEADO = 'N' ");
		sb.append("and c.SN_ATIVO = 'S' ");
		sb.append("and it.sn_ativo = 'S' ");
		sb.append("and it.TP_SITUACAO not in ('L', 'C') ");
		sb.append("group by c.DT_AGENDA_CENTRAL order by c.DT_AGENDA_CENTRAL; ");
				
		Query query = this.entityManager.createNativeQuery(sb.toString());
		query.setParameter("pacienterId", pacienterId);
		query.setParameter("periodoInicio", periodoInicio);
		query.setParameter("periodoFim", periodoFim);
		
		@SuppressWarnings("unchecked")
		final List<Object[]> result = query.getResultList();

		List<EstatisticaAtendimentoDTO> list = new ArrayList<EstatisticaAtendimentoDTO>();
		for (final Object[] obj : result) {
			EstatisticaAtendimentoDTO dto = new EstatisticaAtendimentoDTO();
			dto.setData((Date) obj[0]);
			dto.setAgendados(((BigDecimal) obj[1]).intValue());
			dto.setRealizados(((BigDecimal) obj[2]).intValue());
			
			list.add(dto);
		}
		
		return list;
	
	}

}
