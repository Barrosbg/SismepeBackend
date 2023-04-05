package br.gov.pe.sismepe.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.gov.pe.sismepe.domain.Comorbidade;
import br.gov.pe.sismepe.domain.Endereco;
import br.gov.pe.sismepe.domain.Ome;
import br.gov.pe.sismepe.domain.Pessoa;
import br.gov.pe.sismepe.domain.PessoaDependente;
import br.gov.pe.sismepe.domain.PessoaTitular;
import br.gov.pe.sismepe.domain.Uf;
import br.gov.pe.sismepe.domain.Usuario;
import br.gov.pe.sismepe.domain.enums.TipoDemandaOrdemJudicial;
import br.gov.pe.sismepe.dto.GrauParentescoDTO;
import br.gov.pe.sismepe.dto.PessoaNewDTO;
import br.gov.pe.sismepe.dto.PessoaTitularDTO;
import br.gov.pe.sismepe.dto.RecadastramentoTitularDTO;
import br.gov.pe.sismepe.dto.TelefoneDTO;
import br.gov.pe.sismepe.dto.UploadArquivoDTO;
import br.gov.pe.sismepe.dto.UploadArquivoInfoDTO;
import br.gov.pe.sismepe.repositories.EnderecoRepository;
import br.gov.pe.sismepe.repositories.OmeRepository;
import br.gov.pe.sismepe.repositories.ParentescoRepository;
import br.gov.pe.sismepe.repositories.PessoaDependenteRepository;
import br.gov.pe.sismepe.repositories.PessoaRepository;
import br.gov.pe.sismepe.repositories.PessoaTitularRepository;
import br.gov.pe.sismepe.repositories.UfRepository;
import br.gov.pe.sismepe.repositories.UsuarioRepository;
import br.gov.pe.sismepe.security.UserSS;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import br.gov.pe.sismepe.util.Utils;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repo;
	
	@Autowired
	private PessoaDependenteRepository dependenteRepository;
	
	@Autowired
	private PessoaTitularRepository titularRepository;

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private UfRepository ufRepo;
	
	@Autowired
	private ParentescoRepository parentescoRepo;
	
	@Autowired
	private OmeRepository omeRepo;

	@Autowired
	private MinioStorageService minioStorageService;
	
	public Pessoa find(Long id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrato! ID: " + id));
	}

	public PessoaDependente findDependenteByMatriculaAndSequencial(Integer matricula, Integer sequencial) {
		PessoaDependente p = repo.findPessoaDependenteByMatriculaAndSequencial(matricula, sequencial);
		
		return p;
	}
	
	public Page<PessoaDependente> findDependentesByMatriculaAndSequencial(Integer matricula, Integer sequencial, 
			String dataConcessao, String dataUltimaAtualizacao, String dataCancelamento, String dataBloqueio, 
			Integer page, Integer linesPerPage, String orderBy, String directions) throws ParseException {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		Page<PessoaDependente> dependentes = null;
				
		if(matricula != null && sequencial != null && Utils.isStringNaoNulaNaoVazia(dataConcessao) && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {
			Date data = Utils.getDate(dataConcessao);
			Date dataAtualizacao = Utils.getDate(dataUltimaAtualizacao);
			dependentes = repo.findDependentesByMatriculaAndSequencialAndDataConcessaoGreaterThanEqualAndUltimaAtualizacaoGreaterThanEqual(matricula, sequencial, data, dataAtualizacao, pageRequest);
		
		} else if(matricula != null && sequencial != null && Utils.isStringNaoNulaNaoVazia(dataConcessao)) {
			Date data = Utils.getDate(dataConcessao);
			dependentes = repo.findDependentesByMatriculaAndSequencialAndDataConcessaoGreaterThanEqual(matricula, sequencial, data, pageRequest);
		
		} else if(matricula != null && sequencial != null && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {
			Date data = Utils.getDate(dataUltimaAtualizacao);
			dependentes = repo.findDependentesByMatriculaAndSequencialAndUltimaAtualizacaoGreaterThanEqual(matricula, sequencial, data, pageRequest);
		
		} else if(matricula != null && sequencial != null) {
			dependentes = repo.findDependentesByMatriculaAndSequencial(matricula, sequencial, pageRequest);
		
		} else if(matricula != null && Utils.isStringNaoNulaNaoVazia(dataConcessao)) {
			Date data = Utils.getDate(dataConcessao);
			dependentes = repo.findDependentesByMatriculaAndDataConcessaoGreaterThanEqual(matricula, data, pageRequest);
		
		} else if(matricula != null && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {
			Date data = Utils.getDate(dataUltimaAtualizacao);
			dependentes = repo.findDependentesByMatriculaAndUltimaAtualizacaoGreaterThanEqual(matricula, data, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataConcessao) && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {
			Date data = Utils.getDate(dataConcessao);
			Date dataAtualizacao = Utils.getDate(dataUltimaAtualizacao);
			dependentes = repo.findDependentesByDataConcessaoGreaterThanEqualAndUltimaAtualizacaoGreaterThanEqual(data, dataAtualizacao, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataConcessao)) {
			Date data = Utils.getDate(dataConcessao);
			dependentes = repo.findDependentesByDataConcessaoGreaterThanEqual(data, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {
			Date data = Utils.getDate(dataUltimaAtualizacao);
			dependentes = repo.findDependentesByUltimaAtualizacaoGreaterThanEqual(data, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataCancelamento)) {
			Date data = Utils.getDate(dataCancelamento);
			Date fimDia = new Date();
			
			data.setHours(0);
			data.setMinutes(0);
			data.setSeconds(0);
			
			dependentes = repo.findDependentesByDataCancelamentoBetween(data, fimDia, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataBloqueio)) {
			Date data = Utils.getDate(dataBloqueio);
			Date fimDia = new Date();
			
			data.setHours(0);
			data.setMinutes(0);
			data.setSeconds(0);
			
			dependentes = repo.findDependentesByDataBloqueioBetween(data, fimDia, pageRequest);
		
		}
		else if (matricula != null) {
			dependentes = repo.findDependentesByMatricula(matricula, pageRequest);
		
		} else {					
			dependentes = dependenteRepository.findAll(pageRequest);
		}
		
		return dependentes;
	}
	
	public Page<PessoaTitular> findTitularAtivoByMatricula(Integer matricula, String dataInclusao, String dataUltimaAtualizacao,
			String dataCancelamento, String dataConcessao, Integer page, Integer linesPerPage, String orderBy, String directions) throws ParseException {
		Page<PessoaTitular> list = null;
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		
		if(matricula != null && Utils.isStringNaoNulaNaoVazia(dataInclusao) && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {			
			Date data = Utils.getDate(dataInclusao);
			Date dataAtualizacao = Utils.getDate(dataUltimaAtualizacao);
			list = repo.findPessoaTitularByMatriculaAndDataInclusaoGreaterThanEqualAndDataAlteracaoGreaterThanEqual(matricula, data, dataAtualizacao, pageRequest);
		
		} else if(matricula != null && Utils.isStringNaoNulaNaoVazia(dataInclusao)) {			
			Date data = Utils.getDate(dataInclusao);
			list = repo.findPessoaTitularByMatriculaAndDataInclusaoGreaterThanEqual(matricula, data, pageRequest);
		
		} else if(matricula != null && Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {			
			Date data = Utils.getDate(dataUltimaAtualizacao);
			
			list = repo.findPessoaTitularByMatriculaAndDataAlteracaoGreaterThanEqual(matricula, data, pageRequest);
			if (list.getContent().isEmpty()) {
				list = repo.findPessoaTitularByMatriculaAndDataCancelamentoGreaterThanEqual(matricula, data, pageRequest);
			}
			if (list.getContent().isEmpty()) {
				list = repo.findPessoaTitularByMatriculaAndDataConcessaoGreaterThanEqual(matricula, data, pageRequest);
			}
			if (list.getContent().isEmpty()) {
				PessoaTitular pt = repo.findTitularByMatricula(matricula);
				PessoaTitular p = (PessoaTitular) repo.findPessoaByIdAndDataCadastroGreaterThanEqual(pt.getId(), data);
				
				List<PessoaTitular> ps = new ArrayList<>();
				ps.add(p);
				
				list = new PageImpl<>(ps, pageRequest, ps.size());
			}
		} else if(Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao) && Utils.isStringNaoNulaNaoVazia(dataInclusao)) {			
			Date data = Utils.getDate(dataInclusao);
			Date dataAtualizacao = Utils.getDate(dataUltimaAtualizacao);
			list = repo.findPessoaTitularByDataInclusaoGreaterThanEqualAndDataAlteracaoGreaterThanEqual(data, dataAtualizacao, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataCancelamento)) {
			Date data = Utils.getDate(dataCancelamento);
			Date fimDia = new Date();
			
			data.setHours(0);
			data.setMinutes(0);
			data.setSeconds(0);
			
			list = repo.findPessoaTitularByDataCancelamentoBetween(data, fimDia, pageRequest);
		} else if(Utils.isStringNaoNulaNaoVazia(dataConcessao)) {
			Date data = Utils.getDate(dataConcessao);
			Date fimDia = new Date();
			
			data.setHours(0);
			data.setMinutes(0);
			data.setSeconds(0);
			list = repo.findPessoaTitularByDataConcessaoBetween(data, fimDia, pageRequest);
		}
		else if(matricula != null) {	
			list = repo.findPessoaTitularByMatricula(matricula, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataInclusao)) {	
			Date data = Utils.getDate(dataInclusao);
			list = repo.findPessoaTitularByDataInclusaoGreaterThanEqual(data, pageRequest);
		
		} else if(Utils.isStringNaoNulaNaoVazia(dataUltimaAtualizacao)) {	
			Date data = Utils.getDate(dataUltimaAtualizacao);
			list = repo.findPessoaTitularByDataAlteracaoGreaterThanEqual(data, pageRequest);
		
		} else {
			list = titularRepository.findAll(pageRequest);			
		}
		
		return list;
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Page<Pessoa> findByPessoaNomeMatriculaCpf(String nome, Integer matricula, String cpf, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		
		List<Pessoa> listaPessoa;
		if (matricula != null || (!StringUtils.isEmpty(nome)) || (!StringUtils.isEmpty(cpf))) {
			if(matricula!=null) {
				List<PessoaTitular> listaPessoaTitular = repo.findTitularsByMatriculaAndDataCancelamentoIsNull(matricula);
				List<PessoaDependente> listaPessoaDependente = repo.findDependentesByMatriculaAndDataCancelamentoIsNullAndDataBloqueioIsNull(matricula); 
				if(listaPessoaDependente.isEmpty()) {
					listaPessoa = listaPessoaTitular.stream().map(obj -> obj).collect(Collectors.toList()); 
				}else { 
					listaPessoa = listaPessoaDependente.stream().map(obj -> obj).collect(Collectors.toList());
					listaPessoa.add(listaPessoaTitular.get(0)); 
				}
				List<Long> listId = listaPessoa.stream().map(obj -> obj.getId()).collect(Collectors.toList());
				if(!StringUtils.isEmpty(nome)) {
					if(!StringUtils.isEmpty(cpf)) {
						listaPessoa = repo.findByNomeContainingAndCpfAndIdIn(nome, cpf, listId);
					}
					listaPessoa = repo.findByNomeContainingAndIdIn(nome, listId);
				}
				if(!StringUtils.isEmpty(cpf)) {
					listaPessoa = repo.findByCpfAndIdIn(cpf, listId);
				}
				Page<Pessoa> pageRetorno = new PageImpl<Pessoa>(listaPessoa,pageRequest,listaPessoa.size()); 
				return pageRetorno;
			} 
			if(!StringUtils.isEmpty(nome)) {
				if(!StringUtils.isEmpty(cpf)) {
					listaPessoa = repo.findByNomeContainingAndCpf(nome, cpf);					
				}else {
					listaPessoa = repo.findByNomeContaining(nome);
				}					
				Page<Pessoa> pageRetorno = new PageImpl<Pessoa>(listaPessoa,pageRequest,listaPessoa.size());
				return pageRetorno;
			}			
			listaPessoa = repo.findByNomeContainingAndCpf(nome, cpf);	
			Page<Pessoa> pageRetorno = new PageImpl<Pessoa>(listaPessoa,pageRequest,listaPessoa.size());
			return pageRetorno;
			
		}
		listaPessoa = repo.findAll();
		Page<Pessoa> pageRetorno = new PageImpl<Pessoa>(listaPessoa,pageRequest,listaPessoa.size());
		return pageRetorno;
	}

	public Pessoa save(Pessoa pessoa) {
		return repo.save(pessoa);
	}

	public Pessoa fromDTO(PessoaNewDTO objDTO) {
		UserSS userSS = (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuarioLogado = usuarioRepo.findByLogin(userSS.getLogin());
		return new Pessoa(null, objDTO.getNome(), objDTO.getCpf(), new Date(),new Date(),usuarioLogado,"S");
	}

	public Pessoa insert(Pessoa obj) {
		return repo.save(obj);
	}
	
	public Pessoa save(GrauParentescoDTO dto, boolean basico) throws ObjectNotFoundException {
		PessoaDependente p = repo.findPessoaDependenteByMatriculaAndSequencial(dto.getMatricula(), dto.getSequencial());
		
		if (basico) {
			if (dto.getGrauParentesco() == 2) {
				p.setDescricaoDireitoOrdemJudicial(dto.getNumeroProcesso());
				p.setObservacao(dto.getNumeroSEI());
				p.setTemDireitoOrdemJudicial("S	");
				
				if (dto.getTipoDemanda() == 2) {
					// filho maior não universitario
					LocalDate nasc = dto.getDataNascimento().toLocalDate();
					LocalDate hoje = LocalDate.now();
					
					Period periodo = Period.between(nasc, hoje);
					
					if (periodo.getYears() <= 18 || p.getUniversitario().equalsIgnoreCase("S")) {					
						throw new DateTimeException("Dependente menor de 18 anos ou univeritário");
					}
				} else if (dto.getTipoDemanda() == 3) {
					// filho menor
					LocalDate nasc = dto.getDataNascimento().toLocalDate();
					LocalDate hoje = LocalDate.now();
					
					Period periodo = Period.between(nasc, hoje);
					
					if (periodo.getYears() >= 18) {
						throw new DateTimeException("Dependente maior de 18 anos");
					}
				}
				
				TipoDemandaOrdemJudicial tipo = TipoDemandaOrdemJudicial.fromInteger(dto.getTipoDemanda());
				
				p.setTipoDemanda(tipo);
			} else {
				p.setParentesco(parentescoRepo.findById(dto.getBeneficiarioDireto()).orElseThrow(() -> new ObjectNotFoundException("Parentesco não encontrado")));
				
				if (dto.getBeneficiarioDireto() == 1 || dto.getBeneficiarioDireto() == 10 || dto.getBeneficiarioDireto() == 2) {
					String uni = dto.isUniversitario() ? "S" : "N";
					p.setUniversitario(uni);
					
					String inv = dto.isInvalido() ? "S" : "N";
					p.setInvalido(inv);
				} else {
					p.setUniversitario("N");
					p.setInvalido("N");
				}
			}
		}
		
		p.setRecadastramento("S");
		p.setUltimaAtualizacao(new Date(System.currentTimeMillis()));
		
		if (p.getDataValidadePlano() == null && dto.getDataValidadePlano() != null) {
			if (!dto.getDataValidadePlano().before(new Date(System.currentTimeMillis()))) {
				p.setDataValidadePlano(dto.getDataValidadePlano());
			} else {
				throw new DateTimeException("Data de validade do plano não pode ser no passado");
			}
		}
		p.setDataNascimento(dto.getDataNascimento());
		
		p.setCpf(dto.getCpf());
		p.setSexo(dto.getGenero());
		
		p.setTelefone(dto.getNumeroTelefone());
		p.setTelefone2(dto.getNumeroWhatsapp());
		p.setEmail(dto.getEmail());
		
		p.setNomeMae(dto.getNomeMae());
		
		Uf uf = ufRepo.findById(dto.getUf()).orElseThrow(() -> new ObjectNotFoundException("Uf não encontrado: " + dto.getUf()));
		
		if (p.getEndereco() == null) {
			Endereco end = new Endereco();
			end.setLogradouro(dto.getLogradouro());
			end.setNumero(dto.getNumero());
			end.setComplemento(dto.getComplemento());
			end.setCep(dto.getCep().replaceAll("\\-", ""));
			end.setBairro("");
			end.setCidade(dto.getCidade());
			end.setUf(uf.getSigla());
			
			end.setAtivo("S");
			
			p.setEndereco(end);
			enderecoRepo.save(end);
		} else {
			Endereco end = enderecoRepo.findById(p.getEndereco().getId()).orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
			end.setLogradouro(dto.getLogradouro());
			end.setNumero(dto.getNumero());
			end.setComplemento(dto.getComplemento());
			end.setCep(dto.getCep().replaceAll("\\-", ""));
			end.setCidade(dto.getCidade());
			end.setUf(uf.getSigla());
			
			enderecoRepo.save(end);
		}
		
		repo.save(p);
		
		return p;
	}
	
	public Pessoa saveTitular(RecadastramentoTitularDTO dto) throws ObjectNotFoundException {
		PessoaTitular p = repo.findTitularByMatricula(dto.getMatricula());
		
		if (p == null) {
			throw new ObjectNotFoundException("Pessoa Titular não encontrada");
		}
		
		p.setRecadastramento("S");
		
		p.setDataNascimento(dto.getDataNascimento());
		
		p.setCpf(dto.getCpf());
		p.setSexo(dto.getGenero());
		
		p.setTelefone(dto.getNumeroTelefone());
		p.setTelefone2(dto.getNumeroWhatsapp());
		p.setEmail(dto.getEmail());
		
		p.setNomeMae(dto.getNomeMae());
		
		Uf uf = ufRepo.findById(dto.getUf()).orElseThrow(() -> new ObjectNotFoundException("Uf não encontrado: " + dto.getUf()));
		
		if (p.getEndereco() == null) {
			Endereco end = new Endereco();
			end.setLogradouro(dto.getLogradouro());
			end.setNumero(dto.getNumero());
			end.setComplemento(dto.getComplemento());
			end.setCep(dto.getCep().replaceAll("\\-", ""));
			end.setBairro("");
			end.setCidade(dto.getCidade());
			end.setUf(uf.getSigla());
			
			end.setAtivo("S");
			
			p.setEndereco(end);
			enderecoRepo.save(end);
		} else {
			Endereco end = enderecoRepo.findById(p.getEndereco().getId()).orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
			end.setLogradouro(dto.getLogradouro());
			end.setNumero(dto.getNumero());
			end.setComplemento(dto.getComplemento());
			end.setCep(dto.getCep().replaceAll("\\-", ""));
			end.setCidade(dto.getCidade());
			end.setUf(uf.getSigla());
			
			enderecoRepo.save(end);
		}
		
		repo.save(p);
		
		return p;
	}
	
	
	public Page<PessoaTitularDTO> findByPessoaTitularByMatriculaCorporacaoOme(Integer matricula, Integer corporacao,  Long ome, Integer perfil, Integer page,
			Integer linesPerPage, String orderBy, String directions) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);

		return repo.pessoaTitularPorMatriculaOmeCorporacaoPerfil(matricula, corporacao, ome, perfil, pageRequest);
	}

	public void atualizarOme(PessoaTitularDTO dto) throws ObjectNotFoundException {
		PessoaTitular pessoaTitular = repo.findTitularByMatriculaAndCorporacao(dto.getMatricula(), dto.getCorporacao());
		if(pessoaTitular == null) {
			throw new ObjectNotFoundException("Pessoa titular não encontrada! Matricula: "+ dto.getMatricula() +", Corporação: " + dto.getCorporacao());
		}
		
		Ome ome = omeRepo.findById(dto.getOmeId()).orElseThrow(() -> new ObjectNotFoundException("Ome não encontrada! ID: " + dto.getOmeId()));
		
		pessoaTitular.setOme(ome);
		
		repo.save(pessoaTitular);
	}
	
	public Pessoa adicionarComorbidades(Long id, List<Comorbidade> comorbidades) {
		Pessoa pessoa = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! ID: " + id));
		
		for (Comorbidade comorbidade: comorbidades) {
			if(!pessoa.getComorbidades().contains(comorbidade)) {
				pessoa.getComorbidades().add(comorbidade);
			}
		}
		
		return repo.save(pessoa);
	}
	
	public Pessoa removerComorbidades(Long id, List<Comorbidade> comorbidades) {
		Pessoa pessoa = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! ID: " + id));
		
		for (Comorbidade comorbidade: comorbidades) {
			if(!pessoa.getComorbidades().contains(comorbidade)) {
				throw new ObjectNotFoundException("Não foi encontrada a comorbidade " + comorbidade.getDescricao().toUpperCase() + " na pessoa informada!");
			}
			pessoa.getComorbidades().remove(comorbidade);
		}
		
		return repo.save(pessoa);
	}
	
	public Pessoa atualizarComorbidades(Long id, List<Comorbidade> comorbidades) {
		Pessoa pessoa = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! ID: " + id));
		
//		adiciona as comorbidades que ainda não estão vinculadas a pessoa
		for (Comorbidade comorbidade: comorbidades) {
			if(!pessoa.getComorbidades().contains(comorbidade)) {
				pessoa.getComorbidades().add(comorbidade);
			}
		}
		List<Comorbidade>list = new ArrayList<Comorbidade>();
		list.addAll(pessoa.getComorbidades());
//		remove as comorbidades que não fazem parte da lista
		for (Comorbidade c: list) {
			if(!comorbidades.contains(c)) {
				pessoa.getComorbidades().remove(c);
			}
		}
		
		return repo.save(pessoa);
	}
	
	
	public Pessoa save(TelefoneDTO dto) {
		Pessoa p = repo.findById(dto.getCdPessoa()).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada: " + dto.getCdPessoa()));
		
		p.setTelefone(dto.getTelefone());
		
		return repo.save(p);		
	}
	
	public UploadArquivoInfoDTO save(UploadArquivoDTO uploadArquivoDTO, Long cdPessoa) throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException, InvalidExpiresRangeException {
		UploadArquivoInfoDTO info = minioStorageService.saveFile(uploadArquivoDTO);
		
		Pessoa p = repo.findById(cdPessoa).orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada: " + cdPessoa));
		
		if (p.getBucketMinio() != null && p.getFilenameFotoMinio() != null) {
			minioStorageService.deleteFile(p.getBucketMinio(), p.getFilenameFotoMinio());
		}
		
		p.setBucketMinio(info.getBucket());
		p.setFilenameFotoMinio(info.getFilename());
		repo.save(p);
		
		return info;
	}
}
