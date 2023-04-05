package br.gov.pe.sismepe.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pe.sismepe.domain.AgendaCentral;
import br.gov.pe.sismepe.domain.ItAgendaCentral;

@Repository
public interface ItAgendaCentralRepository extends JpaRepository<ItAgendaCentral,Long>{
	
	
	//Consultar os agendamentos sem atendimento criado
	//Consultar os agendamentos informando o paciente
	//Consultas os agendamentos informando a data
	
	//Consultas com Situacao de Agendamento, Paciente, Ambulatorial, DataAtendimento, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);

	//Consultas com Situacao de Agendamento, Paciente, Ambulatorial, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Pageable pageable);
	
	//Consultas com Situacao de Agendamento, Paciente, DataAtendimento, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, Date dataAgendamento, Pageable pageable);

	//Consultas com Situacao de Agendamento, Paciente, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_tipoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, String tpAgendaCentral, Pageable pageable);
	
	//Consultas com Situacao de Agendamento, Ambulatorial, DataAtendimento, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);
	
	//Consultas com Situacao de Agendamento, Ambulatorial, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Pageable pageable);
	
	//Consultas com Situacao de Agendamento, DataAtendimento, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Date dataAgendamento, Pageable pageable);
	
	//Consultas com Situacao de Agendamento, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_tipoAndAtendimentoIsNull(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, String tpAgendaCentral, Pageable pageable);
		
	//Consultas com Ambulatorial, DataAtendimento, Paciente, AtendimentoNull
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamento(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, Date dataAgendamento, Pageable pageable);

	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamento(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_tipo(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, String tpAgendaCentral, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralIn(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Pageable pageable);
	
	//Consultar os agendamentos sem informar o paciente
	//Consultas os agendamentos informando a data
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamento(String ativo, String agendaAtivo, String agendaBloqueado, Date dataAgendamento, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamento(String ativo, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralIn(String ativo, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Pageable pageable);
	
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_tipo(String ativo, String agendaAtivo, String agendaBloqueado, String tpAgendaCentral, Pageable pageable);
		
	//Consultar os itens disponiveis para marcação
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndSituacaoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoGreaterThanEqual(String ativo, String situacao, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);
		
	@Transactional(readOnly = true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAfter(String ativo, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, Pageable pageable);
		
	@Transactional(readOnly=true)
	List<ItAgendaCentral> findByIdNotAndPaciente_idAndHoraInicial(Long id, Long pacienteId, Date horaInicial);
	
		
	//Consultas com Atendimento, Ambulatoriais, DataAtendimento, Paciente, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?8))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and it.CD_PACIENTE = ?5\r\n"
			+ "       and ac.CD_AGENDA_CENTRAL in (?6)\r\n"
			+ "       and ac.DT_AGENDA_CENTRAL = ?7\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento);

		
	List<ItAgendaCentral> findItAgendaByFiltro(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento);
	
	
	//Consultas com Atendimento, Ambulatoriais, DataAtendimento, Paciente
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento, Pageable pageable);
	
	//Consultas com Atendimento, Ambulatoriais, DataAtendimento, Situacao de Agendamento	
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?7))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and ac.CD_AGENDA_CENTRAL in (?5)\r\n"
			+ "       and ac.DT_AGENDA_CENTRAL = ?6\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento);

	//Consultas com Atendimento, Ambulatoriais, DataAtendimento
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, Date dataAgendamento, List<Integer> situacaoAtendimento, Pageable pageable);

	//Consultas com Atendimento, Ambulatoriais, Paciente, Situacao de Agendamento	
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?7))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and it.CD_PACIENTE = ?5\r\n"
			+ "       and ac.CD_AGENDA_CENTRAL in (?6)\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, List<Integer> situacaoAtendimento);
	
	//Consultas com Atendimento, Ambulatoriais, Paciente
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentralInAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<AgendaCentral> agendaCentral, List<Integer> situacaoAtendimento, Pageable pageable);
	
	//Consultas com Atendimento, Ambulatoriais, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?6))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and ac.CD_AGENDA_CENTRAL in (?5)\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, List<Integer> situacaoAtendimento);
	
	//Consultas com Atendimento, Ambulatoriais
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentralInAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, List<AgendaCentral> agendaCentral, List<Integer> situacaoAtendimento, Pageable pageable);
	
	
						  
	//Consultas com Atendimento, DataAtendimento, Paciente, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = 'S'\r\n"
			+ "       and it.CD_PACIENTE = 79188\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, Date dataAgendamento, List<Integer> situacaoAtendimento);
	
	//Consultas com Atendimento, DataAtendimento, Paciente
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, Date dataAgendamento, List<Integer> situacaoAtendimento, Pageable pageable);
	
	//Consultas com Atendimento, DataAtendimento, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?6))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and ac.DT_AGENDA_CENTRAL = ?5\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Date dataAgendamento, List<Integer> situacaoAtendimento);

	//Consultas com Atendimento, DataAtendimento
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAgendaCentral_dataAgendamentoAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, Date dataAgendamento, List<Integer> situacaoAtendimento, Pageable pageable);

	//Consultas com Atendimento, Paciente, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?6))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "       and it.CD_PACIENTE = ?5\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<Integer> situacaoAtendimento);
	
	//Consultas com Atendimento, Paciente
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndPaciente_idAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, Long pacienteId, List<Integer> situacaoAtendimento, Pageable pageable);
	
	//Consultas com Atendimento, Situacao de Agendamento
	@Transactional(readOnly=true)
	@Query(value = "select * from it_agenda_central it \r\n"
			+ "       inner join agenda_central ac on ac.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join agenda_central_amb aca on aca.CD_AGENDA_CENTRAL = it.CD_AGENDA_CENTRAL\r\n"
			+ "       inner join prestador prest on prest.CD_PRESTADOR = aca.CD_PRESTADOR\r\n"
			+ "       inner join especialidade esp on esp.CD_ESPECIALIDADE = aca.CD_ESPECIALIDADE\r\n"
			+ "       left outer join atendimento a on a.CD_ATENDIMENTO = it.CD_ATENDIMENTO\r\n"
			+ "       where it.sn_ativo = ?1\r\n"
			+ "		  and ((it.TP_SITUACAO in (?2) and it.CD_ATENDIMENTO is null) or (a.CD_SITUACAO_ATENDIMENTO in (?5))) \r\n"
			+ "       and ac.SN_ATIVO = ?3\r\n"
			+ "       and ac.SN_BLOQUEADO = ?4\r\n"
			+ "", nativeQuery = true)
	List<ItAgendaCentral> findByAtivoAndSituacaoInAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAtendimento_situacaoIn(String ativo, List<String> situacao, String agendaAtivo, String agendaBloqueado, List<Integer> situacaoAtendimento);

	//Consultas com Atendimento
	@Transactional(readOnly=true)
	Page<ItAgendaCentral> findByAtivoAndAgendaCentral_ativoAndAgendaCentral_bloqueadoAndAtendimento_situacaoIn(String ativo, String agendaAtivo, String agendaBloqueado, List<Integer> situacaoAtendimento, Pageable pageable);
	
	
	
}
