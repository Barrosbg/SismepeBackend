package br.gov.pe.sismepe.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.ItAgendaCentral;

@Repository
public class ItAgendaCentralRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;
	
	public List<ItAgendaCentral> findItAgendaByFiltro(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento){
		
		String listaAgendaCentral = "";
		agendaCentral.forEach(obj -> {
			if(listaAgendaCentral.equals("")) {
				listaAgendaCentral.concat(""+obj.getId().toString());  
			}else {
				listaAgendaCentral.concat(","+obj.getId().toString());
			}
		});
						
		String jpql = " select it from ItAgendaCentral it \r\n"
				+ "       inner join AgendaCentral ac on ac.id = it.agendaCentral.id \r\n"
				+ "       inner join AgendaCentralAmb aca on aca.id = it.agendaCentral\r\n"
				+ "       inner join Prestador prest on prest.id = aca.prestador\r\n"
				+ "       inner join Especialidade esp on esp.id = aca.especialidade\r\n"
				+ "       left outer join Atendimento a on a.id = it.atendimento\r\n"
				+ "       where it.ativo = :ativo \r\n"
				+ "		  and ((it.situacao in (:situacao) and it.atendimento is null) or (a.situacao in (:situacaoAtendimento))) \r\n"
				+ "       and ac.ativo = :agendaAtivo \r\n"
				+ "       and ac.bloqueado = :agendaBloqueado \r\n"
				+ "       and it.paciente.id = :pacienteId \r\n"
				+ "       and ac.dataAgendamento = :dataAgendamento  ";
		
		final List<Object[]> list = manager.createNativeQuery(jpql)
				.setParameter("ativo", ativo)
				.setParameter("situacao", situacao)
				.setParameter("agendaAtivo", agendaAtivo)
				.setParameter("agendaBloqueado", agendaBloqueado)
				.setParameter("pacienteId", pacienteId)
				.setParameter("dataAgendamento", dataAgendamento)
				.setParameter("situacaoAtendimento", situacaoAtendimento)
				.getResultList();
		
		return null;
	}
	
}
